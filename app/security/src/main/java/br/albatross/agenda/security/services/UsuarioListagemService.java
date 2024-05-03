package br.albatross.agenda.security.services;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.security.daos.spi.UsersDao;
import br.albatross.agenda.security.models.DadosParaListagemDoUsuarioDto;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UsuarioListagemService {

	@Inject
	private UsersDao dao;

	public List<DadosParaListagemDoUsuarioDto> listar() {

		return dao
		        .findAll()
		        .stream()
		        .map(DadosParaListagemDoUsuarioDto::new)
		        .collect(toList());
	}

	public Optional<DadosParaListagemDoUsuarioDto> buscarPorId(int id) {

	    return dao
		        .findById(id)
		        .map(DadosParaListagemDoUsuarioDto::new);

	}

	public Optional<DadosParaListagemDoUsuarioDto> buscarPorUsername(String username) {

        return dao
                .findByUsername(username)
                .map(DadosParaListagemDoUsuarioDto::new);

	}

}
