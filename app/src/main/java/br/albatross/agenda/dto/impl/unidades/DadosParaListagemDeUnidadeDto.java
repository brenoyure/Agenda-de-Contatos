package br.albatross.agenda.dto.impl.unidades;

import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.models.UnidadeAdministrativa;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class DadosParaListagemDeUnidadeDto implements DadosParaListagemDeUnidade {

	private final Integer id;
	private final String sigla;
	private final String descricao;

	public DadosParaListagemDeUnidadeDto(UnidadeAdministrativa unidadeAdministrativa) {
		this.id = unidadeAdministrativa.getId();
		this.sigla = unidadeAdministrativa.getSigla();
		this.descricao = unidadeAdministrativa.getDescricao();
	}

}
