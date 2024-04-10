package br.albatross.agenda.domain.models.setor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosParaCadastroDeNovoSetor(

	@NotBlank(message = "Sigla do Setor Obrigatória")
	@Size(message="Sigla do Setor deve possuir de 2 a 50 caracteres",  min = 2, max = 50) 
	String sigla

	){

}
