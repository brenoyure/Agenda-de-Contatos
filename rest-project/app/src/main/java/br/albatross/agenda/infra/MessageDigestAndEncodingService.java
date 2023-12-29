package br.albatross.agenda.infra;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Base64;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageDigestAndEncodingService implements DigestAndEncodingMessageService {

	@Override
	public byte[] digestMessage(String plainTextMessage, String algorithm) throws GeneralSecurityException {
		var messageDigest = MessageDigest.getInstance(algorithm);
		return digestMessage(messageDigest, plainTextMessage);
	}

	@Override
	public String encodeAndHashDigestedMessage(byte[] digestedMessage) {
		return Base64.getMimeEncoder().encodeToString(digestedMessage);
	}

	@Override
	public byte[] digestMessage(MessageDigest messageDigest, String plainTextPasswordToBeDigested) {
		return messageDigest.digest(plainTextPasswordToBeDigested.getBytes());
	}
}
