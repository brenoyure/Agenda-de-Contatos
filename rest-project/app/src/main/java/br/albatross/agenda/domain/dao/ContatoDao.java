package br.albatross.agenda.domain.dao;

import static br.albatross.agenda.domain.models.contato.Contato_.andar;
import static br.albatross.agenda.domain.models.contato.Contato_.nome;
import static br.albatross.agenda.domain.models.contato.Contato_.numero;
import static br.albatross.agenda.domain.models.contato.Contato_.setor;

import java.util.List;
import java.util.Optional;

import org.hibernate.jpa.AvailableHints;

import br.albatross.agenda.domain.models.contato.Contato;
import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaPesquisaDeContatosDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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

	public List<DadosParaListagemDeContatoDto> listarTodos(DadosParaPesquisaDeContatosDto dados) {
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createQuery(DadosParaListagemDeContatoDto.class);
		var contato = cq.from(Contato.class);

		cq.select(cb.construct(DadosParaListagemDeContatoDto.class, contato));

		var andPredicate = fetchAndPredicate(cb, contato, dados);
		cq.where(andPredicate);

		cq.orderBy(cb.asc(contato.get(andar)));

		return entityManager
				.createQuery(cq)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	public List<DadosParaListagemDeContatoDto> listar(int pagina, byte resultadosPorPagina) {
		return entityManager
				.createQuery("SELECT new br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto(c.id, c.nome, c.numero, c.setor, c.andar) FROM Contato c", DadosParaListagemDeContatoDto.class)
				.setFirstResult((pagina * resultadosPorPagina) - resultadosPorPagina)
				.setMaxResults(resultadosPorPagina)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	public List<DadosParaListagemDeContatoDto> listar(int pagina, byte resultadosPorPagina, DadosParaPesquisaDeContatosDto dadosParaPesquisa) {
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createQuery(DadosParaListagemDeContatoDto.class);
		var contato = cq.from(Contato.class);

		cq.select(cb.construct(DadosParaListagemDeContatoDto.class, contato));

		var andPredicate = fetchAndPredicate(cb, contato, dadosParaPesquisa);
		cq.where(andPredicate);

		return entityManager
				.createQuery(cq)
				.setFirstResult((pagina * resultadosPorPagina) - resultadosPorPagina)
				.setMaxResults(resultadosPorPagina)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	public Optional<DadosParaListagemDeContatoDto> buscarPorId(short contatoId) {
		return Optional.ofNullable(entityManager
					.createQuery("SELECT new br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto(c.id, c.nome, c.numero, c.setor, c.andar) FROM Contato c WHERE c.id = ?1", DadosParaListagemDeContatoDto.class)
					.setParameter(1, contatoId)
					.setHint(AvailableHints.HINT_CACHEABLE, true)
					.getSingleResult());
	}

	public void excluir(short id) {
		entityManager.remove(entityManager.getReference(Contato.class, id));
	}

	private Predicate fetchAndPredicate(CriteriaBuilder cb, Root<Contato> contato, DadosParaPesquisaDeContatosDto dados) {
		Predicate and = cb.and();

		if (dados.nome() != null) {
			and = cb.and(and, cb.equal(contato.get(nome), dados.nome()));
		}

		if (dados.setor() != null) {
			and = cb.and(and, cb.like(contato.get(setor), dados.setor().concat("%")));
		}

		if (dados.andar() != null) {
			and = cb.and(and, cb.equal(contato.get(andar), dados.andar()));
		}

		if (dados.ramal() != null) {
			and = cb.and(and, cb.equal(contato.get(numero), dados.ramal()));
		}

		return and;

	}

}
