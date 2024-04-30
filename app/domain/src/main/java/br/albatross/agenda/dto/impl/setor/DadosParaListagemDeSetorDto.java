package br.albatross.agenda.dto.impl.setor;

import br.albatross.agenda.dto.impl.unidades.DadosParaListagemDeUnidadeDto;
import br.albatross.agenda.dto.spi.setor.DadosParaListagemDeSetor;
import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;
import br.albatross.agenda.models.Setor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter @EqualsAndHashCode(of = "id")
public class DadosParaListagemDeSetorDto implements DadosParaListagemDeSetor {

	private final Integer id;
	private final String sigla;
	private final String descricao;
	private final DadosParaListagemDeUnidade unidadeAdministrativa;

	public DadosParaListagemDeSetorDto(Setor setor) {
		this.id = setor.getId();
		this.sigla = setor.getSigla();
		this.descricao = setor.getDescricao();
		this.unidadeAdministrativa = new DadosParaListagemDeUnidadeDto(setor.getUnidadeAdministrativa());
	}

}
