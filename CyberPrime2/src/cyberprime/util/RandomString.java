package cyberprime.util;

public class RandomString {

	private static int length = 20;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(generateRandomString());

	}
	
	public static String generateRandomString(){
		
		StringBuffer buffer = new StringBuffer();
		String characters = "";
		
		characters = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		for(int i=0; i<length; i++){
			
			double index = Math.random() * length;
			buffer.append(characters.charAt((int)index));
		}
		
		return buffer.toString();
		
	}

}
