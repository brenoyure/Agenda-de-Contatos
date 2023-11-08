package br.albatross.agenda.dao;

import java.util.List;

import br.albatross.agenda.models.UnidadeAdministrativa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class UnidadeAdministrativaDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void persist(UnidadeAdministrativa unidadeAdministrativa) {
		entityManager.persist(unidadeAdministrativa);
	}

	public List<UnidadeAdministrativa> listar() {
		return entityManager
				.createQuery("SELECT u FROM UnidadeAdministrativa u", UnidadeAdministrativa.class)
				.getResultList();
	}

}
