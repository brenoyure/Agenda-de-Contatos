package br.albatross.agenda.security.services;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.security.models.DadosParaAtualizacaoDeUsuarioDto;
import br.albatross.agenda.security.models.DadosParaCadastroDeUsuarioDto;
import br.albatross.agenda.security.models.DadosParaListagemDoUsuarioDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@RequestScoped
public class UsuarioService {
	
	@Inject
	private CadastroUsuarioService cadastroService;
	
	@Inject
	private UsuarioListagemService listagemService;

	public DadosParaListagemDoUsuarioDto cadastrarNovoUsuario(@Valid DadosParaCadastroDeUsuarioDto novosDados) {
		return cadastroService
				.cadastrarNovoUsuario(novosDados);

	}

	public DadosParaListagemDoUsuarioDto atualizarCadastro(@Valid DadosParaAtualizacaoDeUsuarioDto dadosAtualizados) {
		return cadastroService
				.atualizarCadastro(dadosAtualizados);
	}

	public List<DadosParaListagemDoUsuarioDto> getUsuarios() {
		return listagemService
				.listar();
	}

	public Optional<DadosParaListagemDoUsuarioDto> carregarPorId(@Positive int id) {
		return listagemService
				.buscarPorId(id);
	}

	public Optional<DadosParaListagemDoUsuarioDto> carregarPeloUsername(@NotBlank String username) {
		return listagemService
				.buscarPorUsername(username);
	}

	public void excluir(int id) {
		cadastroService.excluirPorId(id);
	}

}
