package br.albatross.agenda.dto.spi.setor;

import java.io.Serializable;

import br.albatross.agenda.dto.spi.unidades.DadosParaListagemDeUnidade;

public interface DadosParaListagemDeSetor {

	Serializable getId();
	String getSigla();
	String getDescricao();

	DadosParaListagemDeUnidade getUnidadeAdministrativa();

}
