package br.albatross.agenda.domain.models.contato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DadosParaAtualizacaoDeContatoDto(

		@Positive
		Short id,

		@NotBlank
		String nome,

		@Pattern(regexp = "\\d{10,13}")
		String ramal,

		@NotNull
		@Positive
		int setorId,

		@NotBlank
		@Size(max = 10)
		String andar
	) {

}
