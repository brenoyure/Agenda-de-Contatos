package br.albatross.agenda.infra.security.exceptions;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.status;
import static jakarta.ws.rs.core.Response.Status.CREATED;

import br.albatross.agenda.domain.models.usuario.DadosParaCriacaoDeUsuarioDto;
import br.albatross.agenda.domain.services.UsuarioService;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/cadastro")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@DenyAll
@ApplicationScoped
public class UsuarioResource {

	@Inject
	private UsuarioService usuarioService;

	@POST
	@Path("/usuario")
	@RolesAllowed("ADMIN")
	public Response cadastrarNovoUsuario(DadosParaCriacaoDeUsuarioDto dadosDoNovoUsuario) {
		var dadosBasicosDoNovoUsuario = usuarioService.criarNovoUsuario(dadosDoNovoUsuario);
		return status(CREATED)
				.entity(dadosBasicosDoNovoUsuario)
				.build();
	}

}
