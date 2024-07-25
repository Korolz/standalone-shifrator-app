package me.korolz.encryptfactory.cipher;

import lombok.Getter;
import lombok.Setter;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyGen {

    @Getter
    @Setter
    private static SecretKey innerKey;

    public static SecretKey createSecretKey128() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(128);
        return keygen.generateKey();
    }
}
