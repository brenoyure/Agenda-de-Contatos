package br.albatross.agenda.domain.services;

import java.util.List;

import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import br.albatross.agenda.domain.models.contato.Pagina;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServicoDePaginacao {

	public Pagina getListagemPaginada(List<DadosParaListagemDeContatoDto> listaDeContatos, int paginaAtual, byte resultadosPorPagina, long totalDeContatos) {
		int totalDePaginas = (int) Math.ceil(((float) totalDeContatos / (float) resultadosPorPagina));
		return new Pagina(listaDeContatos, paginaAtual, totalDePaginas, (long) totalDeContatos, (byte) resultadosPorPagina);
	}

}
