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

	File myFileSource = new File(filePath);
	File myRenamedFile = new File(filePath + " encrypted");
	File myFileDestination = new File(myRenamedFile + "encryption\\");
	InputStream inStream = null;
	OutputStream outStream = null;

	public String fileEncryption() throws IOException, InterruptedException{
		String file = "";
		try {
				
			file = FileUtils.readFileToString(myFileSource);
			
			// logic check
			System.out.println(myFileSource);
			
			if(!myFileSource.exists()){
				
				System.out.println("File does not exist.");
		           //just exit
		           System.exit(0);
			}
			
			else {
				try{
					
					
					
					if(myFileSource.renameTo(myRenamedFile)){
						
						System.out.println("moving successful");
						myFileSource.delete();
						
					} else {
						
						System.out.println("moving unsuccessful");
						
					}
		            
		           }catch(Exception e){
		           
		        	   e.printStackTrace();
		            //error, just exit
		                System.exit(0);
		           }
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}
	
	public String fileDecryption() throws IOException, InterruptedException{
		
		try{
			StringBuilder appendable = new StringBuilder();
			Reader reader = new FileReader(filePath);
			reader.read(appendable);
			reader.close();

			String readString = appendable.toString();
			
		} catch(IOException ex){
			ex.printStackTrace();
		}
		
		return null;
		
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
