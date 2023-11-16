package br.albatross.agenda.beans.consulta;

import java.util.List;

import br.albatross.agenda.models.Contato;
import br.albatross.agenda.services.ContatoService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @RequestScoped
public class ConsultaContatosBean {

	@Inject
	private FacesContext context;

	@Inject
	private ContatoService service;

	@Getter
	private List<Contato> contatos;
	
	@PostConstruct
	void init() {
		contatos = service.listar();
	}
	
	@Transactional
	public String excluir(Contato contato) {
		context.getExternalContext().getFlash().setKeepMessages(true);
		service.excluir(contato);
		context.addMessage(null, new FacesMessage("Contato: " + contato.getNome() + " excluído."));
		return context.getViewRoot().getViewId() + "?faces-redirect=true";
	}

}
