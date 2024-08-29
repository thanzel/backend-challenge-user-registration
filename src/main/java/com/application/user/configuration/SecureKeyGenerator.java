package com.application.user.configuration;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class SecureKeyGenerator {
    /**
     * Genera una clave secreta para poder generar un token
     * @return secretKey
     * @throws Exception
     */
    public String generateKey() throws Exception {

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());

    }

}
