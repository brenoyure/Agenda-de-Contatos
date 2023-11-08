package br.albatross.agenda.beans;

import br.albatross.agenda.dao.SetorDao;
import br.albatross.agenda.models.Setor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @RequestScoped
public class CadastroSetorBean {

	@Inject
	private FacesContext context;

	@Inject
	private SetorDao dao;

	@Getter
	private Setor setor = new Setor();

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@Transactional
	public String cadastrar() {
		dao.persist(setor);
		context.addMessage(null, new FacesMessage("Setor: " + setor.getSigla() + " cadastrado."));

		if (continuarNestaTela) {
			return context.getViewRoot().getViewId() + "?faces-redirect=true";
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		return "/?faces-redirect=true";
	}

}
