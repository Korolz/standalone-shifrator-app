package me.korolz.encryptfactory.cipher;

import javax.crypto.*;
import java.security.PrivateKey;
import java.util.Base64;

public class Decryptor {

    public static String decryptAES(String message, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getUrlDecoder().decode(message)));
    }

    public static String decryptRSA(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getUrlDecoder().decode(encryptedText)));
    }
}
