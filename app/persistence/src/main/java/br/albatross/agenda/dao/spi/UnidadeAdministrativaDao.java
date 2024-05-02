package br.albatross.agenda.dao.spi;

import br.albatross.agenda.domain.models.UnidadeAdministrativa;

public interface UnidadeAdministrativaDao extends Dao<UnidadeAdministrativa, Integer> {

	boolean hasSetores(Integer id);

    boolean existsBySigla(String sigla);
    boolean existsBySiglaAndNotById(String sigla, Integer id);

    boolean existsByDescricao(String descricao);
    boolean existsByDescricaoAndNotById(String descricao, Integer id);    

}
