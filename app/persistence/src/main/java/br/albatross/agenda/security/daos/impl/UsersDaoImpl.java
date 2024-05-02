package br.albatross.agenda.security.daos.impl;

import java.util.Optional;

import org.hibernate.jpa.AvailableHints;

import br.albatross.agenda.dao.impl.DaoImpl;
import br.albatross.agenda.security.daos.spi.UsersDao;
import br.albatross.agenda.security.models.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@RequestScoped
public class UsersDaoImpl extends DaoImpl<User, Integer> implements UsersDao {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean existsByUsername(String username) {
		try {
			return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM User u WHERE u.username = ?1)", Boolean.class)
					.setParameter(1, username)
					.setHint(AvailableHints.HINT_CACHEABLE, true)
					.getSingleResult();
		} catch (NoResultException e) {	return false; }

	}

	public boolean existsByUsernameAndNotById(String newUsername, int id) {
		try {
			return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM User u WHERE u.id != ?1 AND u.username = ?2)", Boolean.class)
					.setParameter(1, id)
					.setParameter(2, newUsername)
					.setHint(AvailableHints.HINT_CACHEABLE, true)
					.getSingleResult();
		} catch (NoResultException e) {	return false; }

	}

	@Override
	public Optional<User> findByUsername(String username) {

		try {

            CriteriaBuilder criteriaBuilder    =  entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery  =  criteriaBuilder.createQuery(User.class);
            Root<User> user                    =  criteriaQuery.from(User.class); 

            user
                .fetch("role", JoinType.INNER);

            criteriaQuery
                .where(criteriaBuilder.equal(user.get("username"), username));

            return Optional
                    .of(entityManager.createQuery(criteriaQuery).getSingleResult());

		} catch (NoResultException e) {	return Optional.empty(); }

	}

}
