package br.albatross.agenda.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.SetorDao;
import br.albatross.agenda.dto.impl.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.models.entities.Setor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@RequestScoped
public class SetorDaoImpl implements SetorDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public DadosParaListagemDeSetor persist(Setor setor) {
		entityManager.persist(setor);
		return new DadosParaListagemDeSetorDto(setor);
	}

	@Override
	public DadosParaListagemDeSetor merge(Setor setor) {
		return new DadosParaListagemDeSetorDto(entityManager.merge(setor));
	}

	@Override
	public boolean existsById(Serializable id) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.id = ?1)", Boolean.class)
						.setParameter(1, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

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
	public List<DadosParaListagemDeSetor> findAll() {

		var cb   =  entityManager.getCriteriaBuilder();
		var cq   =  cb.createQuery(DadosParaListagemDeSetor.class);
		var root =  cq.from(Setor.class);

		root
		    .fetch("unidadeAdministrativa", JoinType.INNER);

		cb.construct(DadosParaListagemDeSetorDto.class, root);
		
		return entityManager
				.createQuery(cq)
				.getResultList();
	}

	@Override
	public Optional<DadosParaListagemDeSetor> findById(Serializable id) {

		try {

			var cb   =  entityManager.getCriteriaBuilder();
			var cq   =  cb.createQuery(DadosParaListagemDeSetor.class);
			var root =  cq.from(Setor.class);

	        root
                .fetch("unidadeAdministrativa", JoinType.INNER);		

			cq.where(cb.equal(root.get("id"), id));
			cb.construct(DadosParaListagemDeSetorDto.class, root);

			return Optional
					.ofNullable(entityManager
									.createQuery(cq)
									.getSingleResult());

		} catch (NoResultException e) { return Optional.empty(); }

	}

    @Override
    public Optional<Setor> getReferenceById(Serializable id) {

        return Optional.ofNullable(entityManager.getReference(Setor.class, id));

    }   	

	@Override
	public void delete(Serializable id) {

		entityManager.remove(entityManager.getReference(Setor.class, id));

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
	public boolean existsBySigla(Serializable id, String sigla) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.sigla = ?1 AND s.id != ?2)", Boolean.class)
						.setParameter(1, sigla)
						.setParameter(2, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public boolean existsByDescricao(Serializable id, String descricao) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.descricao = ?1 AND s.id != ?2)", Boolean.class)
						.setParameter(1, descricao)
						.setParameter(2, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

    @Override
    public boolean hasContatos(Serializable id) {
        try {

            return entityManager
                    .createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.setor.id = ?1)", Boolean.class)
                    .setParameter(1, id)
                    .getSingleResult();

        } catch (NoResultException e) { return false; }
    }

}
