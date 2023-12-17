package br.albatross.agenda.mappers;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

import jakarta.json.Json;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {

	@Override
	public Response toResponse(EntityNotFoundException exception) {
		return Response
				.status(NOT_FOUND)
				.entity(Json
						.createObjectBuilder().add("error", "Entidade com o Id informado não encontrada")
						.build())
				.build();
	}

}
