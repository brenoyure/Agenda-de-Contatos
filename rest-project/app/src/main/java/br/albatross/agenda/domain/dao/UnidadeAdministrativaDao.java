package br.albatross.agenda.domain.dao;

import java.util.List;

import br.albatross.agenda.domain.models.UnidadeAdministrativa;
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

	public void atualizar(UnidadeAdministrativa unidadeAdministrativa) {
		entityManager.merge(unidadeAdministrativa);
	}

	public boolean existePorId(Short unidadeAdministrativaId) {
		return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.id = ?1)", Boolean.class)
					.setParameter(1, unidadeAdministrativaId)
					.getSingleResult();
	}

	public boolean existePorSigla(String sigla) {
		return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.sigla = ?1)", Boolean.class)
					.setParameter(1, sigla)
					.getSingleResult();
	}	

	public List<UnidadeAdministrativa> listar() {
		return entityManager
				.createQuery("SELECT u FROM UnidadeAdministrativa u", UnidadeAdministrativa.class)
				.getResultList();
	}

	public UnidadeAdministrativa carregar(Short id) {
		return entityManager.find(UnidadeAdministrativa.class, id);
	}

	public void excluir (UnidadeAdministrativa unidadeAdministrativa) {
		entityManager.remove(entityManager.getReference(UnidadeAdministrativa.class, unidadeAdministrativa.getId()));
	}

}
