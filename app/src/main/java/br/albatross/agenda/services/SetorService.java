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

		if (setor.getId() == null) {
			dao.persist(setor);
		}

		else {
			dao.atualizar(setor);
		}

	}

	public boolean existePorId(Short setorId) {
		return dao.existePorId(setorId);
	}	

	public List<Setor> listar() {
		return dao.listar();
	}

	public void atualizar(Setor setor) {
		dao.atualizar(setor);
	}

	public Setor carregar(Short setorId) {
		return dao.carregar(setorId);
	}

	public void excluir(Setor setor) {
		dao.excluir(setor);
		
	}

}
