package br.albatross.agenda.domain.models.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosParaCriacaoDeUsuarioDto(

		@NotBlank
		String nomeDoUsuario, 

		@NotBlank
		String senha

	) {

}
