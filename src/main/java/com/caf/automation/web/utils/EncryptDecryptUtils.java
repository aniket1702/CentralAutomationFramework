package com.caf.automation.web.utils;

import org.apache.commons.codec.binary.Base64;


/**
 * The EncryptDecryptUtils class provides utility methods for encryption and decryption.
 */
public final class EncryptDecryptUtils {

    private EncryptDecryptUtils() {
    }

    private static final Base64 BASE_64 = new Base64();


    /**
     * Decrypts the input string using Base64 decoding.
     *
     * @param textToDecrypt The string to be decrypted.
     * @return The decrypted string.
     */
    public static String decryptString(String textToDecrypt) {
        return new String(BASE_64.decode(textToDecrypt.getBytes()));
    }


}
