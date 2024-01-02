package br.albatross.agenda.infra.security.services.password;

import static org.wildfly.security.password.PasswordFactory.getInstance;
import static org.wildfly.security.password.interfaces.SimpleDigestPassword.ALGORITHM_SIMPLE_DIGEST_SHA_512;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.SimpleDigestPassword;
import org.wildfly.security.password.spec.ClearPasswordSpec;
import org.wildfly.security.password.spec.HashPasswordSpec;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class PlainTextPasswordAndHashingService implements PasswordService {

	private static final String ALGORITHM = "SHA-512";

	@Override
	public String generateHashing(String plainTextPassword) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			byte[] digestedPassword = messageDigest.digest(plainTextPassword.getBytes());
			return Base64.getEncoder().encodeToString(digestedPassword);

		} catch (NoSuchAlgorithmException e) { throw new RuntimeException(e); }

	}

	@Override
	public boolean verifyPassword(String encodedPassword) {
		try {
			var passwordFactory = getInstance(ALGORITHM_SIMPLE_DIGEST_SHA_512, WildFlyElytronPasswordProvider.getInstance());
		    var clearSpec = new ClearPasswordSpec(encodedPassword.toCharArray());

		    var original = passwordFactory.generatePassword(clearSpec).castAs(SimpleDigestPassword.class);

		    var digest = original.getDigest();
		    var hashSpec = new HashPasswordSpec(digest);

		    var restored = passwordFactory.generatePassword(hashSpec).castAs(SimpleDigestPassword.class);

		    return passwordFactory.verify(restored, encodedPassword.toCharArray());

		} catch (GeneralSecurityException e) { throw new RuntimeException(e); }

	}

}
