package br.albatross.agenda.dao.impl;

import static br.albatross.agenda.domain.models.Setor_.sigla;
import static br.albatross.agenda.domain.models.Setor_.unidadeAdministrativa;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.SetorDao;
import br.albatross.agenda.domain.models.Setor;
import br.albatross.agenda.domain.models.Setor_;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@RequestScoped
public class SetorDaoImpl extends DaoImpl<Setor, Integer> implements SetorDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean existsBySigla(String sigla) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.sigla = ?1)", Boolean.class)
						.setParameter(1, sigla)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public List<Setor> findAllInnerJoinFetchUnidadeAdministrativa() {

        CriteriaBuilder criteriaBuilder     =  entityManager.getCriteriaBuilder();
        CriteriaQuery<Setor> criteriaQuery  =  criteriaBuilder.createQuery(Setor.class);
        Root<Setor> setor                   =  criteriaQuery.from(Setor.class); 

		setor
		    .fetch(unidadeAdministrativa, JoinType.INNER);

		criteriaQuery
		    .orderBy(criteriaBuilder.asc(setor.get(sigla)));

		return entityManager
				.createQuery(criteriaQuery)
				.setHint(HINT_CACHEABLE, true)
				.getResultList();
	}	

	@Override
	public Optional<Setor> findByIdInnerJoinFetchUnidadeAdministrativa(Integer id) {

		try {

	        CriteriaBuilder criteriaBuilder     =  entityManager.getCriteriaBuilder();
	        CriteriaQuery<Setor> criteriaQuery  =  criteriaBuilder.createQuery(Setor.class);
	        Root<Setor> setor                   =  criteriaQuery.from(Setor.class); 

	        setor
	            .fetch(unidadeAdministrativa, JoinType.INNER);		

	        criteriaQuery.where(criteriaBuilder.equal(setor.get(Setor_.id), id));	        

			return Optional
					.ofNullable(entityManager
									.createQuery(criteriaQuery)
									.getSingleResult());

		} catch (NoResultException e) { return Optional.empty(); }

	}

	@Override
	public boolean existsByDescricao(String descricao) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.descricao = ?1)", Boolean.class)
						.setParameter(1, descricao)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public boolean existsBySiglaAndNotById(String sigla, Integer id) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.sigla = ?1 AND s.id != ?2)", Boolean.class)
						.setParameter(1, sigla)
						.setParameter(2, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public boolean existsByDescricaoAndNotById(String descricao, Integer id) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.descricao = ?1 AND s.id != ?2)", Boolean.class)
						.setParameter(1, descricao)
						.setParameter(2, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

    @Override
    public boolean hasContatos(Integer id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.setor.id = ?1)", Boolean.class)
                    .setParameter(1, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

}
