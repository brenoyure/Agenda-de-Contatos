package br.albatross.agenda.dto.impl.unidades;

import br.albatross.agenda.dto.spi.unidades.DadosParaAtualizacaoDeUnidade;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosParaAtualizacaoDeUnidadeDto extends DadosParaCadastroDeNovaUnidadeDto implements DadosParaAtualizacaoDeUnidade {

	@NotNull @Positive
	private Integer id;

	public DadosParaAtualizacaoDeUnidadeDto(DadosParaListagemDeUnidade dados) {
		this.id = dados.getId();
		super.setSigla(dados.getSigla());
		super.setDescricao(dados.getDescricao());
	}

}
