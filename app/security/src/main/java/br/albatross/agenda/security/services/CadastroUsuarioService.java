package br.albatross.agenda.security.services;

import br.albatross.agenda.security.daos.spi.RolesDao;
import br.albatross.agenda.security.daos.spi.UsersDao;
import br.albatross.agenda.security.exceptions.CadastroException;
import br.albatross.agenda.security.exceptions.ListagemException;
import br.albatross.agenda.security.models.DadosParaAtualizacaoDeUsuarioDto;
import br.albatross.agenda.security.models.DadosParaCadastroDeUsuarioDto;
import br.albatross.agenda.security.models.DadosParaListagemDoUsuarioDto;
import br.albatross.agenda.security.models.Role;
import br.albatross.agenda.security.models.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class CadastroUsuarioService {

	@Inject
	private UsersDao dao;

	@Inject
	private RolesDao rolesDao;

	@Inject
	private PasswordService passwordService;

	public DadosParaListagemDoUsuarioDto cadastrarNovoUsuario(@Valid DadosParaCadastroDeUsuarioDto dados) {

		if (dao.existsByUsername(dados.getUsername())) {
			throw new CadastroException("Usuário já existente", "Já existe um usuário com o nome informado, cadastro não realizado.");			
		}

		final String hashedPassword = passwordService.generateHashing(dados.getPassword());

		var novoUsuario = new User();
		novoUsuario.setUsername(dados.getUsername());
		novoUsuario.setPassword(hashedPassword);

		var role = rolesDao.getReferenceById(Role.class, dados.getRoleId()).orElseThrow(() -> new CadastroException("Role não Encontrada", "Role com o Id informado não encontrada, cadastro de usuário não realizado."));
		novoUsuario.setRole(role);

		return new DadosParaListagemDoUsuarioDto(dao.persist(novoUsuario));

	}

	public DadosParaListagemDoUsuarioDto atualizarCadastro(@Valid DadosParaAtualizacaoDeUsuarioDto dadosAtualizados) {

		if (dao.existsByUsernameAndNotById(dadosAtualizados.getUsername(), dadosAtualizados.getId())) {
			throw new CadastroException("Usuário já existente", "Já existe um usuário com o nome informado, atualização de cadastro não realizado.");
		}

		final String hashedPassword = passwordService.generateHashing(dadosAtualizados.getPassword());

		var usuarioAtualizado = new User();
		usuarioAtualizado.setId(dadosAtualizados.getId());
		usuarioAtualizado.setUsername(dadosAtualizados.getUsername());
		usuarioAtualizado.setPassword(hashedPassword);

		var role = rolesDao.getReferenceById(Role.class, dadosAtualizados.getRoleId()).orElseThrow(() -> new CadastroException("Role não Encontrada", "Role com o Id informado não encontrada, cadastro de usuário não realizado."));
		usuarioAtualizado.setRole(role);

		return new DadosParaListagemDoUsuarioDto(dao.merge(usuarioAtualizado));

	}

	public void excluirPorId(int id) {

		if (id == 1) {
			throw new CadastroException("Impossível Excluir Usuário", "Exclusão do Usuário Administrador não permitida.");
		}

		dao
			.getReferenceById(User.class, id)
			.ifPresentOrElse(dao::removeByReference, () -> {
				throw new ListagemException("Usuário Não Encontrado", "Usuário com o ID informado não encontrado, exclusão não realizada");

			});

	}

}
