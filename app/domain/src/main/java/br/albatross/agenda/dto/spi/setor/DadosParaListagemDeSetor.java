package br.albatross.agenda.dto.spi.setor;

import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;

public interface DadosParaListagemDeSetor {

    Integer getId();
	String getSigla();
	String getDescricao();

	DadosParaListagemDeUnidade getUnidadeAdministrativa();

}
