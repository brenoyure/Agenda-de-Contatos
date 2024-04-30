package br.albatross.agenda.security.daos.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.security.models.DadosParaListagemDoUsuarioDto;
import br.albatross.agenda.security.models.User;

public interface UsersDao {

    DadosParaListagemDoUsuarioDto persist(User user);
    DadosParaListagemDoUsuarioDto atualizar(User user);

    List<DadosParaListagemDoUsuarioDto> findAll();

    Optional<User> getReference(int id);
    Optional<DadosParaListagemDoUsuarioDto> findById(int id);
    Optional<DadosParaListagemDoUsuarioDto> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByUsernameForMerge(int id, String newUsername);

    void remover(User userReference);

}
