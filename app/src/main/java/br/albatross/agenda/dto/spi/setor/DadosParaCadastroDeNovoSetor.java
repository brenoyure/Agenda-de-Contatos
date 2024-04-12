package br.albatross.agenda.dto.spi.setor;

public interface DadosParaCadastroDeNovoSetor {

	String getSigla();
	String getDescricao();
	Number getUnidadeId();	

	void setSigla(String sigla);
	void setDescricao(String descricao);
	void setUnidadeId(Number id);

}
