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

	public void persist(Contato contato) {
		entityManager.persist(contato);
	}

	public void atualizar(Contato contato) {
		entityManager.merge(contato);
	}

	public boolean existePorNome(String nome) {
		return entityManager
				.createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.nome = ?1)", Boolean.class)
				.setParameter(1, nome)
				.getSingleResult();
	}
	
	public boolean existePorId(Short contatoId) {
		return entityManager
				.createQuery("SELECT EXISTS (SELECT c FROM Contato c WHERE c.id = ?1)", Boolean.class)
				.setParameter(1, contatoId)
				.getSingleResult();
	}

	public List<Contato> listar() {
		return entityManager
				.createQuery("SELECT c FROM Contato c JOIN FETCH c.andar JOIN FETCH c.setor s JOIN FETCH s.unidadeAdministrativa LEFT JOIN c.andar", Contato.class)
				.getResultList();
	}

	public Contato buscarPorId(Number contatoId) {
		return entityManager.find(Contato.class, contatoId);
	}

	public void excluir(Contato contato) {
		entityManager.remove(entityManager.getReference(Contato.class, contato.getId()));
	}



}
