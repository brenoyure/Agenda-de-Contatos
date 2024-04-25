package br.albatross.agenda.security.daos;

import java.util.List;
import java.util.Optional;

import org.hibernate.jpa.AvailableHints;

import br.albatross.agenda.security.models.Role;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class RolesDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Optional<Role> getReferenceById(Integer id) {

		return Optional.ofNullable(entityManager.getReference(Role.class, id));

	}

	public List<Role> getRoles() {
		return entityManager
				.createQuery("SELECT r FROM Role r", Role.class)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();
	}

}
