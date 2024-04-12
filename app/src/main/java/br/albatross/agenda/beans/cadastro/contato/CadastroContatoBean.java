package br.albatross.agenda.beans.cadastro.contato;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @RequestScoped
public class CadastroContatoBean {

	@Inject
	private FacesContext context;

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@PostConstruct
	void init() {
	    
	}

	@Transactional
	public String cadastrar() {
//		try {
			context.getExternalContext().getFlash().setKeepMessages(true);
//			service.salvar(contato);
//			context.addMessage(null, new FacesMessage("Contato: " + contato.getNome() + " cadastrado."));

			if (continuarNestaTela) {
				return context.getViewRoot().getViewId() + "?faces-redirect=true";
			}

			return "consultaContatos?faces-redirect=true";

//		} catch (ContatoExistenteException e) {
//			context.addMessage(null, new FacesMessage(e.getMessage()));
//			return context.getViewRoot().getViewId() + "?faces-redirect=true";
//		}

	}

}
