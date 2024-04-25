package br.albatross.agenda.security.services;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.security.daos.UsersDao;
import br.albatross.agenda.security.models.DadosParaListagemDoUsuarioDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UsuarioListagemService {

	@Inject
	private UsersDao dao;

	public List<DadosParaListagemDoUsuarioDto> listar() {
		return dao.findAll();
	}

	public Optional<DadosParaListagemDoUsuarioDto> buscarPorId(int id) {
		return dao.findById(id);
	}

	public Optional<DadosParaListagemDoUsuarioDto> buscarPorUsername(String username) {
		return dao.findByUsername(username);
	}

}
