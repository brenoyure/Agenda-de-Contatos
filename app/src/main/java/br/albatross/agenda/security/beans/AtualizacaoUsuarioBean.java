package br.albatross.agenda.security.beans;

import java.io.Serializable;

import br.albatross.agenda.security.exceptions.CadastroException;
import br.albatross.agenda.security.models.DadosParaAtualizacaoDeUsuarioDto;
import br.albatross.agenda.security.services.UsuarioService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class AtualizacaoUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private UsuarioService usuarioService;

	@Getter @Setter
	private DadosParaAtualizacaoDeUsuarioDto dadosNovos;

	@Transactional
	public void atualizarCadastro() {
		try {

			var listagemDto = usuarioService.atualizarCadastro(dadosNovos);
			facesContext.addMessage(null,
					new FacesMessage(String.format("Cadastro do Usuário %s atualizado com sucesso.", listagemDto.getUsername())));

			this.dadosNovos = new DadosParaAtualizacaoDeUsuarioDto(listagemDto);


		} catch (CadastroException e) { 
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMensagem(), e.getMensagemDetalhada()));

		}
		
	}

	public void carregarUsuarioPeloId(int id) {
		usuarioService.carregarPorId(id).ifPresent(dto -> {
			this.dadosNovos = new DadosParaAtualizacaoDeUsuarioDto(dto);
		});
	}

}
