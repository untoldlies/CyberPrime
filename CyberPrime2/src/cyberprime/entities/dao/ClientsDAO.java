package cyberprime.entities.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cyberprime.entities.Clients;





public class ClientsDAO {
	
	private static DBController db = new DBController();
	
	
	public static Clients registerClient(Clients clients) {
		Connection currentCon = db.getConnection();
		
		try{
			String query = "insert into Client(imageHash,imageSize,imageExtension,userId,email,pattern) " +
					"values(?,?,?,?,?,?)";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			// inserting values
			pstmt.setString(1, clients.getImageHash());
			pstmt.setInt(2, clients.getImageSize());
			pstmt.setString(3, clients.getImageExtension());
			pstmt.setString(4, clients.getUserId());
			pstmt.setString(5, clients.getEmail());
			pstmt.setString(6, clients.getPattern());
			pstmt.executeUpdate();
		
	}catch (Exception ex) {

			System.out.println("Registration failed: An Exception has occurred! "+ ex);
			clients = null;
			
		}
		finally {

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		return clients;
	}
	
	public static Clients retrieveClient(Clients clients){
		Connection currentCon = db.getConnection();
		Clients client = new Clients();
		ResultSet rs = null;
		
		try{
			String query="select * from Client where imageHash = ?;";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1,clients.getImageHash());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				client.setImageHash(rs.getString(1));
				client.setImageSize(rs.getInt(2));
				client.setImageExtension(rs.getString(3));
				client.setUserId(rs.getString(4));
				client.setEmail(rs.getString(5));
				client.setDBPattern(rs.getString(6));
			}

		}catch(Exception ex){
			ex.printStackTrace();
			client = null;
		}finally {

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		return client;
	}
	
	public static boolean checkUser(Clients client){
		boolean check = false;
		Connection currentCon = db.getConnection();
		ResultSet rs = null;
		Clients c = new Clients();
		try{
			String query = "select userId from Client where userId = ?;";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, client.getUserId());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				c.setUserId(rs.getString(1));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		
		if(c.getUserId()!=null){
			check = true;
		}
		
		else{
			check = false;
		}
		
		return check;
		
	}
	
	public static boolean checkEmail(Clients client){
		boolean check = false;
		Connection currentCon = db.getConnection();
		ResultSet rs = null;
		Clients c = new Clients();
		try{
			String query = "select email from Client where email = ?;";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, client.getEmail());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				c.setEmail(rs.getString(1));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		if(c.getEmail()!=null){
			check = true;
		}
		
		else{
			check = false;
		}
		
		return check;
		
	}
	
	public static Clients updateClients(Clients clients){
		Connection currentCon = db.getConnection();
		
		try{
			String query = "update Client set imageHash = ?, imageSize =?, imageExtension, email=?, pattern = ? where userId= ?";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, clients.getImageHash());
			pstmt.setInt(2, clients.getImageSize());
			pstmt.setString(3,clients.getImageExtension());
			pstmt.setString(4, clients.getEmail());
			pstmt.setString(5,clients.getPattern());
			pstmt.setString(6, clients.getUserId());
			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("Update failed: An Exception has occurred! "+ ex);
			clients = null;
		}		
		finally {

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		return clients;
		
		
	}

	public static void main(String args[]){
		Clients client = new Clients();
		client.setEmail("deanelooiz@hotmail.com");
		System.out.println(checkEmail(client));
		
		client.setUserId("g358a59407hcg96iidae");
		System.out.println(checkUser(client));
	}
}
