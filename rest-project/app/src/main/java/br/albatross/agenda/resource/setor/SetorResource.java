package br.albatross.agenda.resource.setor;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;

import org.jboss.resteasy.annotations.cache.Cache;

import br.albatross.agenda.domain.models.setor.DadosParaAtualizacaoDeSetorDto;
import br.albatross.agenda.domain.models.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.domain.services.setor.SetorService;
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

@Path("/setor")
@ApplicationScoped
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RolesAllowed("ADMIN")
@Cache(maxAge = 3600, sMaxAge = 1800, mustRevalidate = true, proxyRevalidate = true)
public class SetorResource {

	@Inject
	private SetorService service;

	private static final String FIRST_PAGE = "1";
	private static final String DEFAULT_RESULTS_PER_PAGE = "5";	

	@POST
	@Transactional
	public Response cadastrarNovoSetor(@Valid DadosParaCadastroDeNovoSetor dados) {
		var novoCadastro = service.salvar(dados);
		return Response
				.status(CREATED)
				.entity(novoCadastro)
				.build();
	}

	@GET
	@RolesAllowed({"USER", "ADMIN"})
	public Response listarSetores(@QueryParam("pagina") @DefaultValue(FIRST_PAGE) int pagina, @QueryParam("resultadosPorPagina") @DefaultValue(DEFAULT_RESULTS_PER_PAGE) byte resultadosPorPagina) {
		var listaDeSetores = service.listaPaginada(pagina, resultadosPorPagina);
		return Response
				.ok(listaDeSetores)
				.build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Response atualizarSetor(@PathParam("id") int id, @Valid DadosParaAtualizacaoDeSetorDto dadosAtualizados) {
		var contato = service.atualizarCadastro(dadosAtualizados);
		return Response
				.ok(contato)
				.build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response apagarSetorPeloId(@PathParam("id") int id) {
		service.excluir(id);
		return Response
				.status(NO_CONTENT)
				.build();
	}	

}
