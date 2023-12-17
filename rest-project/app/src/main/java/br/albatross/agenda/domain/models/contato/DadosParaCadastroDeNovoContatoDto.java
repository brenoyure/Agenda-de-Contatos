package br.albatross.agenda.domain.models.contato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosParaCadastroDeNovoContatoDto(

		@NotBlank
		@Size(max = 50)
		String nome,

		@Pattern(regexp = "\\d{10,13}")
		String ramal,

		@NotBlank
		@Size(max = 50)
		String setor,

		@NotBlank
		@Size(max = 10)
		String andar

	) {

}