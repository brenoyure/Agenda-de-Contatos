package br.albatross.agenda.domain.exceptions;

public class ContatoExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String CONTATO_JA_EXISTE_EXCEPTION_MESSAGE = "Já existe um Contato cadastrado com o Nome informado";
	
	public ContatoExistenteException() {
		super(CONTATO_JA_EXISTE_EXCEPTION_MESSAGE);
	}

}
