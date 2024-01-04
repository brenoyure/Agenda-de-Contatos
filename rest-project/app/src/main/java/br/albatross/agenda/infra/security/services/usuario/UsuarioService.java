package br.albatross.agenda.infra.security.services.usuario;

import br.albatross.agenda.domain.models.usuario.DadosBasicosDoUsuarioParaExibicaoDto;
import br.albatross.agenda.domain.models.usuario.DadosParaCriacaoDeUsuarioDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestScoped
public class UsuarioService {

	@Inject
	private CadastroDeUsuarioService cadastroService;

	@Transactional
	public DadosBasicosDoUsuarioParaExibicaoDto criarNovoUsuario(@Valid DadosParaCriacaoDeUsuarioDto dados) {
		return cadastroService.criarNovoUsuario(dados);
	}

}
