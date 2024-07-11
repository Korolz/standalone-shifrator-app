package me.korolz.encryptfactory.Services;

import org.apache.tomcat.util.digester.DocumentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class Decryptor {

    private KeyGen keyGen;

    @Autowired
    public Decryptor(KeyGen keyGen) {
        this.keyGen = keyGen;
    }

    public String decrypt(String message) throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey key = keyGen.getSecretKey();
        cipher.init(Cipher.DECRYPT_MODE, key);
        String decryptedMessage = new String(cipher.doFinal(Base64.getUrlDecoder().decode(message)));
        keyGen.createKey();
        return decryptedMessage;
    }
}
