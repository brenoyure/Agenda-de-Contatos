package br.albatross.agenda.infra.security.resource;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

import br.albatross.agenda.infra.security.credentials.Credenciais;
import br.albatross.agenda.infra.security.services.LoginService;
import br.albatross.agenda.infra.security.services.jwt.JwtTokenService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@PermitAll
public class LoginResource {

	@Inject
	private LoginService service;
	
	@Inject
	private JwtTokenService tokenService;

	@POST
	public Response logarUsuario(@Valid Credenciais credenciais) {
		var dto = service.logar(credenciais);
		return dto != null ? 
						Response
							.status(CREATED)
							.entity(tokenService.createJsonWebToken(dto.nomeDoUsuario(), dto.role()))
							.build():
						Response
							.status(UNAUTHORIZED)
							.build();
	}

}
