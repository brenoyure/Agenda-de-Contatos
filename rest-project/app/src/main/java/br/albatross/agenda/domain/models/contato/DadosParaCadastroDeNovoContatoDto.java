package br.albatross.agenda.domain.models.contato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosParaCadastroDeNovoContatoDto(

		@NotBlank
		String nome,

		@Pattern(regexp = "\\d{10,13}")
		String ramal,

		@NotBlank
		String setor,

		@NotBlank
		String andar

	) {

}
