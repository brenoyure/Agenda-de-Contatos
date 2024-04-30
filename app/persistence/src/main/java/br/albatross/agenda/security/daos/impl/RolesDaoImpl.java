package br.albatross.agenda.security.daos.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.jpa.AvailableHints;

import br.albatross.agenda.security.daos.spi.RolesDao;
import br.albatross.agenda.security.models.DadosParaListagemDaRoleDto;
import br.albatross.agenda.security.models.Role;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class RolesDaoImpl implements RolesDao {

    @PersistenceContext(unitName = "agendadb")
	private EntityManager entityManager;

	@Override
	public Optional<Role> getReferenceById(int id) {

		return Optional.ofNullable(entityManager.getReference(Role.class, id));

	}

	@Override
	public List<DadosParaListagemDaRoleDto> getAvailableRoles() {

	    var cb   =  entityManager.getCriteriaBuilder();
	    var cq   =  cb.createQuery(DadosParaListagemDaRoleDto.class);
	    var root =  cq.from(Role.class);

	    cq.select(cb.construct(DadosParaListagemDaRoleDto.class, root));

        return entityManager
                .createQuery(cq)
                .setHint(AvailableHints.HINT_CACHEABLE, true)
                .getResultList();

	}

}
