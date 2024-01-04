package br.albatross.agenda.resource.contato;

import static jakarta.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.status;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;

import java.io.File;
import java.io.IOException;

import org.jboss.resteasy.annotations.cache.Cache;

import br.albatross.agenda.domain.models.contato.DadosParaAtualizacaoDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaCadastroDeNovoContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaListagemDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaPesquisaDeContatosDto;
import br.albatross.agenda.domain.services.ContatoService;
import br.albatross.agenda.domain.services.GeradorDeArquivo;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/contato")
@ApplicationScoped
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RolesAllowed("ADMIN")
@Cache(maxAge = 3600, sMaxAge = 1800, mustRevalidate = true, proxyRevalidate = true)
public class ContatoResource {

	@Inject
	private ContatoService service;

	@Inject
	private GeradorDeArquivo<DadosParaListagemDeContatoDto> gerador;

	private static final String FIRST_PAGE = "1";
	private static final String DEFAULT_RESULTS_PER_PAGE = "5";

	@POST
	public Response cadastrarNovoContato(@Valid DadosParaCadastroDeNovoContatoDto dados) {
		var novoCadastro = service.salvar(dados);
		return Response
				.status(CREATED)
				.entity(novoCadastro)
				.build();
	}

	@GET
	@Path("/docx")
	@Transactional
	@Produces("application/msword")
	public Response gerarDOCX(DadosParaPesquisaDeContatosDto dados) throws IOException {
		var contatos = service.listarTodos(dados);
		File file = gerador.gerar(contatos);
		return status(CREATED)
				.header(CONTENT_DISPOSITION, "attachment; filename=\"agenda.docx\"")
				.entity(file)
				.build();
	}

	@GET
	@Path("/docx")
	@Transactional
	@Produces("application/msword")
	public Response gerarDOCX() throws IOException {
		var contatos = service.listarTodos();
		File file = gerador.gerar(contatos);
		return status(CREATED)
				.header(CONTENT_DISPOSITION, "attachment; filename=\"agenda.docx\"")
				.entity(file)
				.build();
	}

	@GET
	@RolesAllowed({"USER", "ADMIN"})
	public Response listarContatos(@QueryParam("pagina") @DefaultValue(FIRST_PAGE) int pagina, @QueryParam("resultadosPorPagina") @DefaultValue(DEFAULT_RESULTS_PER_PAGE) byte resultadosPorPagina) {
		var listaDeContatos = service.listaPaginada(pagina, resultadosPorPagina);
		return Response
				.ok(listaDeContatos)
				.build();
	}

	@GET
	@RolesAllowed({"USER", "ADMIN"})
	public Response listarContatos(@QueryParam("pagina") @DefaultValue(FIRST_PAGE) int pagina, @QueryParam("resultadosPorPagina") @DefaultValue(DEFAULT_RESULTS_PER_PAGE) byte resultadosPorPagina, DadosParaPesquisaDeContatosDto dadosParaPesquisa) {
		var listaDeContatos = service.listaPaginada(pagina, resultadosPorPagina, dadosParaPesquisa);
		return Response
				.ok(listaDeContatos)
				.build();
	}

	@GET
	@Path("/{id}")
	@RolesAllowed({"USER", "ADMIN"})
	public Response getContatoPeloId(@PathParam("id") short id) {
		var contatoOptional = service.buscarPorId(id);
		return Response.ok(contatoOptional.get()).build();
	}

	@PUT
	@Path("/{id}")
	public Response atualizarContato(@PathParam("id") short id, @Valid DadosParaAtualizacaoDeContatoDto dadosAtualizados) {
		var contato = service.atualizarCadastro(dadosAtualizados);
		return Response
				.ok(contato)
				.build();
	}

	@DELETE
	@Path("/{id}")
	public Response apagarContatoPeloId(@PathParam("id") short id) {
		service.excluir(id);
		return Response
				.status(NO_CONTENT)
				.build();
	}

}
