package br.albatross.agenda.mappers;

import static jakarta.ws.rs.core.Response.status;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

import br.albatross.agenda.infra.security.exceptions.UsuarioOuSenhaIncorretaException;
import br.albatross.agenda.infra.security.services.JsonErrorObjectFactory;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UsuarioOuSenhaIncorretaExceptionMapper implements ExceptionMapper<UsuarioOuSenhaIncorretaException> {

	@Inject
	private JsonErrorObjectFactory jsonErrorFactory;

	@Override
	public Response toResponse(UsuarioOuSenhaIncorretaException exception) {
		return status(UNAUTHORIZED)
				.entity(jsonErrorFactory.createObject(exception.getMessage(), "401 - Unauthorized"))
				.build();
	}

}
