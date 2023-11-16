package br.albatross.agenda.beans.consulta;

import java.util.List;

import br.albatross.agenda.models.UnidadeAdministrativa;
import br.albatross.agenda.services.UnidadeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @RequestScoped
public class ConsultaUnidadesBean {

	@Inject
	private FacesContext context;

	@Inject
	private UnidadeService service;

	@Getter
	private List<UnidadeAdministrativa> unidades;

	@PostConstruct
	void init() {
		unidades = service.listar();
	}

	@Transactional
	public String excluir(UnidadeAdministrativa unidadeAdministrativa) {
		context.getExternalContext().getFlash().setKeepMessages(true);
		service.excluir(unidadeAdministrativa);
		context.addMessage(null, new FacesMessage("Unidade: " + unidadeAdministrativa.getSigla() + " excluída."));
		return context.getViewRoot().getViewId() + "?faces-redirect=true";
	}	

}
