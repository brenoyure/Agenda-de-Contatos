package br.albatross.agenda.dto.spi.unidades;

import java.io.Serializable;

public interface DadosParaListagemDeUnidade {

	Serializable getId();
	String getSigla();
	String getDescricao();

}
