package br.albatross.agenda.dao;

import java.util.List;

import br.albatross.agenda.models.Setor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class SetorDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Setor> listar() {
		return entityManager
				.createQuery("SELECT s FROM Setor s", Setor.class)
				.getResultList();
	}

}
