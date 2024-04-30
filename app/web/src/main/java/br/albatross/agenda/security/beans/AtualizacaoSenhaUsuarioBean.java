package br.albatross.agenda.security.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.Serializable;
import java.security.Principal;

import br.albatross.agenda.security.exceptions.CadastroException;
import br.albatross.agenda.security.models.DadosParaAtualizacaoDeUsuarioDto;
import br.albatross.agenda.security.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class AtualizacaoSenhaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private FacesContext facesContext;

	@Inject
	private Principal principal;

	@Getter @Setter
	private DadosParaAtualizacaoDeUsuarioDto dadosNovos;

	@Transactional
	public void atualizarCadastro() {
		try {

			usuarioService.atualizarCadastro(dadosNovos);
			facesContext.addMessage(null, new FacesMessage("Senha do Usuário Alterada com Sucesso"));

		} catch (CadastroException e) {
			facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR, e.getMensagem(), e.getMensagemDetalhada()));
		}
	}

	@Transactional
	@PostConstruct
	public void carregarUsuarioPeloUsername() {
		try {

			usuarioService
			.carregarPeloUsername(principal.getName())
			.ifPresent(dto -> {
				this.dadosNovos = new DadosParaAtualizacaoDeUsuarioDto(dto);
			});

		} catch(CadastroException e) {
			facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR, e.getMensagem(), e.getMensagemDetalhada()));
			facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/index?faces-redirect=true");
		}

	}

}
