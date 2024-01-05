package br.albatross.agenda.infra.security.dao;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;

import br.albatross.agenda.infra.security.models.DadosBasicosDoUsuarioParaExibicaoDto;
import br.albatross.agenda.infra.security.models.Role;
import br.albatross.agenda.infra.security.models.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;

	public DadosBasicosDoUsuarioParaExibicaoDto criarNovoUsuario(Usuario usuario) {
		entityManager.persist(usuario);
		return new DadosBasicosDoUsuarioParaExibicaoDto(usuario.getUsername(), usuario.getRole().getNome());
	}

	public boolean existsByUsername(String username) {
		return entityManager
				.createQuery("SELECT EXISTS(SELECT u FROM Usuario u WHERE u.username = ?1)", Boolean.class)
				.setParameter(1, username)
				.setHint(HINT_CACHEABLE, true)
				.getSingleResult();
	}

	public List<Role> getRoles() {
		return entityManager
					.createQuery("SELECT r FROM Role r", Role.class)
					.setHint(HINT_CACHEABLE, true)
					.getResultList();
	}

	public Role getReferenceById(int id) {
		return entityManager.getReference(Role.class, id);
	}

	public int getRoleIdByName(String roleName) {
		return entityManager
				.createQuery("SELECT r.id FROM Role r WHERE r.nome = ?1", Integer.class)
				.setParameter(1, roleName)
				.setHint(HINT_CACHEABLE, true)
				.getSingleResult();
	}

}
