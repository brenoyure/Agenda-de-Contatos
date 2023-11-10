package br.albatross.agenda.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class UnidadeExistenteException extends CadastroException {

	private static final long serialVersionUID = 1L;

	public UnidadeExistenteException(String message) {
		super(message);
	}

}
