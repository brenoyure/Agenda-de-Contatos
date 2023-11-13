package br.albatross.agenda.beans.consulta;

import java.util.List;

import br.albatross.agenda.models.UnidadeAdministrativa;
import br.albatross.agenda.services.UnidadeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @RequestScoped
public class ConsultaUnidadesBean {

	@Inject
	private UnidadeService service;

	@Getter
	private List<UnidadeAdministrativa> unidades;

	@PostConstruct
	void init() {
		unidades = service.listar();
	}

}
