package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.models.Contato;

public interface ContatoDao extends Dao<Contato, Long> {

    boolean existsByNome(String nome);
    boolean existsByNomeAndNotById(String nome, Long id);

    Optional<Contato> findByIdLeftJoinFetchSetor(Long id);

    List<Contato> findAllLeftJoinFetchSetorAndUnidadeAdministrativa();
    
}
