package com.myCompany.RepairAgency.servlet.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

public class Encrypt {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * Encrypt String from param, use for passwords encryption.
     * @param s String that will be encrypted.
     * @return Encrypt value.
     */
    public static String encrypt(String s) {
//        SecureRandom random = new SecureRandom();
        byte[] salt;
//        random.nextBytes(salt);
//        System.out.println(Arrays.toString(salt));
//        salt = new byte[]{-106, -22, -128, -26, -60, -68, -87, -87, -64, -84, -116, -71, -37, 77, 73, 58};
        salt = new byte[]{-35, 68, 23, 18, 38, -116, -72, -15, -88, -15, 42, 36, -70, -126, -23, 22, 30, 43,
                36, 27, 86, -106, -52, 7, -8, 50, -27, 77, 114, -124, -26};

        KeySpec spec = new PBEKeySpec(s.toCharArray(), salt, 128, 252);
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return bytesToHex(factory.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }


    /**
     * Generate String with random letters
     */
    public static String generateCode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
