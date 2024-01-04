package br.albatross.agenda.infra.security.resource;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.status;
import static jakarta.ws.rs.core.Response.Status.CREATED;

import br.albatross.agenda.infra.security.credentials.Credenciais;
import br.albatross.agenda.infra.security.exceptions.UsuarioOuSenhaIncorretaException;
import br.albatross.agenda.infra.security.services.authentication.LoginService;
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
	public Response logarUsuario(@Valid Credenciais credenciais) throws UsuarioOuSenhaIncorretaException {
		var dto = service.logar(credenciais);
		return status(CREATED)
				.entity(tokenService.createJsonWebToken(dto.nomeDoUsuario(), dto.role()))
				.build();
	}

}
