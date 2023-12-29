package br.albatross.agenda.domain.services;

import br.albatross.agenda.domain.dao.UsuarioDao;
import br.albatross.agenda.domain.models.usuario.DadosBasicosDoUsuarioParaExibicaoDto;
import br.albatross.agenda.domain.models.usuario.DadosParaCriacaoDeUsuarioDto;
import br.albatross.agenda.domain.models.usuario.Role;
import br.albatross.agenda.domain.models.usuario.Usuario;
import br.albatross.agenda.infra.security.password.PasswordService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioService {

	@Inject
	private UsuarioDao dao;

	@Inject
	private PasswordService passwordService;

	@Transactional
	public DadosBasicosDoUsuarioParaExibicaoDto criarNovoUsuario(DadosParaCriacaoDeUsuarioDto dados) {
		Usuario usuario = new Usuario();
		usuario.setUsername(dados.nomeDoUsuario());
		usuario.setPassword(passwordService.generateHashing(dados.senha()));
		int userRoleId = dao.getRoleIdByName("USER");
		Role userRoleReference = dao.getReferenceById(userRoleId);
		usuario.setRole(userRoleReference);
		return dao.criarNovoUsuario(usuario);
	}

}
