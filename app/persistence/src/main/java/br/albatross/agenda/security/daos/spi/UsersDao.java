package br.albatross.agenda.security.daos.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.Dao;
import br.albatross.agenda.security.models.User;

public interface UsersDao extends Dao<User, Integer> {

    Optional<User> findById(Integer id);
    Optional<User> findByUsername(String username);

    List<User> findAll();

    boolean existsByUsername(String username);
    boolean existsByUsernameAndNotById(String username, int id);

}
