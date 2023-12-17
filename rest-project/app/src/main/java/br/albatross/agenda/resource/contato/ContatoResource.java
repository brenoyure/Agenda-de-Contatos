package br.albatross.agenda.resource.contato;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;

import br.albatross.agenda.domain.models.contato.DadosParaAtualizacaoDeContatoDto;
import br.albatross.agenda.domain.models.contato.DadosParaCadastroDeNovoContatoDto;
import br.albatross.agenda.domain.services.ContatoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/contato")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ContatoResource {

	@Inject
	private ContatoService service;

	@POST
	public Response cadastrarNovoContato(@Valid DadosParaCadastroDeNovoContatoDto dados) {
		var novoCadastro = service.salvar(dados);
		return Response
				.status(CREATED)
				.entity(novoCadastro)
				.build();
	}

	@GET
	public Response listarContatos(@QueryParam("pagina") int pagina, @QueryParam("resultadosPorPagina") byte resultadosPorPagina) {
		var listaDeContatos = service.listar(pagina, resultadosPorPagina);
		return Response
				.ok(listaDeContatos)
				.build();
	}

	@GET
	@Path("/{id}")
	public Response getContatoPeloId(@PathParam("id") short id) {
		var contatoOptional = service.buscarPorId(id);
		return contatoOptional.isEmpty() ? Response.status(NOT_FOUND).build() : Response.ok(contatoOptional.get()).build();
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
