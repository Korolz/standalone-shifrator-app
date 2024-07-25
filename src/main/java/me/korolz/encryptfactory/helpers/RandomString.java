package me.korolz.encryptfactory.helpers;

import java.security.SecureRandom;

public class RandomString {

    static final String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-._~";
    static SecureRandom rnd = new SecureRandom();

    public static String create(){
        int len = 20;
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(str.charAt(rnd.nextInt(str.length())));
        return sb.toString();
    }
}
