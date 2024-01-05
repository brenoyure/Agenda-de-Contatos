package br.albatross.agenda.domain.services.contato;

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
import br.albatross.agenda.domain.services.GeradorDeArquivo;
import br.albatross.agenda.domain.services.ServicoDePaginacao;
import br.albatross.agenda.domain.services.setor.SetorService;
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
	private SetorService setorService;

	@Inject
	private GeradorDeArquivo<DadosParaListagemDeContatoDto> geradorDeArquivoService;

	public DadosParaListagemDeContatoDto salvar(@Valid DadosParaCadastroDeNovoContatoDto dados) {
		Contato contato = new Contato();
		contato.setNome(dados.nome());
		contato.setNumero(dados.numero());
		contato.setAndar(dados.andar());
		contato.setSetor(setorService.getReferenceById(dados.setorId()));
		return dao.persist(contato);
	}

	public Pagina listaPaginada(int pagina, byte resultadosPorPagina) {
		return servicoDePaginacao.getListagemPaginada(dao.listar(pagina, resultadosPorPagina), pagina, resultadosPorPagina, dao.getTotal());
	}

	public Pagina listaPaginada(int pagina, byte resultadosPorPagina, DadosParaPesquisaDeContatosDto dadosParaPesquisa) {
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
