package br.albatross.agenda.infra.security.services.jwt;

public record JWTTokenDto(
		String subject, 
		String role, 
		String token, 
		String dataEHoraDeExpiracao
	) {

}
