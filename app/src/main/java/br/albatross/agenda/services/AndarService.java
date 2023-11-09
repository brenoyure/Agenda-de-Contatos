package br.albatross.agenda.services;

import java.util.List;

import br.albatross.agenda.dao.AndarDao;
import br.albatross.agenda.models.Andar;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AndarService {

	@Inject
	private AndarDao dao;

	public List<Andar> listar() {
		return dao.listar();
	}
}
