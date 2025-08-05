package com.ingress_track.util;
import java.security.SecureRandom;
import java.util.Base64;

public class SecretGenerator {

    public static String generateSecret() {
        byte[] secret = new byte[32];
        new SecureRandom().nextBytes(secret);
        return Base64.getEncoder().encodeToString(secret);
    }
}
