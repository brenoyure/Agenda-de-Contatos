package br.albatross.agenda.services;

import java.util.List;

import br.albatross.agenda.dao.ContatoDao;
import br.albatross.agenda.exceptions.ContatoExistenteException;
import br.albatross.agenda.models.Contato;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ContatoService {

	@Inject
	private ContatoDao dao;
	
	public void salvar(Contato contato) throws ContatoExistenteException {
		if (contato.getId() == null) {
			dao.persist(contato);
		}

		else {
			dao.atualizar(contato);
		}

	}

	public List<Contato> listar() {
		return dao.listar();
	}

	public Contato buscarPorId(Number contatoId) {
		return dao.buscarPorId(contatoId);
	}

	public void excluir(Contato contato) {
		dao.excluir(contato);
	}
	
}
