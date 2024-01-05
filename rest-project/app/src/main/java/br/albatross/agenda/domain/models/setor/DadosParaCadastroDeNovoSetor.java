package br.albatross.agenda.domain.models.setor;

import jakarta.validation.constraints.Size;

public record DadosParaCadastroDeNovoSetor(

	@Size(min = 2, max = 50) 
	String sigla

	){

}
