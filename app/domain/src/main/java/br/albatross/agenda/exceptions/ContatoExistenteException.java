package br.albatross.agenda.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ContatoExistenteException extends CadastroException {

	private static final long serialVersionUID = 1L;

	public ContatoExistenteException(String message) {
		super(message);
	}

}
