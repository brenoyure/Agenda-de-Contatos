package br.albatross.agenda.dto.spi.unidades;

/**
 * Representa os dados necessários para cadastrar uma nova Unidade Administrativa no Sistema, 
 * como uma sede ou matriz da empresa. 
 * 
 * @author breno.brito
 */
public interface DadosParaCadastroDeNovaUnidade {

	String getSigla();
	String getDescricao();
	
	void setSigla(String sigla);
	void setDescricao(String descricao);

}
