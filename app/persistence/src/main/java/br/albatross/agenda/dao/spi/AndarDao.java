package br.albatross.agenda.dao.spi;

import br.albatross.agenda.domain.models.Andar;

public interface AndarDao extends Dao<Andar, Integer> {

    boolean hasContatos(Integer id);

    boolean existsByNome(String nome);

    boolean existsByNomeAndNotById(String nome, Integer id);

}
