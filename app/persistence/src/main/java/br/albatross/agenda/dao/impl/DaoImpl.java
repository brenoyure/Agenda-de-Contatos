package br.albatross.agenda.dao.impl;

import static java.lang.String.format;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.Dao;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Dependent
public class DaoImpl<T, K> implements Dao<T, K> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public T persist(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    public T merge(T t) {
        return entityManager.merge(t);
    }

    @Override
    public boolean existsById(Class<T> entityClass, K id) {
        try {
            final String jpql = format("SELECT EXISTS(SELECT t FROM %s t WHERE t.id = ?1)", entityClass.getSimpleName(), id);

            return entityManager
                    .createQuery(jpql, Boolean.class)
                    .setParameter(1, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        
        var cb   =  entityManager.getCriteriaBuilder();
        var cq   =  cb.createQuery(entityClass);
                    cq.from(entityClass);

        return entityManager
                .createQuery(cq)
                .getResultList();

    }

    @Override
    public Optional<T> findById(Class<T> entityClass, K id) {

        return Optional.ofNullable(entityManager.find(entityClass, id));

    }

    @Override
    public Optional<T> getReferenceById(Class<T> entityClass, K id) {

        return Optional.ofNullable(entityManager.getReference(entityClass, id));

    }

    @Override
    public void removeByReference(T t) {

        entityManager.remove(t);

    }

}