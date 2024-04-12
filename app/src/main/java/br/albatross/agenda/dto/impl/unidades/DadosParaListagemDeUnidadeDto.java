package br.albatross.agenda.dto.impl.unidades;

import java.io.Serializable;

import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.models.entities.UnidadeAdministrativa;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter @EqualsAndHashCode(of = "id")
public class DadosParaListagemDeUnidadeDto implements DadosParaListagemDeUnidade {

	private final Serializable id;
	private final String sigla;
	private final String descricao;

	public DadosParaListagemDeUnidadeDto(UnidadeAdministrativa unidadeAdministrativa) {
		this.id = unidadeAdministrativa.getId();
		this.sigla = unidadeAdministrativa.getSigla();
		this.descricao = unidadeAdministrativa.getDescricao();
	}

}
