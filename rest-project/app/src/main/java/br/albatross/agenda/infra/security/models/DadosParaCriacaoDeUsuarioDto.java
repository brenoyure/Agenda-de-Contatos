package br.albatross.agenda.infra.security.models;

import jakarta.validation.constraints.NotBlank;

public record DadosParaCriacaoDeUsuarioDto(

		@NotBlank
		String nomeDoUsuario, 

		@NotBlank
		String senha

	) {

}
