package br.albatross.agenda.domain.models.contato;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosParaCadastroDeNovoContatoDto(

		@NotBlank
		@Max(50)
		String nome,

		@Pattern(regexp = "\\d{10,13}")
		String ramal,

		@NotBlank
		@Max(50)
		String setor,

		@NotBlank
		@Max(10)
		String andar

	) {

}
