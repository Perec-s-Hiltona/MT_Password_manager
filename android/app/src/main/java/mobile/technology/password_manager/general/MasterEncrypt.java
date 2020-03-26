/*
* Class for encrypt and decrypt data
* */

package mobile.technology.password_manager.general;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MasterEncrypt {

    private String AES = "AES";
    private String passwordKey = "passwordKey";
    public static String firstPassword = "TFHfgVHvGv!%@&^!ghFTHfpeld0)-1±>+ujswpQjhgwdqn/d/?,.swbquyk!";

    // encrypt password key
    public String encryptPasswordKey(String passwordKey)throws Exception{

        SecretKeySpec keySpec = generateKey(firstPassword);

        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE,keySpec);

        byte[] encVal = cipher.doFinal(passwordKey.getBytes());
        String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);

        System.out.println("encrypted data : "+ encryptedValue.toString());

        return encryptedValue;
    }

    private SecretKeySpec generateKey(String password) throws Exception {

        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        messageDigest.update(bytes, 0, bytes.length);
        byte[] key = messageDigest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

    public String encryptData(String data, SecretKeySpec encryptPassword)throws Exception{
        // encrypt data

        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, encryptPassword);

        byte[] encVal = cipher.doFinal(passwordKey.getBytes());
        String encData = Base64.encodeToString(encVal,Base64.DEFAULT);

        return  encData;
    }
    /*
    public SecretKeySpec convertStringToSecretKeySpec(String keyStr){

        //TODO

    }
    */
}
