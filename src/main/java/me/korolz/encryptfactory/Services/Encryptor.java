package me.korolz.encryptfactory.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class Encryptor {

    private KeyGen keyGen;

    @Autowired
    public Encryptor(KeyGen keyGen) {
        this.keyGen = keyGen;
    }

    public String encrypt(String message) throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        keyGen.createKey();
        SecretKey key = keyGen.getSecretKey();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getUrlEncoder().encodeToString(cipher.doFinal(message.getBytes()));
    }
}
