package br.albatross.agenda.dao.impl;

import static br.albatross.agenda.models.Setor_.unidadeAdministrativa;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.SetorDao;
import br.albatross.agenda.dto.impl.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.models.Setor;
import br.albatross.agenda.models.Setor_;
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

	    setor = entityManager.merge(setor);
		return new DadosParaListagemDeSetorDto(setor);

	}

	@Override
	public boolean existsById(Integer id) {
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
		    .fetch(unidadeAdministrativa, JoinType.INNER);

		cq.select(cb.construct(DadosParaListagemDeSetorDto.class, root));
		
		return entityManager
				.createQuery(cq)
				.getResultList();
	}

	@Override
	public Optional<DadosParaListagemDeSetor> findById(Integer id) {

		try {

			var cb   =  entityManager.getCriteriaBuilder();
			var cq   =  cb.createQuery(DadosParaListagemDeSetor.class);
			var root =  cq.from(Setor.class);

	        root
	            .fetch(unidadeAdministrativa, JoinType.INNER);		

	        cq.select(cb.construct(DadosParaListagemDeSetorDto.class, root));

	        cq.where(cb.equal(root.get(Setor_.id), id));	        

			return Optional
					.ofNullable(entityManager
									.createQuery(cq)
									.getSingleResult());

		} catch (NoResultException e) { return Optional.empty(); }

	}

    @Override
    public Optional<Setor> getReferenceById(Integer id) {

        return Optional.ofNullable(entityManager.getReference(Setor.class, id));

    }   	

	@Override
	public void delete(Integer id) {

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
	public boolean existsBySigla(Integer id, String sigla) {
		try {
			return entityManager
						.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.sigla = ?1 AND s.id != ?2)", Boolean.class)
						.setParameter(1, sigla)
						.setParameter(2, id)
						.getSingleResult();
		} catch (NoResultException e) { return false; }
	}

	@Override
	public boolean existsByDescricao(Integer id, String descricao) {
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
