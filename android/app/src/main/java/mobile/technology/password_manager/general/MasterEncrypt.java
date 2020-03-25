/*
* Class for encrypt and decrypt data
* */

package mobile.technology.password_manager.general;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import mobile.technology.password_manager.ORM.AppSettings;

public class MasterEncrypt {

    private AppSettings appSettings;
    private String passwordKey = "passwordKey";
    public static String firstPassword = "TFHfgVHvGv!%@&^!ghFTHfpeld0)-1±>+ujswpQjhgwdqn/d/?,.swbquyk!";

    public boolean encryptPasswordKey(String passwordKey)throws Exception{

        SecretKeySpec keySpec = generateKey(firstPassword);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,keySpec);
        byte[] encVal = cipher.doFinal(passwordKey.getBytes());
        String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);

        System.out.println("encrypted data : "+ encryptedValue.toString());

        // save encrypted password Key in database
        appSettings = new AppSettings();
        appSettings.setTypePassword(this.passwordKey);
        appSettings.setValuePassword(encryptedValue);
        appSettings.save();

        return true;
    }

    private SecretKeySpec generateKey(String password) throws Exception{
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        messageDigest.update(bytes, 0, bytes.length);
        byte[] key = messageDigest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
}
