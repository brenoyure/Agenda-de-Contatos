package br.albatross.agenda.services.impl.unidades;

import java.io.Serializable;

import br.albatross.agenda.dao.spi.UnidadeAdministrativaDao;
import br.albatross.agenda.dto.spi.unidades.DadosParaAtualizacaoDeUnidade;
import br.albatross.agenda.dto.spi.unidades.DadosParaCadastroDeNovaUnidade;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.exceptions.CadastroException;
import br.albatross.agenda.exceptions.UnidadeExistenteException;
import br.albatross.agenda.models.entities.UnidadeAdministrativa;
import br.albatross.agenda.services.spi.unidades.UnidadeCadastroService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class CadastroUnidadeServiceImpl implements UnidadeCadastroService {

	@Inject
	private UnidadeAdministrativaDao dao;

	@Override
	public DadosParaListagemDeUnidade cadastrar(@Valid DadosParaCadastroDeNovaUnidade dadosNovos) throws CadastroException {

		if (dao.existsBySigla(dadosNovos.getSigla())) {
			throw new UnidadeExistenteException("Já existe uma Unidade com a sigla informada");
		}

		if (dao.existsByDescricao(dadosNovos.getDescricao())) {
			throw new UnidadeExistenteException("Já existe uma Unidade com a descrição informada");
		}

		var novaUnidade = new UnidadeAdministrativa(dadosNovos);
		return dao.persist(novaUnidade);

	}

	@Override
	public DadosParaListagemDeUnidade atualizar(@Valid DadosParaAtualizacaoDeUnidade dadosAtualizados) throws CadastroException {

		boolean existeOutraUnidadeComASigla     = dao.existsBySigla(dadosAtualizados.getId(), dadosAtualizados.getSigla());
		boolean existeOutraUnidadeComADescricao = dao.existsByDescricao(dadosAtualizados.getId(), dadosAtualizados.getDescricao());

		if (existeOutraUnidadeComASigla) {
			throw new UnidadeExistenteException("Já existe outra Unidade cadastrada com a Sigla informada");
		}

		if (existeOutraUnidadeComADescricao) {
			throw new UnidadeExistenteException("Já existe outra Unidade cadastrada com a Descrição informada");
		}

		var unidadeAtualizada = new UnidadeAdministrativa(dadosAtualizados);
		return dao.merge(unidadeAtualizada);

	}

	@Override
	public void excluir(Serializable id) throws CadastroException {

	    boolean unidadePossuiSetores = dao.hasSetores(id);

	    if (unidadePossuiSetores) {
	        throw new CadastroException("A Unidade ainda possui setores associados a ela. Exclusão Não Permitida");
	    }

	    dao.delete(id);

	}

}
