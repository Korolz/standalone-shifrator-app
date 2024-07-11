package me.korolz.encryptfactory.Services;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

@Component
@Getter
public class KeyGen {

    private SecretKey secretKey;

    public void createKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(128);
        secretKey = keygen.generateKey();
    }
}
