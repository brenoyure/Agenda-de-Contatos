package br.albatross.agenda.domain.services.paginacao;

import java.util.List;

import br.albatross.agenda.domain.models.contato.Pagina;
import jakarta.enterprise.context.Dependent;

/**
 * Serviço Responsável por gerar a paginação de uma lista de objetos, de forma a
 * facilitar o front-end por exemplo, ao entregar um objeto do tipo <code>Página</code>
 * 
 * Ao realizar uma consulta de contatos, o front receberá este objeto, contendo a lista de contatos, bem como a quantidade total de registros,
 * Resultados por Página, Total de Páginas e etc.
 * 
 */
@Dependent
public class ServicoDePaginacao<T> {

	public Pagina<T> getListagemPaginada(List<T> listaDeContatos, int paginaAtual,	byte resultadosPorPagina, long totalDeContatos) {
		int totalDePaginas = (int) Math.ceil(((float) totalDeContatos / (float) resultadosPorPagina));
		return new Pagina<T>(listaDeContatos, paginaAtual, totalDePaginas, (long) totalDeContatos,	(byte) resultadosPorPagina);
	}

}
