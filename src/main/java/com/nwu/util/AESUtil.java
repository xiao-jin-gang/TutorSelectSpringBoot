package com.nwu.util;

/**
 * @author Rex Joush
 * @time 2021.09.08 20:01
 */


import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * AES 的加解密算法
 */
public class AESUtil {

    // 可配置到Constant中，并读取配置文件注入,16位,自己定义
    private static final String KEY = "tutor-select-sys";

    // 参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMS = "AES/ECB/PKCS5Padding";

    /**
     * aes 加密函数
     * @param plainText 明文
     * @return 密文
     */
    public static String encode(String plainText) {

        byte[] b = new byte[256];
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            Cipher cipher = Cipher.getInstance(ALGORITHMS);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
            b = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);


    }

    /**
     * aes 解密函数
     * @param encryptText 密文
     * @return 明文
     */
    public static String decode(String encryptText) {

        byte[] decryptBytes = new byte[256];
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            Cipher cipher = Cipher.getInstance(ALGORITHMS);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));

            // 采用 base64 算法进行转码, 避免出现中文乱码
            byte[] encryptBytes = Base64.decodeBase64(encryptText);
            decryptBytes = cipher.doFinal(encryptBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decryptBytes);
    }

}
