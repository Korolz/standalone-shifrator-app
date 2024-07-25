package me.korolz.encryptfactory.cipher;

import javax.crypto.*;
import java.util.Base64;

public class Encryptor {

    public static String encryptAES(String message, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getUrlEncoder().encodeToString(cipher.doFinal(message.getBytes()));
    }
}
