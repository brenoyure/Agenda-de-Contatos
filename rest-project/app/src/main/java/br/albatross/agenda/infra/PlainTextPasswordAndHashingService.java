package br.albatross.agenda.infra;

import static org.wildfly.security.password.PasswordFactory.getInstance;
import static org.wildfly.security.password.interfaces.SimpleDigestPassword.ALGORITHM_SIMPLE_DIGEST_SHA_512;

import java.security.GeneralSecurityException;

import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.SimpleDigestPassword;
import org.wildfly.security.password.spec.ClearPasswordSpec;
import org.wildfly.security.password.spec.HashPasswordSpec;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlainTextPasswordAndHashingService implements PasswordService {

	private boolean valid;
	private String passwordHash;

	private static final String ALGORITHM = "SHA-512";

	@Inject
	private DigestAndEncodingMessageService digestAndEncodingService;

	@Override
	public String createPasswordHashing(String plainTextPassword) throws GeneralSecurityException {
		var digestedPassword = digestAndEncodingService.digestMessage(plainTextPassword, ALGORITHM);
		return digestAndEncodingService.encodeAndHashDigestedMessage(digestedPassword);
	}

	public boolean verifyPassword(String passwordHash) throws GeneralSecurityException {
		var passwordFactory = getInstance(ALGORITHM_SIMPLE_DIGEST_SHA_512, WildFlyElytronPasswordProvider.getInstance());
	    var clearSpec = new ClearPasswordSpec(passwordHash.toCharArray());
	    var original = (SimpleDigestPassword) passwordFactory.generatePassword(clearSpec);

	    byte[] digest = original.getDigest();
	    var hashSpec = new HashPasswordSpec(digest);

	    var restored = passwordFactory.generatePassword(hashSpec).castAs(SimpleDigestPassword.class);

		return this.valid = passwordFactory.verify(restored, passwordHash.toCharArray());
	}

	@Override
	public String getPasswordHash() {
		return passwordHash;
	}

	@Override
	public String getAlgorithm() {
		return ALGORITHM;
	}

	public boolean isValid() {
		return valid;
	}
	

}
