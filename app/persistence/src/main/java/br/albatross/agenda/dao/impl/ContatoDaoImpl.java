package br.albatross.agenda.dao.impl;

import static br.albatross.agenda.domain.models.Contato_.andar;
import static br.albatross.agenda.domain.models.Contato_.setor;
import static br.albatross.agenda.domain.models.Setor_.unidadeAdministrativa;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.ContatoDao;
import br.albatross.agenda.domain.models.Contato;
import br.albatross.agenda.domain.models.Contato_;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@RequestScoped
public class ContatoDaoImpl extends DaoImpl<Contato, Long> implements ContatoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Contato> findByIdLeftJoinFetchSetorAndAndar(Long id) {

        try {

            CriteriaBuilder criteriaBuilder       =  entityManager.getCriteriaBuilder();
            CriteriaQuery<Contato> criteriaQuery  =  criteriaBuilder.createQuery(Contato.class);
            Root<Contato> contato                 =  criteriaQuery.from(Contato.class); 

            contato
                .fetch(setor, JoinType.LEFT);

            contato
                .fetch(andar, JoinType.LEFT);

            criteriaQuery
                .where(criteriaBuilder.equal(contato.get(Contato_.id), id));

            return Optional.of(
                    entityManager.createQuery(criteriaQuery).getSingleResult());

        } catch (NoResultException e) { return Optional.empty(); }

    }

    @Override
    public List<Contato> findAllLeftJoinFetchSetorAndUnidadeAdministrativaAndAndar() {

        CriteriaBuilder criteriaBuilder       = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contato> criteriaQuery  = criteriaBuilder.createQuery(Contato.class);
        Root<Contato> contato                 = criteriaQuery.from(Contato.class); 

        contato
            .fetch(setor, JoinType.LEFT)
            .fetch(unidadeAdministrativa, JoinType.LEFT);

        contato
            .fetch(andar, JoinType.LEFT);

        return entityManager
                .createQuery(criteriaQuery)
                .setHint(HINT_CACHEABLE, true)
                .getResultList();
    } 

    @Override
    public boolean existsByNomeAndNotById(String nome, Long id) {
        return entityManager
                .createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.nome = ?1 AND c.id != ?2)", Boolean.class)
                .setParameter(1, nome)
                .setParameter(2, id)
                .getSingleResult();
    }

    @Override
    public boolean existsByNome(String nome) {
        return entityManager
                .createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.nome = ?1)", Boolean.class)
                .setParameter(1, nome)
                .getSingleResult();
    }

}
