package br.albatross.agenda.mappers;

import static jakarta.ws.rs.core.Response.status;
import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

import br.albatross.agenda.infra.security.exceptions.UsuarioExistenteException;
import jakarta.json.Json;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UsuarioExistenteExceptionMapper implements ExceptionMapper<UsuarioExistenteException> {

	@Override
	public Response toResponse(UsuarioExistenteException exception) {
		var objetoErro = Json
				.createObjectBuilder()
					.add("property", "username")
					.add("message", "Já existe um usuário cadastrado com o nome informado.")
					.add("code", "400 - Already Exists")
					.build();

		return status(BAD_REQUEST)
				.entity(objetoErro)
				.build();
	}

}
