package me.korolz.encryptfactory.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;

@Component
public class Encrypter {

    private Cipher cipher;
    @Autowired
    public Encrypter() {
        cipher = Cipher.
    }
}
