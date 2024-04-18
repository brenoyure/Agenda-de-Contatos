package br.albatross.agenda.dao.impl;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.AndarDao;
import br.albatross.agenda.dto.impl.andar.DadosParaListagemDoAndarDto;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.models.Andar;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class AndarDaoImpl implements AndarDao {

	@PersistenceContext
	private EntityManager entityManager;

    @Override
    public DadosParaListagemDoAndar persist(Andar andar) {

        entityManager.persist(andar);
        return new DadosParaListagemDoAndarDto(andar);

    }

    @Override
    public DadosParaListagemDoAndar merge(Andar andar) {

        andar = entityManager.merge(andar);
        return new DadosParaListagemDoAndarDto(andar);

    }	

    @Override
    public List<DadosParaListagemDoAndar> findAll() {

        var cb   =  entityManager.getCriteriaBuilder();
        var cq   =  cb.createQuery(DadosParaListagemDoAndar.class);
        var root =  cq.from(Andar.class);

        cq.select(cb.construct(DadosParaListagemDoAndarDto.class, root));

        return entityManager
                .createQuery(cq)
                .setHint(HINT_CACHEABLE, true)
                .getResultList();

    }

    @Override
    public Optional<Andar> getReferenceById(Integer id) {

        return Optional.ofNullable(entityManager.getReference(Andar.class, id));

    }

    @Override
    public Optional<DadosParaListagemDoAndar> findById(Integer id) {
        try {

            var cb   =  entityManager.getCriteriaBuilder();
            var cq   =  cb.createQuery(DadosParaListagemDoAndar.class);
            var root =  cq.from(Andar.class);

            cq
                .select(cb.construct(DadosParaListagemDoAndarDto.class, root))
                .where(cb.equal(root.get("id"), id));

            return Optional.of(entityManager.createQuery(cq).getSingleResult());

        } catch (NoResultException e) { return Optional.empty(); }

    }

    @Override
    public boolean existsById(Integer id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT a FROM Andar a WHERE a.id = ?1)", Boolean.class)
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
    public boolean existsByNome(Integer id, String nome) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT a FROM Andar a WHERE a.id = ?1 AND a.nome = ?2)", Boolean.class)
                    .setParameter(1, id)
                    .setParameter(2, nome)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

    @Override
    public void delete(Integer id) {

        entityManager.remove(entityManager.getReference(Andar.class, id));

    }

    @Override
    public boolean hasContatos(Integer id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.andar.id = ?1)", Boolean.class)
                    .setParameter(1, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }

    }

}
