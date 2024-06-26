package br.albatross.agenda.services.impl.andar;

import br.albatross.agenda.dao.spi.AndarDao;
import br.albatross.agenda.domain.models.Andar;
import br.albatross.agenda.dto.impl.andar.DadosParaListagemDoAndarDto;
import br.albatross.agenda.dto.spi.andar.DadosParaAtualizacaoDoAndar;
import br.albatross.agenda.dto.spi.andar.DadosParaCadastroDoAndar;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.services.spi.andares.AndarCadastroService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class AndarCadastroServiceImpl implements AndarCadastroService {

    @Inject
    private AndarDao dao;

    @Override
    public DadosParaListagemDoAndar cadastrar(@Valid DadosParaCadastroDoAndar dadosNovos) throws CadastroException {

        if (dao.existsByNome(dadosNovos.getNome())) {
            throw new CadastroException("Já existe um Andar com o Nome informado");
        }

        var novoAndar = new Andar(dadosNovos.getNome());

        return new DadosParaListagemDoAndarDto(dao.persist(novoAndar));

    }

    @Override
    public DadosParaListagemDoAndar atualizar(@Valid DadosParaAtualizacaoDoAndar dadosAtualizados) throws CadastroException {

        if (dao.existsByNomeAndNotById(dadosAtualizados.getNome(), dadosAtualizados.getId())) {
            throw new CadastroException("Já existe um Andar com o Nome informado");
        }

        var andarParaSerAtualizado = new Andar(dadosAtualizados.getId(), dadosAtualizados.getNome());
        return new DadosParaListagemDoAndarDto(andarParaSerAtualizado);

    }

    @Override
    public void excluir(Integer id) throws CadastroException {

        if (dao.hasContatos(id)) {
            throw new CadastroException("Existem Contatos associados ao Andar. Exclusão Não Permitida");
        }

        dao.getReferenceById(Andar.class, id).ifPresent(dao::removeByReference);

    }

}
