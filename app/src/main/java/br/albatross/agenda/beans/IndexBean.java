package br.albatross.agenda.beans;

import java.util.List;

import br.albatross.agenda.dao.ContatoDao;
import br.albatross.agenda.models.Contato;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named @RequestScoped
public class IndexBean {

	@Inject
	private ContatoDao dao;
	
	public List<Contato> getContatos() {
		return dao.listar();
	}

}
