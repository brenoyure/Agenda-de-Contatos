package br.albatross.agenda.dao;

import java.util.List;

import br.albatross.agenda.models.Contato;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class ContatoDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Contato> listar() {
		return entityManager
				.createQuery("SELECT c FROM Contato c", Contato.class)
				.getResultList();
	}

}
