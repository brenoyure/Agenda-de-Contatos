package br.albatross.agenda.security.daos.spi;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.security.models.DadosParaListagemDaRoleDto;
import br.albatross.agenda.security.models.Role;

public interface RolesDao {

    Optional<Role> getReferenceById(int id);
    List<DadosParaListagemDaRoleDto> getAvailableRoles();

}
