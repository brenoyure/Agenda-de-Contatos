package br.albatross.agenda.mappers;

import static jakarta.ws.rs.core.Response.status;
import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

import br.albatross.agenda.domain.exceptions.SetorCadastroException;
import br.albatross.agenda.infra.security.services.JsonErrorObjectFactory;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class SetorCadastroExceptionMapper implements ExceptionMapper<SetorCadastroException> {

	@Inject
	private JsonErrorObjectFactory jsonErrorFactory;

	@Override
	public Response toResponse(SetorCadastroException exception) {
		return status(BAD_REQUEST)
				.entity(jsonErrorFactory.createObject(exception.getMessage(), "400 - BAD REQUEST"))
				.build();
	}

}
