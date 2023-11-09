package br.albatross.agenda.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class SetorExistenteException extends CadastroException {

	private static final long serialVersionUID = 1L;

	public SetorExistenteException(String message) {
		super(message);
	}

}
