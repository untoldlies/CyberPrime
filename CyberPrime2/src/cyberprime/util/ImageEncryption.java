package cyberprime.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class ImageEncryption {

	private String filename;
	
	
	public ImageEncryption(String filename){
		super();
		this.filename = filename;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	protected byte[] getImage() {
		byte[] result = null;
		String fileLocation = filename;
		File f = new File(fileLocation);
		result = new byte[(int)f.length()];
		try {
		FileInputStream in = new FileInputStream(fileLocation);
		in.read(result);
		}
		catch(Exception ex) {
		System.out.println("GET IMAGE PROBLEM :: "+ex);
		ex.printStackTrace();
		}
		return result;
		}
	

	public int getSize(){
		String fileLocation = filename;
		File f = new File(fileLocation);
		long size = f.length();
		return (int) size;
	}
	
	public String getExtension(){
		String fileLocation = filename;
		String extension = "";
		int i = fileLocation.lastIndexOf('.');
		int p = Math.max(fileLocation.lastIndexOf('/'), fileLocation.lastIndexOf('\\'));
		if(i > p){
			extension = fileLocation.substring(i+1);
		}
		
		return extension;
	}

	public String getHash() throws NoSuchAlgorithmException{
		byte[] data = getImage();
		MessageDigest hash = MessageDigest.getInstance("SHA-512");
		hash.update(data);
	    String encodedValue = new BASE64Encoder().encode(hash.digest());
		return encodedValue;
	}
	
	
	public static byte[] encrypt(byte[]data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		
		KeyPair keyPair = initializeKeyPair();
		
		final Cipher rsa = Cipher.getInstance("RSA");
		rsa.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
		
		SecureRandom random = new SecureRandom();
		
		final byte[]secretKey = new byte[16];
		random.nextBytes(secretKey);
		
		Cipher aes = Cipher.getInstance("AES");
		SecretKeySpec k = new SecretKeySpec(secretKey,"AES");
		aes.init(Cipher.ENCRYPT_MODE, k);
		
		final byte[]ciphedKey = rsa.doFinal(secretKey);
		final byte[]ciphedData = aes.doFinal(data);
		
		
		return ciphedData;
	}

	private static KeyPair initializeKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg =  KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair = kpg.generateKeyPair();
		return keyPair;
	}
	
}
