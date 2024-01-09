package br.albatross.agenda.domain.models.contato;

import jakarta.validation.constraints.NotNull;

public record DadosParaPesquisaDeContatosDto(

		@NotNull String andar,

		@NotNull String nome,

		@NotNull String ramal,

		@NotNull String setor

) {

}
