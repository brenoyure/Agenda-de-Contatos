package br.albatross.agenda.domain.services.contato;

import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.dao.ContatoDao;
import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaPesquisaDeContatosDto;
import br.albatross.agenda.domain.models.contato.Pagina;
import br.albatross.agenda.domain.services.paginacao.ServicoDePaginacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContatoConsultaService {

	@Inject
	private ContatoDao dao;

	@Inject
	private ServicoDePaginacao<DadosParaListagemDeContatoDto> servicoDePaginacao;

	public Pagina<DadosParaListagemDeContatoDto> listaPaginada(int pagina, byte resultadosPorPagina) {
		return servicoDePaginacao.getListagemPaginada(dao.listar(pagina, resultadosPorPagina), pagina, resultadosPorPagina, dao.getTotal());
	}

	//TODO Método está retornando o número de páginas errado no DTO, pendente para correção.
	public Pagina<DadosParaListagemDeContatoDto> listaPaginada(int pagina, byte resultadosPorPagina, DadosParaPesquisaDeContatosDto dadosParaPesquisa) {
		return servicoDePaginacao.getListagemPaginada(dao.listar(pagina, resultadosPorPagina, dadosParaPesquisa), pagina, resultadosPorPagina, dao.getTotal());
	}

	public List<DadosParaListagemDeContatoDto> listarTodos(DadosParaPesquisaDeContatosDto dados) {
		return dao.listarTodos(dados);
	}

	public List<DadosParaListagemDeContatoDto> listarTodos() {
		return dao.listarTodos();
	}

	public Optional<DadosParaListagemDeContatoDto> buscarPorId(short contatoId) {
		return dao.buscarPorId(contatoId);
	}

}
