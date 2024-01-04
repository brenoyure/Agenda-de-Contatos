package br.albatross.agenda.infra.security.exceptions;

public class UsuarioExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioExistenteException(String msg) {
		super(msg);
	}

}
