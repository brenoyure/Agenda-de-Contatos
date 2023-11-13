package br.albatross.agenda.beans.consulta;

import java.util.List;

import br.albatross.agenda.models.Setor;
import br.albatross.agenda.services.SetorService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @RequestScoped
public class ConsultaSetoresBean {

	@Inject
	private FacesContext context;

	@Inject
	private SetorService service;

	@Getter
	private List<Setor> setores;

	@PostConstruct
	void init() {
		setores = service.listar();
	}

	@Transactional
	public String excluir(Setor setor) {
		context.getExternalContext().getFlash().setKeepMessages(true);
		service.excluir(setor);
		context.addMessage(null, new FacesMessage("Setor: " + setor.getSigla() + " excluído."));
		return context.getViewRoot().getViewId() + "?faces-redirect=true";
	}

}
