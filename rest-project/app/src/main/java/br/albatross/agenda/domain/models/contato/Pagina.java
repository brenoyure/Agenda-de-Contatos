package br.albatross.agenda.domain.models.contato;

import java.util.List;

/**
 * DTO que representa uma "página" contendo a lista dos dados<T> para exibição, a página atual e o total de registros
 * 
 * @author breno.brito
 */
public record Pagina<T>(
		List<T> listaDeDados, 
		int paginaAtual, 
		int totalDePaginas,
		long totalDeRegistros,
		byte resultadosPorPagina
	) {

}
