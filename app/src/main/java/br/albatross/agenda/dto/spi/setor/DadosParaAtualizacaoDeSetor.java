package br.albatross.agenda.dto.spi.setor;

import java.io.Serializable;

public interface DadosParaAtualizacaoDeSetor extends DadosParaCadastroDeNovoSetor {

	Serializable getId();
	void setId(Serializable id);
	
}
