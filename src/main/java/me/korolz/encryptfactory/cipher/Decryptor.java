package me.korolz.encryptfactory.cipher;

import javax.crypto.*;
import java.util.Base64;

public class Decryptor {

    public static String decryptAES(String message, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getUrlDecoder().decode(message)));
    }
}
