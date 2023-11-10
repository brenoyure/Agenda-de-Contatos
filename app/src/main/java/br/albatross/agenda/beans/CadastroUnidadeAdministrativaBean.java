package br.albatross.agenda.beans;

import br.albatross.agenda.exceptions.UnidadeExistenteException;
import br.albatross.agenda.models.UnidadeAdministrativa;
import br.albatross.agenda.services.UnidadeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @RequestScoped
public class CadastroUnidadeAdministrativaBean {

	@Inject
	private FacesContext context;
	
	@Inject
	private UnidadeService service;

	@Getter
	private UnidadeAdministrativa unidadeAdmin = new UnidadeAdministrativa();

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@Transactional
	public String cadastrar() {
		try {
			context.getExternalContext().getFlash().setKeepMessages(true);
			service.salvar(unidadeAdmin);
			context.addMessage(null, new FacesMessage("Unidade " + unidadeAdmin.getSigla() + " cadastrada"));

			if (continuarNestaTela) {
				return context.getViewRoot().getViewId() + "?faces-redirect=true";
			}

			return "index?faces-redirect=true";

		} catch (UnidadeExistenteException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
			return context.getViewRoot().getViewId() + "?faces-redirect=true";
		}
		
	}

}
