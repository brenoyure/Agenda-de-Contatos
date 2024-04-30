package br.albatross.agenda.services.impl.contato;

import br.albatross.agenda.dao.spi.ContatoDao;
import br.albatross.agenda.dto.spi.contato.DadosParaAtualizacaoDeContato;
import br.albatross.agenda.dto.spi.contato.DadosParaCadastroDeNovoContato;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.exceptions.ContatoExistenteException;
import br.albatross.agenda.models.Contato;
import br.albatross.agenda.services.spi.andares.AndarConsultaService;
import br.albatross.agenda.services.spi.contatos.ContatoCadastroService;
import br.albatross.agenda.services.spi.setores.SetorConsultaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestScoped
@Transactional
public class ContatoCadastroServiceImpl implements ContatoCadastroService {

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

        if (existeOutroContatoComONome)
            throw new ContatoExistenteException("Já existe outro Contato com o Nome informado. Atualização de Contato não Realizada");

        var contatoAtualizado = new Contato(dadosAtualizados);

        if (dadosAtualizados.getAndarId() != null) {
            andarConsultaService
                .obterReferenciaPorId(dadosAtualizados.getAndarId())
                .ifPresent(contatoAtualizado::setAndar);
        }

        if (dadosAtualizados.getSetorId() != null) {
            setorConsultaService
                .obterReferenciaPorId(dadosAtualizados.getSetorId())
                .ifPresent(contatoAtualizado::setSetor);
        }

        return dao.merge(contatoAtualizado);

    }   

    @Override
    public void excluir(Long id) {

        dao.delete(id);

    }

}
