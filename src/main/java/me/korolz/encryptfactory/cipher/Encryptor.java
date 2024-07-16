package me.korolz.encryptfactory.cipher;

import javax.crypto.*;
import java.security.PublicKey;
import java.util.Base64;

public class Encryptor {

    public static String encryptAES(String message, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getUrlEncoder().encodeToString(cipher.doFinal(message.getBytes()));
    }

    public static String encryptRSA(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getUrlEncoder().encodeToString(cipher.doFinal(message.getBytes()));
    }
}
