package br.albatross.agenda.resource.contato;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;

import br.albatross.agenda.domain.models.contato.DadosParaCadastroDeNovoContatoDto;
import br.albatross.agenda.domain.services.ContatoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/contato")
public class ContatoResource {

	@Inject
	private ContatoService service;

	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response cadastrarNovoContato(@Valid DadosParaCadastroDeNovoContatoDto dados) {
		var novoCadastro = service.salvar(dados);
		return Response.ok(novoCadastro).status(Status.CREATED).build();
	}

	@GET
	@Produces(APPLICATION_JSON)
	public Response listarContatos(@QueryParam("pagina") int pagina, @QueryParam("resultadosPorPagina") int resultadosPorPagina) {
		return Response.ok(service.listar(pagina, resultadosPorPagina)).build();
	}

	@GET
	@Path("/{id}")
	@Produces(APPLICATION_JSON)
	public Response getContatoPeloId(@QueryParam("id") int id) {
		var contatoOptional = service.buscarPorId(id);
		return contatoOptional.isEmpty() ? Response.status(NO_CONTENT).build() : Response.ok(contatoOptional.get()).build();
	}

}
