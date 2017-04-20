package com.common.utils;


import com.google.common.io.BaseEncoding;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class CryptoUtils {
    private static SecureRandom SECURE_RANDOM = new SecureRandom();

    public static SecureRandom getSecureRandom() {
        return SECURE_RANDOM;
    }

    public static String aesEncrypt(String key, String value, String initVector) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return BaseEncoding.base64().encode(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String aesDecrypt(String key, String encrypted, String initVector) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(BaseEncoding.base64().decode(encrypted));

            return new String(original);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String hmacSha1(String key, String content) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(keySpec);
            byte[] result = mac.doFinal(content.getBytes("UTF-8"));
            return BaseEncoding.base64().encode(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String bcrypt(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
