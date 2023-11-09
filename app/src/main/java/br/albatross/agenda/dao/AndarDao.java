package br.albatross.agenda.dao;

import java.util.List;

import br.albatross.agenda.models.Andar;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class AndarDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Andar> listar() {
		return entityManager
				.createQuery("SELECT a FROM Andar a", Andar.class)
				.getResultList();
	}

}
