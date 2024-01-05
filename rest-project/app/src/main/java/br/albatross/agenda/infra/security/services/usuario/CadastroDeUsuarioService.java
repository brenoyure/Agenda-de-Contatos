package br.albatross.agenda.infra.security.services.usuario;

import br.albatross.agenda.infra.security.dao.UsuarioDao;
import br.albatross.agenda.infra.security.exceptions.UsuarioExistenteException;
import br.albatross.agenda.infra.security.models.DadosBasicosDoUsuarioParaExibicaoDto;
import br.albatross.agenda.infra.security.models.DadosParaCriacaoDeUsuarioDto;
import br.albatross.agenda.infra.security.models.Usuario;
import br.albatross.agenda.infra.security.services.password.PasswordService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class CadastroDeUsuarioService {

	@Inject
	private UsuarioDao dao;

	@Inject
	private PasswordService passwordService;

	public DadosBasicosDoUsuarioParaExibicaoDto criarNovoUsuario(@Valid DadosParaCriacaoDeUsuarioDto dados) {
		if (dao.existsByUsername(dados.nomeDoUsuario())) {
			throw new UsuarioExistenteException("Já existe um usuário cadastrado com o nome informado");
		}
		return dao.criarNovoUsuario(
							new Usuario(dados.nomeDoUsuario(), 
										passwordService.generateHashing(dados.senha()),
										dao.getReferenceById(dao.getRoleIdByName("USER"))));
	}

}
