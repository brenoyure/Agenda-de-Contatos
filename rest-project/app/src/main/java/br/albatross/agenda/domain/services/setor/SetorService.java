package br.albatross.agenda.domain.services.setor;

import br.albatross.agenda.domain.dao.SetorDao;
import br.albatross.agenda.domain.models.setor.Setor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SetorService {

	@Inject
	private SetorDao dao;

	public Setor getReferenceById(int id) {
		return dao.getReferenceById(id);
	}

}
