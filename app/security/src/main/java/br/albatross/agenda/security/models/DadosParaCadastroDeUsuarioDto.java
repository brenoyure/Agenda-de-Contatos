package br.albatross.agenda.security.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosParaCadastroDeUsuarioDto {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@Positive
	private int roleId;

}