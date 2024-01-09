package br.albatross.agenda.domain.dao;

import static br.albatross.agenda.domain.models.contato.Contato_.andar;
import static br.albatross.agenda.domain.models.contato.Contato_.nome;
import static br.albatross.agenda.domain.models.contato.Contato_.numero;
import static br.albatross.agenda.domain.models.contato.Contato_.setor;
import static br.albatross.agenda.domain.models.setor.Setor_.sigla;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;
import java.util.Optional;

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
				.setHint(HINT_CACHEABLE, true)
				.getSingleResult();
	}

	public boolean existePorId(Short contatoId) {
		return entityManager
				.createQuery("SELECT EXISTS (SELECT c FROM Contato c WHERE c.id = ?1)", Boolean.class)
				.setParameter(1, contatoId)
				.setHint(HINT_CACHEABLE, true)
				.getSingleResult();
	}

	public long getTotal() {
		return entityManager
				.createQuery("SELECT COUNT (c) FROM Contato c", Long.class)
				.setHint(HINT_CACHEABLE, true)
				.getSingleResult();
	}

	public List<DadosParaListagemDeContatoDto> listarTodos() {
		return entityManager
				.createQuery("SELECT new br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto(c) FROM Contato c ORDER BY c.andar, c.setor.sigla ASC", DadosParaListagemDeContatoDto.class)
				.setHint(HINT_CACHEABLE, true)
				.getResultList();
	}

	public List<DadosParaListagemDeContatoDto> listarTodos(DadosParaPesquisaDeContatosDto dados) {
		var cb      =  entityManager.getCriteriaBuilder();
		var cq      =  cb.createQuery(DadosParaListagemDeContatoDto.class);
		var contato =  cq.from(Contato.class);

		cq.select(cb.construct(DadosParaListagemDeContatoDto.class, contato));
		cq.where(fetchAndPredicate(cb, contato, dados));

		cq.orderBy(cb.asc(contato.get(andar)));
		cq.orderBy(cb.asc(contato.get(setor).get(sigla)));

		return entityManager
				.createQuery(cq)
				.setHint(HINT_CACHEABLE, true)
				.getResultList();
	}

	public List<DadosParaListagemDeContatoDto> listar(int pagina, byte resultadosPorPagina) {
		return entityManager
				.createQuery("SELECT new br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto(c.id, c.nome, c.numero, c.setor.sigla, c.andar) FROM Contato c", DadosParaListagemDeContatoDto.class)
				.setFirstResult((pagina * resultadosPorPagina) - resultadosPorPagina)
				.setMaxResults(resultadosPorPagina)
				.setHint(HINT_CACHEABLE, true)
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
				.setHint(HINT_CACHEABLE, true)
				.getResultList();
	}

	public Optional<DadosParaListagemDeContatoDto> buscarPorId(short contatoId) {
		return Optional.ofNullable(entityManager
					.createQuery("SELECT new br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto(c.id, c.nome, c.numero, c.setor.sigla, c.andar) FROM Contato c WHERE c.id = ?1", DadosParaListagemDeContatoDto.class)
					.setParameter(1, contatoId)
					.setHint(HINT_CACHEABLE, true)
					.getSingleResult());
	}

	public void excluir(short id) {
		entityManager.remove(entityManager.getReference(Contato.class, id));
	}

	//TODO Pesquisa Dinâmica ainda Lançando Exceptions 
	private Predicate fetchAndPredicate(CriteriaBuilder cb, Root<Contato> contato, DadosParaPesquisaDeContatosDto dados) {
		Predicate and = cb.and();

		if (!dados.nome().isBlank()) {
			and = cb.and(and, cb.equal(contato.get(nome), dados.nome()));
		}

		if (!dados.setor().isBlank()) {
			and = cb.and(and, cb.equal(contato.get(setor).get(sigla), dados.setor()));
		}

		if (!dados.andar().isBlank()) {
			and = cb.and(and, cb.equal(contato.get(andar), dados.andar()));
		}

		if (!dados.ramal().isBlank()) {
			and = cb.and(and, cb.equal(contato.get(numero), dados.ramal()));
		}

		return and;

	}

}
