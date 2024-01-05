package br.albatross.agenda.domain.dao;

import br.albatross.agenda.domain.models.setor.DadosParaListagemDeSetorDto;
import br.albatross.agenda.domain.models.setor.Setor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class SetorDao {

	@PersistenceContext
	private EntityManager entityManager;

	public DadosParaListagemDeSetorDto persist(Setor setor) {
		entityManager.persist(setor);
		return new DadosParaListagemDeSetorDto(setor);
	}

	public Setor getReferenceById(int id) {
		return entityManager.getReference(Setor.class, id);
	}

}
