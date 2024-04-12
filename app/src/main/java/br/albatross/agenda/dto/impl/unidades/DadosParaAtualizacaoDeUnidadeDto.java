package br.albatross.agenda.dto.impl.unidades;

import java.io.Serializable;

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
	private Serializable id;

	public DadosParaAtualizacaoDeUnidadeDto(DadosParaListagemDeUnidade dados) {
		this.id = dados.getId();
		setSigla(dados.getSigla());
		setDescricao(dados.getDescricao());
	}
	
}
