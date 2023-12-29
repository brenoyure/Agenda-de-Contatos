package br.albatross.agenda.infra;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public interface DigestAndEncodingMessageService {

	byte[] digestMessage(String plainTextToBeDigested, String algorithm) throws GeneralSecurityException;

	String encodeAndHashDigestedMessage(byte[] digestedMessage);

	byte[] digestMessage(MessageDigest messageDigest, String plainTextToBeDigested);
}

