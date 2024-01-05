package br.albatross.agenda.resource;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;

import org.jboss.resteasy.annotations.cache.Cache;

import br.albatross.agenda.domain.models.setor.DadosParaCadastroDeNovoSetor;
import br.albatross.agenda.domain.services.setor.SetorService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

	@POST
	@Transactional
	public Response cadastrarNovoContato(@Valid DadosParaCadastroDeNovoSetor dados) {
		var novoCadastro = service.salvar(dados);
		return Response
				.status(CREATED)
				.entity(novoCadastro)
				.build();
	}

}
