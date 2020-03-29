/*
* Class for encrypt and decrypt data
* */

package mobile.technology.password_manager.general;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MasterEncrypt {

    private String zeroPassword = "TFHfgVHvGv!%@&^!ghFTHfpeld0)-1±>+ujswpQjhgwdqn/d/?,.swbquyk!";

    public String encryptData(String data, String password) throws Exception{
        SecretKeySpec key = generateKey(password);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encVal = cipher.doFinal(data.getBytes());
        String encryptedVal = Base64.encodeToString(encVal,Base64.DEFAULT);
        return encryptedVal;
    }

    public String decryptData(String data, String password) throws Exception{
        SecretKeySpec key = generateKey(password);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,key);

        byte[] decodedVal = Base64.decode(data, Base64.DEFAULT);
        byte[] decVal = cipher.doFinal(decodedVal);

        String decryptedVal = new String(decVal);
        return decryptedVal;
    }

    private SecretKeySpec generateKey(String password) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

    public String getZeroPassword(){
        return this.zeroPassword;
    }
}
