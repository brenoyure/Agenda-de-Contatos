package br.albatross.agenda.domain.exceptions;

/**
 * Esta Exception será lançada pelo serviço de cadastro de setor caso tente
 * cadastrar um setor que já existe.
 */
public class SetorCadastroException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String SETOR_JA_EXISTE_EXCEPTION_MESSAGE = "Já existe um setor cadastrado com a sigla informada";
	public static final String SETOR_AINDA_POSSUI_CONTATOS_EXCEPTION_MESSAGE = "Existem Contatos cadastrados com este setor. Exclusão Não Realizada";

	public SetorCadastroException(String msg) {
		super(msg);
	}

}
