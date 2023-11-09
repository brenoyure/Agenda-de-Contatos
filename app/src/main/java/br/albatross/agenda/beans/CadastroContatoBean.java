package br.albatross.agenda.beans;

import java.util.List;

import br.albatross.agenda.exceptions.ContatoExistenteException;
import br.albatross.agenda.models.Andar;
import br.albatross.agenda.models.Contato;
import br.albatross.agenda.models.Setor;
import br.albatross.agenda.services.AndarService;
import br.albatross.agenda.services.ContatoService;
import br.albatross.agenda.services.SetorService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
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

	@Inject
	private ContatoService service;

	@Inject
	private SetorService setorService;
	
	@Inject
	private AndarService andarService;

	@Getter
	private Contato contato;

	@Getter
	private List<Setor> setores;
	
	@Getter
	private List<Andar> andares;

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@PostConstruct
	void init() {
		contato = new Contato();
		setores = setorService.listar();
		andares = andarService.listar();
	}

	@Transactional
	public String cadastrar() {
		try {
			context.getExternalContext().getFlash().setKeepMessages(true);
			service.salvar(contato);
			context.addMessage(null, new FacesMessage("Contato: " + contato.getNome() + " cadastrado."));

			if (continuarNestaTela) {
				return context.getViewRoot().getViewId() + "?faces-redirect=true";
			}

			return "index?faces-redirect=true";

		} catch (ContatoExistenteException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
			return context.getViewRoot().getViewId() + "?faces-redirect=true";
		}

	}

}
