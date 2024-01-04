package br.albatross.agenda.infra.security.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RequestScoped
public class JsonErrorObjectFactory {

	public JsonObject createObject(String message) {
		return Json
				.createObjectBuilder()
					.add("message", message)
					.build();
	}

	public JsonObject createObject(String message, String code) {
		return Json
					.createObjectBuilder()
						.add("code", code)
						.add("message", message)
						.build();

	}

	public JsonObject createObject(String message, String code, String... properties) {
		var arrayBuilder = Json.createArrayBuilder();
		for (var prop : properties) {
			arrayBuilder.add(prop);
		}
		var jsonArray = arrayBuilder.build();

		return Json
					.createObjectBuilder()
						.add("code", code)
						.add("message", message)
						.add("properties", jsonArray)
						.build();

	}

}
