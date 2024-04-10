package br.albatross.agenda.domain.models.contato;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DadosParaCadastroDeNovoContatoDto(

		@NotBlank(message = "Nome do Contato Obrigatório")
		@Size(message="Nome do Contato deve possuir no máximo 55 caractéres", max = 55)
		String nome,

		@NotBlank(message = "Número do Contato Obrigatório")
		@Size(message="Número do Contato deve possuir no máximo 55 caractéres", max = 55)
		String numero,

		@Positive(message = "Id do Setor deve ser um número inteiro positivo")
		@JsonbProperty("setor")
		int setorId,

		@NotBlank(message = "Andar do Contato Obrigatório")
		@Size(message="Andar deve possuir no máximo 10 caractéres", max = 10)
		String andar

	) {

}
