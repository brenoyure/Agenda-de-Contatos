package br.albatross.agenda.dao.impl;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.dao.spi.AndarDao;
import br.albatross.agenda.dto.spi.andar.DadosParaListagemDoAndar;
import br.albatross.agenda.models.Andar;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class AndarDaoImpl implements AndarDao {

	@PersistenceContext
	private EntityManager entityManager;
	
    @Override
    public List<DadosParaListagemDoAndar> findAll() {

        var cb   =  entityManager.getCriteriaBuilder();
        var cq   =  cb.createQuery(DadosParaListagemDoAndar.class);
        var root =  cq.from(Andar.class);

        cb
            .construct(DadosParaListagemDoAndar.class, root);

        return entityManager
                .createQuery(cq)
                .getResultList();

    }

    @Override
    public Optional<Andar> getReferenceById(Integer id) {

        return Optional.ofNullable(entityManager.getReference(Andar.class, id));

    }

}
