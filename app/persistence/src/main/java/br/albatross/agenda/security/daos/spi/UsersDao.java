package br.albatross.agenda.security.daos.spi;

import java.util.Optional;

import br.albatross.agenda.dao.spi.Dao;
import br.albatross.agenda.security.models.User;

public interface UsersDao extends Dao<User, Integer> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByUsernameAndNotById(String username, int id);

}
