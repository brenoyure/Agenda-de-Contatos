package br.albatross.agenda.infra;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.ok;
import static jakarta.ws.rs.core.Response.status;
import static jakarta.ws.rs.core.Response.Status.FORBIDDEN;

import java.security.GeneralSecurityException;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/password")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@PermitAll
@ApplicationScoped
public class PasswordEndPoint {

	@Inject
	private PasswordService passwordService;
	
	@POST
	public Response digestPassword(Credenciais credenciais) throws GeneralSecurityException {

		String passwordHash = passwordService.createPasswordHashing(credenciais.password());
		boolean valid = passwordService.verifyPassword(passwordHash);

		return valid
				? ok(passwordHash).build() 
				: status(FORBIDDEN).build();
	}

}
