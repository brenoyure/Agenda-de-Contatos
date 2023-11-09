package br.albatross.agenda.services;

import java.util.List;

import br.albatross.agenda.dao.SetorDao;
import br.albatross.agenda.exceptions.SetorExistenteException;
import br.albatross.agenda.models.Setor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SetorService {

	@Inject
	private SetorDao dao;

	public void salvar(Setor setor) throws SetorExistenteException {
		if (dao.existePorSigla(setor.getSigla())) {
			throw new SetorExistenteException(String.format("Setor com a Sigla informada '%s' já existente.", setor.getSigla()));
		}

		dao.persist(setor);
	}

	public List<Setor> listar() {
		return dao.listar();
	}

}
