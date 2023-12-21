package br.albatross.agenda.domain.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.jpa.AvailableHints;

import br.albatross.agenda.domain.models.contato.Contato;
import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class ContatoDao {

	@PersistenceContext
	private EntityManager entityManager;

	public DadosParaListagemDeContatoDto persist(Contato contato) {
		entityManager.persist(contato);
		return new DadosParaListagemDeContatoDto(contato);
	}

	public Contato atualizar(Contato contato) {
		return entityManager.merge(contato);
	}

	public boolean existePorNome(String nome) {
		return entityManager
				.createQuery("SELECT EXISTS(SELECT c FROM Contato c WHERE c.nome = ?1)", Boolean.class)
				.setParameter(1, nome)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getSingleResult();
	}
	
	public boolean existePorId(Short contatoId) {
		return entityManager
				.createQuery("SELECT EXISTS (SELECT c FROM Contato c WHERE c.id = ?1)", Boolean.class)
				.setParameter(1, contatoId)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getSingleResult();
	}

	public long getTotal() {
		return entityManager
				.createQuery("SELECT COUNT (c) FROM Contato c", Long.class)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getSingleResult();
	}

	public List<DadosParaListagemDeContatoDto> listar(int pagina, byte resultadosPorPagina) {
		return entityManager
				.createQuery("SELECT new br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto(c.id, c.nome, c.numero, c.setor, c.andar) FROM Contato c", DadosParaListagemDeContatoDto.class)
				.setFirstResult((pagina * resultadosPorPagina) - resultadosPorPagina)
				.setMaxResults(resultadosPorPagina)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();
	}
	
	public Optional<DadosParaListagemDeContatoDto> buscarPorId(short contatoId) {
		try {
			return Optional.ofNullable(entityManager
					.createQuery("SELECT new br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto(c.id, c.nome, c.numero, c.setor, c.andar) FROM Contato c WHERE c.id = ?1", DadosParaListagemDeContatoDto.class)
					.setParameter(1, contatoId)
					.setHint(AvailableHints.HINT_CACHEABLE, true)
					.getSingleResult());
		} catch (NoResultException e) { return Optional.empty(); }
	}

	public void excluir(short id) {
		entityManager.remove(entityManager.getReference(Contato.class, id));
	}

}
