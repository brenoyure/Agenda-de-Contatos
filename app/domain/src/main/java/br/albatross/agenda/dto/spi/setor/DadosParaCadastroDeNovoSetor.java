package br.albatross.agenda.dto.spi.setor;

public interface DadosParaCadastroDeNovoSetor {

	String getSigla();
	String getDescricao();
	Integer getUnidadeId();	

	void setSigla(String sigla);
	void setDescricao(String descricao);
	void setUnidadeId(Integer id);

}
