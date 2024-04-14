package br.albatross.agenda.services.impl.contato;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.ContatoDao;
import br.albatross.agenda.dto.spi.contato.DadosParaAtualizacaoDeContato;
import br.albatross.agenda.dto.spi.contato.DadosParaCadastroDeNovoContato;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.exceptions.ContatoExistenteException;
import br.albatross.agenda.models.Contato;
import br.albatross.agenda.services.spi.andares.AndarConsultaService;
import br.albatross.agenda.services.spi.contatos.ContatoCadastroService;
import br.albatross.agenda.services.spi.contatos.ContatoConsultaService;
import br.albatross.agenda.services.spi.setores.SetorConsultaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class ContatoServiceImpl implements ContatoCadastroService, ContatoConsultaService {

    @Inject
    private ContatoDao dao;

    @Inject
    private AndarConsultaService andarConsultaService;
    
    @Inject
    private SetorConsultaService setorConsultaService;

    @Override
    public DadosParaListagemDeContato cadastrar(@Valid DadosParaCadastroDeNovoContato dadosNovos) throws CadastroException {

        if (dao.existsByNome(dadosNovos.getNome()))
            throw new ContatoExistenteException("Já existe outro Contato com o Nome informado, cadastro não realizado");
        
        var novoContato = new Contato(dadosNovos);

        if (dadosNovos.getAndarId() != null)
            andarConsultaService.obterReferenciaPorId(dadosNovos.getAndarId()).ifPresent(novoContato::setAndar);

        if (dadosNovos.getSetorId() != null)
            setorConsultaService.obterReferenciaPorId(dadosNovos.getSetorId()).ifPresent(novoContato::setSetor);

        return dao.persist(novoContato);

    }

    @Override
    public DadosParaListagemDeContato atualizar(@Valid DadosParaAtualizacaoDeContato dadosAtualizados) throws CadastroException {

        boolean existeOutroContatoComONome = dao.existsByNomeAndNotById(dadosAtualizados.getNome(), dadosAtualizados.getId());
        var andarReference                 = andarConsultaService.obterReferenciaPorId(dadosAtualizados.getAndarId());
        var setorReference                 = setorConsultaService.obterReferenciaPorId(dadosAtualizados.getSetorId());

        if (existeOutroContatoComONome)
            throw new ContatoExistenteException("Já existe outro Contato com o Nome informado. Atualização de Contato não Realizada");

        if (andarReference.isEmpty())
            throw new CadastroException("Andar com o Id informado não encontrado. Atualização de Contato não Realizada");

        if (setorReference.isEmpty())
            throw new CadastroException("Setor com o Id informado não encontrado. Atualização de Contato não Realizada");        

        return dao.merge(new Contato(dadosAtualizados, andarReference.get(), setorReference.get()));

    }    

    @Override
    public List<DadosParaListagemDeContato> listar() {

        return dao.findAll();

    }

    @Override
    public boolean existePorId(Long id) {

        return dao.existsById(id);

    }

    @Override
    public boolean existePorNome(String nome) {

        return dao.existsByNome(nome);

    }

    @Override
    public boolean existePorNome(Long id, String nome) {

        return dao.existsByNomeAndNotById(nome, id);

    }

    @Override
    public Optional<DadosParaListagemDeContato> buscarPorId(Long id) {

        return dao.findById(id);

    }

    @Override
    public Optional<Contato> obterReferenciaPorId(Long id) {

        return dao.getReferenceById(id);

    }

    @Override
    public void excluir(Long id) {

        dao.delete(id);

    }

}
