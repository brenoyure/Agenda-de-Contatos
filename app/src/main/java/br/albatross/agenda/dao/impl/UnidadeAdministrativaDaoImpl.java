package br.albatross.agenda.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.UnidadeAdministrativaDao;
import br.albatross.agenda.dto.impl.unidades.DadosParaListagemDeUnidadeDto;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.models.entities.UnidadeAdministrativa;
import br.albatross.agenda.models.metamodels.UnidadeAdministrativa_;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class UnidadeAdministrativaDaoImpl implements UnidadeAdministrativaDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public DadosParaListagemDeUnidade persist(UnidadeAdministrativa unidadeAdministrativa) {
		entityManager.persist(unidadeAdministrativa);
		return new DadosParaListagemDeUnidadeDto(unidadeAdministrativa);
	}

	@Override
	public DadosParaListagemDeUnidade merge(UnidadeAdministrativa unidadeAdministrativa) {
		return new DadosParaListagemDeUnidadeDto(entityManager.merge(unidadeAdministrativa));
	}

	@Override
	public Optional<DadosParaListagemDeUnidade> findById(Serializable id) {
		var cb   =  entityManager.getCriteriaBuilder();
		var cq   =  cb.createQuery(DadosParaListagemDeUnidade.class);
		var root =  cq.from(UnidadeAdministrativa.class);

		cq.where(cb.equal(root.get(UnidadeAdministrativa_.id), id));
		cb.construct(DadosParaListagemDeUnidadeDto.class, root);

		return Optional
				.ofNullable(entityManager
								.createQuery(cq)
								.getSingleResult());

	}

    @Override
    public Optional<UnidadeAdministrativa> getReferenceById(Serializable id) {

        return Optional.ofNullable(entityManager.getReference(UnidadeAdministrativa.class, id));

    }	

	@Override
	public boolean existsById(Serializable unidadeAdministrativaId) {

		try {

			return entityManager
						.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.id = ?1)", Boolean.class)
						.setParameter(1, unidadeAdministrativaId)
						.getSingleResult();

		} catch (NoResultException e) { return false; }

	}

	@Override
	public boolean existsBySigla(String sigla) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.sigla = ?1)", Boolean.class)
						.setParameter(1, sigla)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}	

	@Override
	public List<DadosParaListagemDeUnidade> findAll() {
		var cb   =  entityManager.getCriteriaBuilder();
		var cq   =  cb.createQuery(DadosParaListagemDeUnidade.class);
		var root =  cq.from(UnidadeAdministrativa.class);

		cb.construct(DadosParaListagemDeUnidadeDto.class, root);
		
		return entityManager
				.createQuery(cq)
				.getResultList();

	}

	@Override
	public void delete (Serializable id) {
		entityManager.remove(entityManager.getReference(UnidadeAdministrativa.class, id));
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
	public boolean existsBySigla(Serializable id, String sigla) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.sigla = ?1 AND u.id != ?2)", Boolean.class)
						.setParameter(1, sigla)
						.setParameter(2, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public boolean existsByDescricao(Serializable id, String descricao) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT u FROM UnidadeAdministrativa u WHERE u.descricao = ?1 AND u.id != ?2)", Boolean.class)
						.setParameter(1, descricao)
						.setParameter(2, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public boolean hasSetores(Serializable id) {
		try {

			return entityManager
					.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.unidadeAdministrativa.id = ?1)", Boolean.class)
					.setParameter(1, id)
					.getSingleResult();

		} catch (NoResultException e) { return false; }

	}

}
