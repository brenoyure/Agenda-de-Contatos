package br.albatross.agenda.infra.security.credentials;

import jakarta.validation.constraints.NotBlank;

public record Credenciais(

		@NotBlank
		String username, 

		@NotBlank
		String password

	) {

}
