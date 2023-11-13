package br.albatross.agenda.services;

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
		if (dao.existePorNome(contato.getNome())) {
			throw new ContatoExistenteException(String.format("Contato com o Nome informado '%s' já existente.", contato.getNome()));
		}

		dao.persist(contato);
	}

	public Contato buscarPorId(Number contatoId) {
		return dao.buscarPorId(contatoId);
	}
	
}
