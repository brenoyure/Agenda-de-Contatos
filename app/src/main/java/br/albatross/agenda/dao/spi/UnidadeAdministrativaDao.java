package br.albatross.agenda.dao.spi;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.models.entities.UnidadeAdministrativa;

public interface UnidadeAdministrativaDao {

	DadosParaListagemDeUnidade persist(UnidadeAdministrativa unidadeAdministrativa);
	DadosParaListagemDeUnidade merge(UnidadeAdministrativa unidadeAdministrativa);

	boolean existsById(Serializable id);
	boolean existsBySigla(String sigla);
	boolean existsByDescricao(String descricao);

	boolean existsBySigla(Serializable id, String sigla);
	boolean existsByDescricao(Serializable id, String descricao);	

	boolean hasSetores(Serializable id);

	List<DadosParaListagemDeUnidade> findAll();
	Optional<DadosParaListagemDeUnidade> findById(Serializable id);
	Optional<UnidadeAdministrativa> getReferenceById(Serializable id);

	void delete(Serializable id);

}
