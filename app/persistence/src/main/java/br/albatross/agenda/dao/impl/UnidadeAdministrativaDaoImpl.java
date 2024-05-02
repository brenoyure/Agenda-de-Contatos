package br.albatross.agenda.dao.impl;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import br.albatross.agenda.dao.spi.UnidadeAdministrativaDao;
import br.albatross.agenda.domain.models.UnidadeAdministrativa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class UnidadeAdministrativaDaoImpl extends DaoImpl<UnidadeAdministrativa, Integer> implements UnidadeAdministrativaDao {

    @PersistenceContext
    private EntityManager entityManager;

	public boolean existsBySigla(String sigla) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.sigla = ?1)", Boolean.class)
						.setParameter(1, sigla)
						.setHint(HINT_CACHEABLE, true)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}	

	@Override
	public boolean existsByDescricao(String descricao) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.descricao = ?1)", Boolean.class)
						.setParameter(1, descricao)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public boolean existsBySiglaAndNotById(String sigla, Integer id) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.sigla = ?1 AND u.id != ?2)", Boolean.class)
						.setParameter(1, sigla)
						.setParameter(2, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public boolean existsByDescricaoAndNotById(String descricao, Integer id) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.descricao = ?1 AND u.id != ?2)", Boolean.class)
						.setParameter(1, descricao)
						.setParameter(2, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public boolean hasSetores(Integer id) {
		try {

			return entityManager
					.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.unidadeAdministrativa.id = ?1)", Boolean.class)
					.setParameter(1, id)
					.getSingleResult();

		} catch (NoResultException e) { return false; }

	}

}
