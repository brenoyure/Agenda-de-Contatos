package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.models.UnidadeAdministrativa;

public interface UnidadeAdministrativaDao {

	DadosParaListagemDeUnidade persist(UnidadeAdministrativa unidadeAdministrativa);
	DadosParaListagemDeUnidade merge(UnidadeAdministrativa unidadeAdministrativa);

	boolean existsById(Integer id);
	boolean existsBySigla(String sigla);
	boolean existsByDescricao(String descricao);

	boolean existsBySigla(Integer id, String sigla);
	boolean existsByDescricao(Integer id, String descricao);	

	boolean hasSetores(Integer id);

	List<DadosParaListagemDeUnidade> findAll();
	Optional<DadosParaListagemDeUnidade> findById(Integer id);
	Optional<UnidadeAdministrativa> getReferenceById(Integer id);

	void delete(Integer id);

}
