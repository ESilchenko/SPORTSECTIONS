package ru.sportsections;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by esilchenko on 17.02.2016.
 */
public class MD5Digest {

    public static String getPasswordHash(String password) throws NoSuchAlgorithmException {
        String originalPassword = password;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(originalPassword.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
