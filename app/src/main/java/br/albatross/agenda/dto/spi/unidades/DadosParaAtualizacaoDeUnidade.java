package br.albatross.agenda.dto.spi.unidades;

import java.io.Serializable;

public interface DadosParaAtualizacaoDeUnidade extends DadosParaCadastroDeNovaUnidade {

	Serializable getId();
	void setId(Serializable id);

}
