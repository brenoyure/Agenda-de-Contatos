package br.albatross.agenda.security.beans;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;

import br.albatross.agenda.security.daos.spi.RolesDao;
import br.albatross.agenda.security.models.DadosParaListagemDaRoleDto;
import br.albatross.agenda.security.models.Role;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @ViewScoped
public class ListaRolesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RolesDao dao;

	@Getter
	private List<DadosParaListagemDaRoleDto> roles;

	@PostConstruct
	@Transactional
	void init() {
		roles = dao
		        .findAll(Role.class)
		        .stream()
		        .map(DadosParaListagemDaRoleDto::new)
		        .collect(toList());
	}

}
