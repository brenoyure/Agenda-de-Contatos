package br.albatross.agenda.domain.services;

import java.io.File;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.dao.ContatoDao;
import br.albatross.agenda.domain.models.contato.Contato;
import br.albatross.agenda.domain.models.contato.DadosParaAtualizacaoDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaCadastroDeNovoContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaPesquisaDeContatosDto;
import br.albatross.agenda.domain.models.contato.Pagina;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class ContatoService {

	@Inject
	private ContatoDao dao;

	@Inject
	private ServicoDePaginacao servicoDePaginacao;

	@Inject
	private GeradorDeArquivo<DadosParaListagemDeContatoDto> geradorDeArquivoService;

	public DadosParaListagemDeContatoDto salvar(@Valid DadosParaCadastroDeNovoContatoDto dados) {
		return dao.persist(new Contato(dados));
	}

	public Pagina listaPaginada(int pagina, byte resultadosPorPagina) {
		var listaDeContatos = dao.listar(pagina, resultadosPorPagina);
		var totalDeContatos = dao.getTotal();
		return servicoDePaginacao.getListagemPaginada(listaDeContatos, pagina, resultadosPorPagina, totalDeContatos);
	}

	public Pagina listaPaginada(int pagina, byte resultadosPorPagina, DadosParaPesquisaDeContatosDto dadosParaPesquisa) {
		var listaDeContatos = dao.listar(pagina, resultadosPorPagina, dadosParaPesquisa);
		var totalDeContatos = dao.getTotal();
		return servicoDePaginacao.getListagemPaginada(listaDeContatos, pagina, resultadosPorPagina, totalDeContatos);
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

	public DadosParaListagemDeContatoDto atualizarCadastro(@Valid DadosParaAtualizacaoDeContatoDto dados) {
		return new DadosParaListagemDeContatoDto(dao.atualizar(new Contato(dados)));
	}

	public void excluir(short id) {
		dao.excluir(id);
	}

	public File gerarDOCX() {
		return geradorDeArquivoService.gerar(dao.listarTodos());
	}

	public File gerarDOCX(DadosParaPesquisaDeContatosDto dados) {
		return geradorDeArquivoService.gerar(dao.listarTodos(dados));
	}

}
