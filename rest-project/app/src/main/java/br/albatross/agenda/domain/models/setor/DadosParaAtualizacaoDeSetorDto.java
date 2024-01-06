package br.albatross.agenda.domain.models.setor;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DadosParaAtualizacaoDeSetorDto(@Positive int id, @Size(min = 2, max = 50) String sigla) {

}
