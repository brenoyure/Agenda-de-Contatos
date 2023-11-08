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

	public List<Setor> listar() {
		return entityManager
				.createQuery("SELECT s FROM Setor s", Setor.class)
				.getResultList();
	}

}
