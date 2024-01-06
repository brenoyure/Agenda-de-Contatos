package br.albatross.agenda.domain.services.contato;

import java.io.File;
import java.util.List;
import java.util.Optional;

import br.albatross.agenda.domain.models.contato.DadosParaAtualizacaoDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaCadastroDeNovoContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaPesquisaDeContatosDto;
import br.albatross.agenda.domain.models.contato.Pagina;
import br.albatross.agenda.domain.services.GeradorDeArquivo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class ContatoService {

	@Inject
	private ContatoCadastroService cadastroService;

	@Inject
	private ContatoConsultaService consultaService;

	@Inject
	private GeradorDeArquivo<DadosParaListagemDeContatoDto> geradorDeArquivoService;

	public DadosParaListagemDeContatoDto salvar(@Valid DadosParaCadastroDeNovoContatoDto dados) {
		return cadastroService.salvar(dados);
	}

	public Pagina<DadosParaListagemDeContatoDto> listaPaginada(int pagina, byte resultadosPorPagina) {
		return consultaService.listaPaginada(pagina, resultadosPorPagina);
	}

	public Pagina<DadosParaListagemDeContatoDto> listaPaginada(int pagina, byte resultadosPorPagina, DadosParaPesquisaDeContatosDto dadosParaPesquisa) {
		return consultaService.listaPaginada(pagina, resultadosPorPagina, dadosParaPesquisa);
	}
	
	public List<DadosParaListagemDeContatoDto> listarTodos(DadosParaPesquisaDeContatosDto dados) {
		return consultaService.listarTodos(dados);
	}
	
	public List<DadosParaListagemDeContatoDto> listarTodos() {
		return consultaService.listarTodos();
	}

	public Optional<DadosParaListagemDeContatoDto> buscarPorId(short contatoId) {
		return consultaService.buscarPorId(contatoId);
	}

	public DadosParaListagemDeContatoDto atualizarCadastro(@Valid DadosParaAtualizacaoDeContatoDto dados) {
		return cadastroService.atualizarCadastro(dados);
	}

	public void excluir(short id) {
		cadastroService.excluir(id);
	}

	public File gerarDOCX() {
		return geradorDeArquivoService.gerar(listarTodos());
	}

	public File gerarDOCX(DadosParaPesquisaDeContatosDto dados) {
		return geradorDeArquivoService.gerar(listarTodos(dados));
	}

}
