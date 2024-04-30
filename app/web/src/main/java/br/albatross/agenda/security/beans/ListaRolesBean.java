package br.albatross.agenda.security.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.agenda.security.daos.RolesDao;
import br.albatross.agenda.security.models.Role;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ViewScoped
public class ListaRolesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RolesDao dao;

	@Getter
	private List<Role> roles;

	@PostConstruct
	void init() {
		roles = dao.getRoles();
	}

}
