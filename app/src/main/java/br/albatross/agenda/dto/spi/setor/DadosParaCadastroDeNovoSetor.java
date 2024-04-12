package br.albatross.agenda.dto.spi.setor;

import java.io.Serializable;

public interface DadosParaCadastroDeNovoSetor {

	String getSigla();
	String getDescricao();
	Serializable getUnidadeId();	

	void setSigla(String sigla);
	void setDescricao(String descricao);
	void setUnidadeId(Serializable id);

}
