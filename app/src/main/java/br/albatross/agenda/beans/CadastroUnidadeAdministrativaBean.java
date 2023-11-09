package br.albatross.agenda.beans;

import br.albatross.agenda.dao.UnidadeAdministrativaDao;
import br.albatross.agenda.models.UnidadeAdministrativa;
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
	private UnidadeAdministrativaDao dao;

	@Getter
	private UnidadeAdministrativa unidadeAdmin = new UnidadeAdministrativa();

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@Transactional
	public String cadastrar() {
		dao.persist(unidadeAdmin);
		context.addMessage(null, new FacesMessage("Unidade " + unidadeAdmin.getSigla() + " cadastrada"));
		context.getExternalContext().getFlash().setKeepMessages(true);

		if (continuarNestaTela) {
			return context.getViewRoot().getViewId() + "?faces-redirect=true";
		}

		return "index?faces-redirect=true";
		
	}

}
