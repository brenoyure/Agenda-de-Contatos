package br.albatross.agenda.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CadastroException extends AgendaApplicationException {

	private static final long serialVersionUID = 1L;

	public CadastroException(String message) {
		super(message);
	}

}
