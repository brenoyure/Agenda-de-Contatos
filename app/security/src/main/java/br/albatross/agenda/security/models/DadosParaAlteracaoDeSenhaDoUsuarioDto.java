package br.albatross.agenda.security.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class DadosParaAlteracaoDeSenhaDoUsuarioDto {

	@Positive
	private int usuarioId;

	@NotBlank
	private String newPlainTextPassword;

	public DadosParaAlteracaoDeSenhaDoUsuarioDto(DadosParaListagemDoUsuarioDto dto) {

		this.usuarioId = dto.getId();

	}

}
