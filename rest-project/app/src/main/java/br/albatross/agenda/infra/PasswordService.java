package br.albatross.agenda.infra;

import java.security.GeneralSecurityException;

public interface PasswordService {

	String createPasswordHashing(String plainTextPassword) throws GeneralSecurityException;
	String getPasswordHash();
	String getAlgorithm();
	boolean verifyPassword(String passwordHash) throws GeneralSecurityException;

}
