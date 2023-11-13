package br.albatross.agenda.beans.cadastro;

import java.util.List;

import br.albatross.agenda.dao.UnidadeAdministrativaDao;
import br.albatross.agenda.exceptions.SetorExistenteException;
import br.albatross.agenda.models.Setor;
import br.albatross.agenda.models.UnidadeAdministrativa;
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
public class CadastroSetorBean {

	@Inject
	private FacesContext context;

	@Inject
	private SetorService service;

	@Inject
	private UnidadeAdministrativaDao unidadeAdminDao;

	@Getter
	private List<UnidadeAdministrativa> unidadesAdministrativas;

	@Getter
	private Setor setor;

	@Getter @Setter
	private boolean continuarNestaTela = false;

	@PostConstruct
	void init() {
		setor = new Setor();
		unidadesAdministrativas = unidadeAdminDao.listar();
	}

	@Transactional
	public String cadastrar() {
		try {
			context.getExternalContext().getFlash().setKeepMessages(true);
			service.salvar(setor);
			context.addMessage(null, new FacesMessage("Setor: " + setor.getSigla() + " cadastrado."));

			if (continuarNestaTela) {
				return context.getViewRoot().getViewId() + "?faces-redirect=true";
			}

			return "consultaSetores?faces-redirect=true";

		} catch (SetorExistenteException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
			return context.getViewRoot().getViewId() + "?faces-redirect=true";
		}

	}

	public void carregar(Short setorId) {
		if (service.existePorId(setorId)) {
			setor = service.carregar(setorId);
		}
	}

}
