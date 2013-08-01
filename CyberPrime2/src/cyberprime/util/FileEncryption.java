package cyberprime.util;

import java.io.*;
import java.security.*;
import java.nio.*;

import javax.crypto.*;
import javax.crypto.spec.*;

import org.apache.commons.io.*;

import cyberprime.entities.*;

public class FileEncryption {

	Files files = new Files();
	String filePath = files.getFilePath();
	private File file;
	private String algorithm;

	public FileEncryption(String algorithm, String path){
		this.algorithm = algorithm;
		this.file = new File(files.getFilePath());
	}
	
	public void encrypt() throws Exception{
		//opening streams
		FileInputStream inputStream = new FileInputStream(file);
		file = new File(file.getAbsolutePath()+".enc");
		FileOutputStream outStream = new FileOutputStream(file);
		
		//generating keys
		byte k[] = "HignDIPs".getBytes();
		SecretKeySpec key = new SecretKeySpec(k,algorithm.split("/")[0]);
		
		//creating and initializing cipher and cipher streams
		Cipher encrypt = Cipher.getInstance(algorithm);
		encrypt.init(Cipher.ENCRYPT_MODE, key);
		CipherOutputStream c = new CipherOutputStream(outStream, encrypt);
		
		byte[] buf = new byte[1024];
		
		int read;
		while((read = inputStream.read(buf)) != -1){ //reading data
			c.write(buf, 0 , read); //writing encrypted data
		}
		
		//closing streams
		inputStream.close();
		c.flush();
		c.close();
	}
	
	public void decrypt() throws Exception { 
		//opening Stream
		FileInputStream fis = new FileInputStream(file);
		file = new File(file.getAbsolutePath()+".dec");
		FileOutputStream fos = new FileOutputStream(file);
		
		//generating keys
		byte k[] = "HignDIPs".getBytes();
		SecretKeySpec key = new SecretKeySpec(k, algorithm.split("/")[0]);
		
		//creating and initializing cipher and cipher streams
		Cipher decrypt = Cipher.getInstance(algorithm);
		decrypt.init(Cipher.DECRYPT_MODE, key);
		CipherInputStream cin = new CipherInputStream(fis, decrypt);
		
		byte[] buf = new byte[1024];
		int read = 0;
		while ((read = cin.read(buf)) != -1){
			fos.write(buf, 0 , read);
			
			//close streams
			cin.close();
			fos.flush();
			fos.close();
			
		}

	}
	
	
	public static void main (String[] args) throws Exception{
		new FileEncryptor("DES/ECB/PKCS5Padding",file).encrypt;
	}
	
	
	/*
	 * private String filename;
	 * 
	 * 
	 * public FileEncryption(String filename){ super(); this.filename =
	 * filename; }
	 * 
	 * public String getFilename() { return filename; }
	 * 
	 * public void setFilename(String filename) { this.filename = filename; }
	 * 
	 * protected byte[] getImage() { byte[] result = null; String fileLocation =
	 * filename; File f = new File(fileLocation); result = new
	 * byte[(int)f.length()]; try { FileInputStream in = new
	 * FileInputStream(fileLocation); in.read(result); } catch(Exception ex) {
	 * System.out.println("GET IMAGE PROBLEM :: "+ex); ex.printStackTrace(); }
	 * return result; }
	 * 
	 * 
	 * public int getSize(){ String fileLocation = filename; File f = new
	 * File(fileLocation); long size = f.length(); return (int) size; }
	 * 
	 * public String getExtension(){ String fileLocation = filename; String
	 * extension = ""; int i = fileLocation.lastIndexOf('.'); int p =
	 * Math.max(fileLocation.lastIndexOf('/'), fileLocation.lastIndexOf('\\'));
	 * if(i > p){ extension = fileLocation.substring(i+1); }
	 * 
	 * return extension; }
	 * 
	 * public String getHash() throws NoSuchAlgorithmException{ byte[] data =
	 * getImage(); MessageDigest hash = MessageDigest.getInstance("SHA-512");
	 * hash.update(data); return new String(hash.digest()); }
	 * 
	 * 
	 * public static byte[] encrypt(byte[]data) throws NoSuchAlgorithmException,
	 * NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
	 * BadPaddingException{
	 * 
	 * KeyPair keyPair = initializeKeyPair();
	 * 
	 * final Cipher rsa = Cipher.getInstance("RSA");
	 * rsa.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
	 * 
	 * SecureRandom random = new SecureRandom();
	 * 
	 * final byte[]secretKey = new byte[16]; random.nextBytes(secretKey);
	 * 
	 * Cipher aes = Cipher.getInstance("AES"); SecretKeySpec k = new
	 * SecretKeySpec(secretKey,"AES"); aes.init(Cipher.ENCRYPT_MODE, k);
	 * 
	 * final byte[]ciphedKey = rsa.doFinal(secretKey); final byte[]ciphedData =
	 * aes.doFinal(data);
	 * 
	 * 
	 * return ciphedData; }
	 * 
	 * private static KeyPair initializeKeyPair() throws
	 * NoSuchAlgorithmException { KeyPairGenerator kpg =
	 * KeyPairGenerator.getInstance("RSA"); KeyPair keyPair =
	 * kpg.generateKeyPair(); return keyPair; }
	 */
}
