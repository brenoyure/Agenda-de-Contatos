package br.albatross.agenda.domain.dao;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;

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

	public boolean existeContatoComEsteSetor(int setorId) {
		return entityManager
				.createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.setor.id = ?1)", Boolean.class)
				.setParameter(1, setorId)
				.getSingleResult();		
	}

	public Setor getReferenceById(int id) {
		return entityManager.getReference(Setor.class, id);
	}

	public boolean existsByName(String setor) {
		return entityManager
				.createQuery("SELECT EXISTS(SELECT s FROM Setor s WHERE s.sigla = ?1)", Boolean.class)
				.setParameter(1, setor)
				.getSingleResult();
	}

	public List<DadosParaListagemDeSetorDto> listar(int pagina, byte resultadosPorPagina) {
		return entityManager
				.createQuery("SELECT new br.albatross.agenda.domain.models.setor.DadosParaListagemDeSetorDto(s.id, s.sigla) FROM Setor s", DadosParaListagemDeSetorDto.class)
				.setFirstResult((pagina * resultadosPorPagina) - resultadosPorPagina)
				.setMaxResults(resultadosPorPagina)
				.setHint(HINT_CACHEABLE, true)
				.getResultList();
	}

	public List<DadosParaListagemDeSetorDto> findAll() {
		return entityManager
				.createQuery("SELECT new br.albatross.agenda.domain.models.setor.DadosParaListagemDeSetorDto(s.id, s.sigla) FROM Setor s ORDER BY s.sigla", DadosParaListagemDeSetorDto.class)
				.setHint(HINT_CACHEABLE, true)
				.getResultList();
	}	

	public long getTotal() {
		return entityManager
				.createQuery("SELECT COUNT(s) FROM Setor s", Long.class)
				.setHint(HINT_CACHEABLE, true)
				.getSingleResult();
	}

	public Setor atualizar(Setor setor) {
		return entityManager.merge(setor);
	}

	public void excluir(int id) {
		entityManager.remove(getReferenceById(id));
	}

}
