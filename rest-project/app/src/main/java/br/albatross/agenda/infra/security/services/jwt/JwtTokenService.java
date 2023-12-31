package br.albatross.agenda.infra.security.services.jwt;

import static java.lang.String.format;
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
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class JwtTokenService {

	private RSAPrivateKey privateKey;

	private static final String ISSUER   = "Albatross18";
	private static final String AUDIENCE = "Agenda.Rest.Service.API";
	private static final String ROLES_CLAIM = "Roles";

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

	public JWTTokenDto createJsonWebToken(String username, String role) {
		var dataEHoraDeExpiracao = Date.from(getExpiresAtInstant());
		var token = JWT
						.create()
						.withSubject(username)
						.withExpiresAt(getExpiresAtInstant())
						.withIssuer(ISSUER)
						.withAudience(AUDIENCE)
						.withClaim(ROLES_CLAIM, role)
						.sign(Algorithm.RSA256(privateKey));

		return new JWTTokenDto(username, role, format("Bearer %s", token), dataEHoraDeExpiracao.toString());
	}

	private Instant getExpiresAtInstant() {
		return now().plusHours(TOKEN_VALID_HOURS).toInstant(ZoneOffset.UTC);
	}

}
