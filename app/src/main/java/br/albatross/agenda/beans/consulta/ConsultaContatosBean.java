package br.albatross.agenda.beans.consulta;

import java.util.List;

import br.albatross.agenda.models.Contato;
import br.albatross.agenda.services.ContatoService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named @RequestScoped
public class ConsultaContatosBean {

	@Inject
	private ContatoService service;

	public List<Contato> getContatos() {
		return service.listar();
	}

}
