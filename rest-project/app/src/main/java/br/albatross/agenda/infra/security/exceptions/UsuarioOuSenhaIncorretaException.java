package br.albatross.agenda.infra.security.exceptions;

public class UsuarioOuSenhaIncorretaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String INCORRET_USERNAME_OR_PASSWORD_EXCEPTION_MESSAGE = "Usuário ou Senha Incorretos";

	public UsuarioOuSenhaIncorretaException() {
		super(INCORRET_USERNAME_OR_PASSWORD_EXCEPTION_MESSAGE);
	}

	public UsuarioOuSenhaIncorretaException(String message) {
		super(message);
	}

}
