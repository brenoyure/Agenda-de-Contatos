package br.albatross.agenda.dao.impl;

import br.albatross.agenda.dao.spi.AndarDao;
import br.albatross.agenda.domain.models.Andar;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class AndarDaoImpl extends DaoImpl<Andar, Integer> implements AndarDao {

	@PersistenceContext
	private EntityManager entityManager;

    public boolean hasContatos(Integer id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.andar.id = ?1)", Boolean.class)
                    .setParameter(1, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

    @Override
    public boolean existsByNome(String nome) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT a FROM Andar a WHERE a.nome = ?1)", Boolean.class)
                    .setParameter(1, nome)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

    @Override
    public boolean existsByNomeAndNotById(String nome, Integer id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT a FROM Andar a WHERE a.nome = ?1 AND a.id != ?2)", Boolean.class)
                    .setParameter(1, nome)
                    .setParameter(2, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

}
