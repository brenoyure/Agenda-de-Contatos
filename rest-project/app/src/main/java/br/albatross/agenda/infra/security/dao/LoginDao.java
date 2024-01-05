package br.albatross.agenda.infra.security.dao;

import static java.util.Optional.empty;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.Optional;

import br.albatross.agenda.infra.security.credentials.Credenciais;
import br.albatross.agenda.infra.security.models.DadosBasicosDoUsuarioParaExibicaoDto;
import br.albatross.agenda.infra.security.services.password.PasswordService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class LoginDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private PasswordService passwordService;

	public Optional<DadosBasicosDoUsuarioParaExibicaoDto> logar(Credenciais credenciais) {
		try {
			return Optional.of(entityManager
					.createQuery("SELECT new br.albatross.agenda.domain.models.usuario.DadosBasicosDoUsuarioParaExibicaoDto(u.username, u.role.nome) u FROM Usuario u WHERE u.username = ?1 AND u.password = ?2", DadosBasicosDoUsuarioParaExibicaoDto.class)
					.setParameter(1, credenciais.username())
					.setParameter(2, passwordService.generateHashing(credenciais.password()))
					.setHint(HINT_CACHEABLE, true)
					.getSingleResult());
		} catch (NoResultException e) { return empty(); }
	}

}
