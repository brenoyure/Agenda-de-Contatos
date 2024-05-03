package br.albatross.agenda.security.daos.impl;

import static br.albatross.agenda.security.models.User_.role;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.impl.DaoImpl;
import br.albatross.agenda.security.daos.spi.UsersDao;
import br.albatross.agenda.security.models.User;
import br.albatross.agenda.security.models.User_;

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

    @Override
    public Optional<User> findById(Integer id) {

        try {

            CriteriaBuilder criteriaBuilder    =  entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery  =  criteriaBuilder.createQuery(User.class);
            Root<User> user                    =  criteriaQuery.from(User.class); 

            user
                .fetch(role, JoinType.INNER);

            criteriaQuery
                .where(criteriaBuilder.equal(user.get(User_.id), id));

            return Optional
                    .of(entityManager.createQuery(criteriaQuery).getSingleResult());

        } catch (NoResultException e) { return Optional.empty(); }
    }

    @Override
    public Optional<User> findByUsername(String username) {

        try {

            CriteriaBuilder criteriaBuilder    =  entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery  =  criteriaBuilder.createQuery(User.class);
            Root<User> user                    =  criteriaQuery.from(User.class); 

            user
                .fetch(role, JoinType.INNER);

            criteriaQuery
                .where(criteriaBuilder.equal(user.get(User_.username), username));

            return Optional
                    .of(entityManager.createQuery(criteriaQuery).getSingleResult());

        } catch (NoResultException e) { return Optional.empty(); }

    }

    @Override
    public List<User> findAll() {

        CriteriaBuilder criteriaBuilder    =  entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery  =  criteriaBuilder.createQuery(User.class);
        Root<User> user                    =  criteriaQuery.from(User.class); 

        user
            .fetch(role, JoinType.INNER);

        return entityManager
                .createQuery(criteriaQuery)
                .getResultList();

    }	

	public boolean existsByUsername(String username) {
		try {

			return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM User u WHERE u.username = ?1)", Boolean.class)
					.setParameter(1, username)
					.getSingleResult();

		} catch (NoResultException e) {	return false; }

	}

	public boolean existsByUsernameAndNotById(String newUsername, int id) {
		try {

		    return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM User u WHERE u.id != ?1 AND u.username = ?2)", Boolean.class)
					.setParameter(1, id)
					.setParameter(2, newUsername)
					.getSingleResult();

		} catch (NoResultException e) {	return false; }

	}

}
