package br.albatross.agenda.domain.models.contato;

public record DadosParaListagemDeContatoDto(

		Short  id,
		String nome,
		String ramal,
		String setor,
		String andar

	)  {

	public DadosParaListagemDeContatoDto(Contato contato) {
		this(contato.getId(), contato.getNome(), contato.getNumero(), contato.getSetor().getSigla(), contato.getAndar());
	}

}
