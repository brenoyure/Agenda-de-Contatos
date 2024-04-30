package br.albatross.agenda.dto.impl.unidades;

import br.albatross.agenda.dto.spi.unidades.DadosParaCadastroDeNovaUnidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa os dados necessários para cadastrar uma nova Unidade Administrativa no Sistema, 
 * como uma sede ou matriz da empresa. 
 * 
 * @author breno.brito
 */
@Getter @Setter
@NoArgsConstructor
public class DadosParaCadastroDeNovaUnidadeDto implements DadosParaCadastroDeNovaUnidade {

	@NotBlank(message = "{unidade.sigla.obrigatoria}")
	@Size(message = "{unidade.sigla.tamanho.permitido}", min = 2, max = 55)
	private String sigla;
	private String descricao;

}
