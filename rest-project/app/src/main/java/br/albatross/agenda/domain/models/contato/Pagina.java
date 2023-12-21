package br.albatross.agenda.domain.models.contato;

import java.util.List;

/**
 * DTO que representa uma "página" da Agenda, contento uma lista de contatos, a página atual e o total de registros
 * 
 * @author breno.brito
 */
public record Pagina(
		List<DadosParaListagemDeContatoDto> listaDeContatos, 
		int paginaAtual, 
		int totalDePaginas,
		long totalDeContatos,
		byte resultadosPorPagina
	) {

}
