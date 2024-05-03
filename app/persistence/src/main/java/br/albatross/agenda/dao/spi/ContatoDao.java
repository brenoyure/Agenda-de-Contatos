package br.albatross.agenda.dao.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.models.Contato;

public interface ContatoDao extends Dao<Contato, Long> {

    Optional<Contato> findByIdLeftJoinFetchSetorAndAndar(Long id);
    List<Contato> findAllLeftJoinFetchSetorAndUnidadeAdministrativaAndAndar();    

    boolean existsByNome(String nome);
    boolean existsByNomeAndNotById(String nome, Long id);
    
}
