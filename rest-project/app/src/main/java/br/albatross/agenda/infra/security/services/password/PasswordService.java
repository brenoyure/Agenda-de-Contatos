package br.albatross.agenda.infra.security.services.password;

public interface PasswordService {

	String generateHashing(String plainTextPassword);
	boolean verifyPassword(String plainTextPassword);
}
