package cyberprime.entities.dao;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBController {
	
	private Connection con;
	private String dbSource="//localhost:3306/Cyberprime";
	private String user="myuser";
	private String password="0000";
	
	
	public DBController(){
		super();
		try {
			testDriver();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void testDriver() throws Exception {
		System.out.println("Initializing Server... "); 
	try { 
		Class.forName("com.mysql.jdbc.Driver"); 
		System.out.println(" Driver Found."); 
	} 
	catch (ClassNotFoundException e) { 
		System.out.println(" Driver Not Found, exiting.."); 
		throw (e); 
		} 
	
	}
	
	public Connection getConnection(){ 

		String url = ""; 
		try { 
			url = "jdbc:mysql:"+dbSource; 
			con=DriverManager.getConnection(url,user,password);
			System.out.println("Successfully connected to " + dbSource+ "."); 
		} 
		catch (java.sql.SQLException e) { 
			System.out.println("Connection failed ->"+ dbSource); 
			System.out.println(e); 
		} 
		return con;
	} 
	
	public void terminate() {
		// close connection
		try {
			con.close();
			System.out.println("Connection is closed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
