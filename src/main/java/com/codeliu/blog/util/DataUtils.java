package com.codeliu.blog.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.SecureRandom;

/**
 * The class of data encryption.
 */
public class DataUtils {

    /**
     * MD5 salt encryption method, after passing in the password and salt value,
     * calculate the corresponding hash value and return the result
     *
     * @param str Incoming password
     * @param salt Incoming salt value
     * @return Hash salt result
     */
    public static String getMD5Str(String str, String salt) {
        return new SimpleHash("MD5", str, ByteSource.Util.bytes(salt), 1).toString();
    }

    /**
     * The function of randomly generating salt value
     * should be called to obtain the corresponding salt value
     * and saved in the database when the user changes the password or registers the account.
     *
     * No need to generate it again when logging in for verification in the future,
     * and read it directly from the database
     * @return
     */
    public static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] seed = new byte[20];
        random.nextBytes(seed);
        String salt = Hex.encodeHexString(seed);
        return salt;
    }

    public static void main(String[] args) {
        // Make a salt
        String salt = getSalt();
        String password = getMD5Str("123456", salt);
        System.out.println("salt = " + salt);
        System.out.println("password = " + password);
    }
}
