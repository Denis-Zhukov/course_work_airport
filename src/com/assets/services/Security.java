package com.assets.services;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Security {
    public static String hash(String password) {
        StringBuilder result = new StringBuilder();
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            char[] passwordBytes = password.toCharArray();

            String salt = "salt";
            byte[] saltBytes = salt.getBytes();

            PBEKeySpec spec = new PBEKeySpec(passwordBytes, saltBytes, 65536, 512);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            for (byte b : res)
                result.append(String.format("%02X", b));
            return result.toString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
