package cyberprime.entities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Clients {
	
	private String userId;
	private String imageHash;
	private int imageSize;
	private String imageExtension;
	private String email;
	private String pattern;
	
	
	public Clients(){
		super();
	}
	
	public Clients(String userId, String imageHash, int imageSize, String imageExtension, String email, String pattern) {
		this();
		this.userId = userId;
		this.imageHash = imageHash;
		this.imageSize = imageSize;
		this.imageExtension = imageExtension;
		this.email = email;
		this.pattern = pattern;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getImageHash() {
		return imageHash;
	}
	public void setImageHash(String imageHash) {
		this.imageHash = imageHash;
	}
	public int getImageSize() {
		return imageSize;
	}
	public void setImageSize(int imageSize) {
		this.imageSize = imageSize;
	}
	public String getImageExtension() {
		return imageExtension;
	}
	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPattern() {
		return pattern;
	}
	
	public void setDBPattern(String pattern){
		this.pattern = pattern;
	}
	public void setPattern(String pattern) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.pattern = getHash(pattern,this.userId);
	}
	
	public static String getHash(String pattern,String salt) throws NoSuchAlgorithmException{
		
		pattern += salt;
		MessageDigest hash = MessageDigest.getInstance("SHA-512");
		hash.update(pattern.getBytes());
		return new String(hash.digest());
	}
	
	

}
