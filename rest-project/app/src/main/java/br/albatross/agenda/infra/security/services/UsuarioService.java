package br.albatross.agenda.infra.security.services;

import br.albatross.agenda.domain.dao.UsuarioDao;
import br.albatross.agenda.domain.models.usuario.DadosBasicosDoUsuarioParaExibicaoDto;
import br.albatross.agenda.domain.models.usuario.DadosParaCriacaoDeUsuarioDto;
import br.albatross.agenda.domain.models.usuario.Role;
import br.albatross.agenda.domain.models.usuario.Usuario;
import br.albatross.agenda.infra.security.exceptions.UsuarioExistenteException;
import br.albatross.agenda.infra.security.services.password.PasswordService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class UsuarioService {

	@Inject
	private UsuarioDao dao;

	@Inject
	private PasswordService passwordService;

	@Transactional
	public DadosBasicosDoUsuarioParaExibicaoDto criarNovoUsuario(DadosParaCriacaoDeUsuarioDto dados) {
		if (verificarSeUsuarioExistePorNome(dados.nomeDoUsuario())) {
			throw new UsuarioExistenteException();
		}
		Usuario usuario = new Usuario();
		usuario.setUsername(dados.nomeDoUsuario());
		usuario.setPassword(passwordService.generateHashing(dados.senha()));
		int userRoleId = dao.getRoleIdByName("USER");
		Role userRoleReference = dao.getReferenceById(userRoleId);
		usuario.setRole(userRoleReference);
		return dao.criarNovoUsuario(usuario);
	}

	public boolean verificarSeUsuarioExistePorNome(String nomeDoUsuario) {
		return dao.existsByUsername(nomeDoUsuario);
	}

}
