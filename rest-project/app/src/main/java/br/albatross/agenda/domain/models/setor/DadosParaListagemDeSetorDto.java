package br.albatross.agenda.domain.models.setor;

public record DadosParaListagemDeSetorDto(int id, String sigla) {

	public DadosParaListagemDeSetorDto(Setor setor) {
		this(setor.getId(), setor.getSigla());
	}

}
