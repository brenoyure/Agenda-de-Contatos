package br.albatross.agenda.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AgendaApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public AgendaApplicationException(String message) {
		super(message);
	}

}
