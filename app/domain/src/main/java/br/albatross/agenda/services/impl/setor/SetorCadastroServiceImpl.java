package br.albatross.agenda.services.impl.setor;

import br.albatross.agenda.dao.spi.SetorDao;
import br.albatross.agenda.domain.models.Setor;
import br.albatross.agenda.dto.impl.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.dto.spi.setor.DadosParaAtualizacaoDeSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.exceptions.SetorExistenteException;
import br.albatross.agenda.services.spi.setores.SetorCadastroService;
import br.albatross.agenda.services.spi.unidades.UnidadeConsultaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class SetorCadastroServiceImpl implements SetorCadastroService {

    @Inject
    private SetorDao dao;

    @Inject
    private UnidadeConsultaService unidadeConsultaService; 

    @Override
    public DadosParaListagemDeSetor cadastrar(@Valid DadosParaCadastroDeNovoSetor dadosNovos) throws CadastroException {

        boolean siglaJaExistente     = dao.existsBySigla(dadosNovos.getSigla());
        boolean descricaoJaExistente = dao.existsByDescricao(dadosNovos.getDescricao());

        if (siglaJaExistente) {
            throw new SetorExistenteException("Já existe um Setor com a Sigla informada");
        }

        if (descricaoJaExistente) {
            throw new SetorExistenteException("Já existe um Setor com a Descrição informada");
        }

        var unidadeReference = unidadeConsultaService.obterReferenciaPorId(dadosNovos.getUnidadeId());
        if (unidadeReference.isEmpty()) {
            throw new CadastroException("Unidade Administrativa com o Id informado não encontrada, Cadastro de Setor não realizado");
        }


        var novoSetor = new Setor(dadosNovos.getSigla(), dadosNovos.getDescricao(), unidadeReference.get());

        return new DadosParaListagemDeSetorDto(dao.persist(novoSetor));

    }

    @Override
    public DadosParaListagemDeSetor atualizar(@Valid DadosParaAtualizacaoDeSetor dadosAtualizados) throws CadastroException {

        boolean existeOutroSetorComASigla     = dao.existsBySiglaAndNotById(dadosAtualizados.getSigla(), dadosAtualizados.getId());
        boolean existeOutroSetorComADescricao = dao.existsByDescricaoAndNotById(dadosAtualizados.getDescricao(), dadosAtualizados.getId());

        if (existeOutroSetorComASigla) {
            throw new SetorExistenteException("Já existe outro Setor com a Sigla informada");
        }

        if (existeOutroSetorComADescricao) {
            throw new SetorExistenteException("Já existe outro Setor com a Descrição informada");
        }

        var unidadeReference = unidadeConsultaService.obterReferenciaPorId(dadosAtualizados.getUnidadeId());
        if (unidadeReference.isEmpty()) {
            throw new CadastroException("Unidade Administrativa com o Id informado não encontrada, Atualização de Setor não realizado");
        }

        var setorAtualizado = new Setor(dadosAtualizados.getId(), dadosAtualizados.getSigla(), dadosAtualizados.getDescricao(), unidadeReference.get());

        return new DadosParaListagemDeSetorDto(dao.merge(setorAtualizado));

    }

    @Override
    public void excluir(Integer id) throws CadastroException {

        boolean setorPossuiContatos = dao.hasContatos(id);

        if (setorPossuiContatos) {
            throw new CadastroException("O Setor possui Contatos associados. Exclusão Não Permitida");
        }

        dao.getReferenceById(Setor.class, id).ifPresent(dao::removeByReference);

    }

}
