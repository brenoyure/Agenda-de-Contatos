package br.albatross.agenda.dao.impl;

import static br.albatross.agenda.models.Contato_.andar;
import static br.albatross.agenda.models.Contato_.setor;
import static br.albatross.agenda.models.Setor_.unidadeAdministrativa;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.ContatoDao;
import br.albatross.agenda.dto.impl.contato.DadosParaListagemDeContatoDto;
import br.albatross.agenda.dto.spi.contato.DadosParaListagemDeContato;
import br.albatross.agenda.models.Contato;
import br.albatross.agenda.models.Contato_;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@RequestScoped
public class ContatoDaoImpl implements ContatoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DadosParaListagemDeContato persist(Contato contato) {
        entityManager.persist(contato);
        return new DadosParaListagemDeContatoDto(contato);
    }

    @Override
    public DadosParaListagemDeContato merge(Contato contato) {
        contato = entityManager.merge(contato);
        return new DadosParaListagemDeContatoDto(contato);
    }

    @Override
    public Optional<Contato> getReferenceById(Long id) {
        try {

            return Optional.of(entityManager.getReference(Contato.class, id));

        } catch (NoResultException e) { return Optional.empty(); }
        
    }

    @Override
    public boolean existsByNomeAndNotById(String nome, Long id) {
        return entityManager
                .createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.nome = ?1 AND c.id != ?2)", Boolean.class)
                .setParameter(1, nome)
                .setParameter(2, id)
                .setHint(HINT_CACHEABLE, true)
                .getSingleResult();
    }

    @Override
    public boolean existsByNome(String nome) {
        return entityManager
                .createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.nome = ?1)", Boolean.class)
                .setParameter(1, nome)
                .setHint(HINT_CACHEABLE, true)
                .getSingleResult();
    }

    @Override
    public boolean existsById(Long id) {
        return entityManager
                .createQuery("SELECT EXISTS (SELECT c FROM Contato c WHERE c.id = ?1)", Boolean.class)
                .setParameter(1, id)
                .setHint(HINT_CACHEABLE, true)
                .getSingleResult();
    }

    @Override
    public List<DadosParaListagemDeContato> findAll() {

        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(DadosParaListagemDeContato.class);
        var root = cq.from(Contato.class);

        root
            .fetch(andar,                 JoinType.LEFT);

        root
            .fetch(setor,                 JoinType.LEFT)
            .fetch(unidadeAdministrativa, JoinType.LEFT);

        cq.select(cb.construct(DadosParaListagemDeContatoDto.class, root));

        return entityManager
                .createQuery(cq)
                .setHint(HINT_CACHEABLE, true)
                .getResultList();

    }

    @Override
    public Optional<DadosParaListagemDeContato> findById(Long id) {
        try {

            var cb = entityManager.getCriteriaBuilder();
            var cq = cb.createQuery(DadosParaListagemDeContato.class);
            var root = cq.from(Contato.class);

            root
                .fetch(andar,                 JoinType.LEFT);

            root
                .fetch(setor,                 JoinType.LEFT)
                .fetch(unidadeAdministrativa, JoinType.LEFT);

            cq
                .select(cb.construct(DadosParaListagemDeContatoDto.class, root))
                .where(cb.equal(root.get(Contato_.id), id));

            return Optional.of(
                    entityManager
                        .createQuery(cq)
                        .setHint(HINT_CACHEABLE, true)
                        .getSingleResult());

        } catch (NoResultException e) { return Optional.empty(); }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Contato.class, id));
    }

}
