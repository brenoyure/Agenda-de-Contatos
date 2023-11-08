package br.albatross.agenda.beans;

import java.util.List;

import br.albatross.agenda.dao.UnidadeAdministrativaDao;
import br.albatross.agenda.models.UnidadeAdministrativa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @RequestScoped
public class CadastroUnidadeAdministrativaBean {

	@Inject
	private FacesContext context;
	
	@Inject
	private UnidadeAdministrativaDao dao;

	@Getter
	private UnidadeAdministrativa unidadeAdmin = new UnidadeAdministrativa();

	@Transactional
	public String cadastrar() {
		dao.persist(unidadeAdmin);
		context.addMessage(null, new FacesMessage(unidadeAdmin.getSigla() + " cadastrada."));
		context.getExternalContext().getFlash().setKeepMessages(true);
		return "/?faces-redirect=true";
	}

	public List<UnidadeAdministrativa> getUnidadesAdministrativas() {
		return dao.listar(); 
	}
}
