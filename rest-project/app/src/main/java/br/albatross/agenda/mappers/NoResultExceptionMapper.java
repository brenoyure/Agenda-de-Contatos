package br.albatross.agenda.mappers;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

import jakarta.persistence.NoResultException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NoResultExceptionMapper implements ExceptionMapper<NoResultException> {

	@Override
	public Response toResponse(NoResultException exception) {
		return Response.status(NOT_FOUND).build();
	}

}
