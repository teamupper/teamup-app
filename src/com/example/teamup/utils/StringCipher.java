package com.example.teamup.utils;

import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.SecretKeySpec;  

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
  
/** 
 * 
 * @author andres 
 */  
public class StringCipher {  
  
    private String secret = "99HUMANCENTIPEDE"; // secret key length must be 16  
    private SecretKey key;  
    private Cipher cipher;  
    private Base64 coder;  
    
    public StringCipher() {
        byte[] linebreak = {}; // Remove Base64 encoder default linebreak  
        try {  
            key = new SecretKeySpec(secret.getBytes(), "AES");  
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
            coder = new Base64(32, linebreak, true);  
        } catch (Throwable t) {  
            t.printStackTrace();  
        }  
	}

	/** 
     * 
     * @param plainText 
     * @return 
     * @throws Exception 
     */  
    public String encrypt(String plainText) throws Exception {  
        cipher.init(Cipher.ENCRYPT_MODE, key);  
        byte[] cipherText = cipher.doFinal(plainText.getBytes());  
        return new String(coder.encode(cipherText));  
    }  
  
    /** 
     * 
     * @param codedText 
     * @return 
     * @throws Exception 
     */  
    public String decrypt(String codedText) throws Exception {  
        byte[] encypted = coder.decode(codedText.getBytes());  
        cipher.init(Cipher.DECRYPT_MODE, key);  
        byte[] decrypted = cipher.doFinal(encypted);  
        return new String(decrypted);  
    }
}  