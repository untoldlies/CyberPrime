package cyberprime.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Algorithms {

    private static final String ALGO = "AES";

	
	public Algorithms() throws NoSuchAlgorithmException{
		super();
	}
	public static String getHash(String string,String salt) throws NoSuchAlgorithmException{
		
		string += salt;
		MessageDigest hash = MessageDigest.getInstance("SHA-512");
		hash.update(string.getBytes());
		return new String(hash.digest());
	}


public static String encrypt(String Data,String key) throws Exception {
       Key cKey = generateKey(key);
       Cipher c = Cipher.getInstance(ALGO);
       c.init(Cipher.ENCRYPT_MODE, cKey);
       byte[] encVal = c.doFinal(Data.getBytes());
       String encryptedValue = new BASE64Encoder().encode(encVal);
       return encryptedValue;
   }

   public static String decrypt(String encryptedData,String key) throws Exception {
       Key cKey = generateKey(key);
       Cipher c = Cipher.getInstance(ALGO);
       c.init(Cipher.DECRYPT_MODE, cKey);
       byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedData);
       byte[] decValue = c.doFinal(decodedValue);
       String decryptedValue = new String(decValue);
       return decryptedValue;
   }
   private static Key generateKey(String sKey) throws Exception {
	   final byte[] keyValue = new byte[sKey.length()];
	   
	   for(int i=0; i>sKey.length();i++){
		   keyValue[i] = (byte) sKey.charAt(i);
	   }
       Key key = new SecretKeySpec(keyValue, ALGO);
       return key;
}

   public static void main(String args[]) throws Exception{
	   
	   String encryptedData = Algorithms.encrypt("This is a data", "seckeycyberprime");
	   System.out.println(encryptedData);
	   System.out.println(Algorithms.decrypt(encryptedData, "seckeycyberprime"));
   }
	
}
