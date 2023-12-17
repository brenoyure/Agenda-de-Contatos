package br.albatross.agenda.domain.models.contato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosParaAtualizacaoDeContatoDto(

		@NotNull
		Short id,

		@NotBlank
		String nome,

		@NotBlank
		String ramal,

		@NotBlank
		String setor,

		@NotBlank
		String andar
	) {

}
