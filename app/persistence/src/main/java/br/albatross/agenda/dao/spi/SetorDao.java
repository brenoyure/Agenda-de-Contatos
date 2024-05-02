package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.models.Setor;

public interface SetorDao extends Dao<Setor, Integer> {

    boolean hasContatos(Integer id);

    boolean existsBySigla(String sigla);
    boolean existsBySiglaAndNotById(String sigla, Integer id);

    boolean existsByDescricao(String descricao);
	boolean existsByDescricaoAndNotById(String descricao, Integer id);

	Optional<Setor> findByIdInnerJoinFetchUnidadeAdministrativa(Integer id);
	List<Setor> findAllInnerJoinFetchUnidadeAdministrativa();

}
