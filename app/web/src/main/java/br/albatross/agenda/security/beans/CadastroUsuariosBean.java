package br.albatross.agenda.security.beans;

import static java.lang.String.format;

import java.io.Serializable;

import br.albatross.agenda.security.interceptors.UsuarioServiceExceptionHandler;
import br.albatross.agenda.security.models.DadosParaCadastroDeUsuarioDto;
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
public class CadastroUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private UsuarioService usuarioService;

	@Getter @Setter
	private DadosParaCadastroDeUsuarioDto dadosDeCadastro;

	@PostConstruct
	void init() {
		dadosDeCadastro = new DadosParaCadastroDeUsuarioDto();
	}

	@Transactional
	@UsuarioServiceExceptionHandler
	public void cadastrarUsuario() {

		usuarioService.cadastrarNovoUsuario(dadosDeCadastro);
		facesContext.addMessage(null, new FacesMessage(format("Usuário %s cadastrado com sucesso.", dadosDeCadastro.getUsername())));
		dadosDeCadastro = new DadosParaCadastroDeUsuarioDto();

	}


}
