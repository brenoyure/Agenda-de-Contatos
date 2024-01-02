package br.albatross.agenda.infra.security.services;

import static java.time.LocalDateTime.now;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.time.Instant;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtTokenService {

	private RSAPrivateKey privateKey;

	private static final String ISSUER   = "Albatross18";
	private static final String AUDIENCE = "Agenda.Rest.Service.API";

	private static final long TOKEN_VALID_HOURS = 12L;

	@PostConstruct
	void init() {
		final String configDir = System.getProperty("jboss.server.config.dir");
		final Path keyStore = Path.of(configDir, "agenda.jwt.keystore");
		char[] password = "secret".toCharArray();
		String alias = "agenda-jwt-auth";
		PrivateKey pk = null;
		try (InputStream in = Files.newInputStream(keyStore)) {
			final KeyStore ks = KeyStore.getInstance("PKCS12");
			ks.load(in, password);
			Key key = ks.getKey(alias, password);
			if (key instanceof PrivateKey) {
				pk = (PrivateKey) key;
			}
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}

		privateKey = (RSAPrivateKey) pk;

	}

	public String createJsonWebToken(String username, String role) {
		return JWT
				.create()
				.withSubject(username)
				.withExpiresAt(getExpiresAtInstant())
				.withIssuer(ISSUER)
				.withAudience(AUDIENCE)
				.withClaim("Roles", role)
				.sign(Algorithm.RSA256(privateKey));
	}

	private Instant getExpiresAtInstant() {
		return now().plusHours(TOKEN_VALID_HOURS).toInstant(ZoneOffset.UTC);
	}

}
