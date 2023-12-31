package br.albatross.agenda.dao;

import java.util.List;

import br.albatross.agenda.models.Setor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class SetorDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Setor setor) {
		entityManager.persist(setor);
	}

	public void atualizar(Setor setor) {
		entityManager.merge(setor);
	}

	public boolean existePorSigla(String sigla) {
		return entityManager
				.createQuery("SELECT EXISTS (SELECT s FROM Setor s WHERE s.sigla = ?1)", Boolean.class)
				.setParameter(1, sigla)
				.getSingleResult();
	}

	public boolean existePorId(Short setorId) {
		return entityManager
				.createQuery("SELECT EXISTS (SELECT s FROM Setor s WHERE s.id = ?1)", Boolean.class)
				.setParameter(1, setorId)
				.getSingleResult();
	}

	public List<Setor> listar() {
		return entityManager
				.createQuery("SELECT s FROM Setor s ORDER BY s.sigla ASC", Setor.class)
				.getResultList();
	}

	public Setor carregar(Short setorId) {
		return entityManager.find(Setor.class, setorId);
	}

	public void excluir(Setor setor) {
		entityManager.remove(entityManager.getReference(Setor.class, setor.getId()));
	}

}
