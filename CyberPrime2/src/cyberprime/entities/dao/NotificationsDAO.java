package cyberprime.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cyberprime.entities.Notifications;

public class NotificationsDAO {
	
private static DBController db = new DBController();
	
	
	public static Notifications createNotification(Notifications n) {
		Connection currentCon = db.getConnection();
		try{
			String query = "insert into notifications(sender,receiver,content) " +
					"values(?,?,?)";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			// inserting values
			pstmt.setString(1, n.getSender());
			pstmt.setString(2, n.getReceiver());
			pstmt.setString(3, n.getContent());
			pstmt.executeUpdate();
		
	}catch (Exception ex) {

			System.out.println("Create notification failed: An Exception has occurred! "+ ex);
			n= null;
			
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
		return n;
	}
	
	public static ArrayList<Notifications> searchNotifications(String receiver){
		Connection currentCon = db.getConnection();
		ArrayList<Notifications> notifications = new ArrayList<Notifications>();
		ResultSet rs = null;
		
		try{
			String query="select * from notifications where receiver = ?;";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, receiver);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String sender = rs.getString(1);
				String recipient = (rs.getString(2));
				String content = rs.getString(3);
				Notifications n = new Notifications(sender,recipient,content);
				notifications.add(n);
			}

		}catch(Exception ex){
			ex.printStackTrace();
			notifications = null;
		}finally {

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		return notifications;
	}
	
	public static ArrayList<Notifications> searchNotificationsSender(String sender){
		Connection currentCon = db.getConnection();
		ArrayList<Notifications> notifications = new ArrayList<Notifications>();
		ResultSet rs = null;
		
		try{
			String query="select * from notifications where sender = ?;";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, sender);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String sende = rs.getString(1);
				String reciever = (rs.getString(2));
				String content = rs.getString(3);
				Notifications n = new Notifications(sender,reciever,content);
				notifications.add(n);
			}

		}catch(Exception ex){
			ex.printStackTrace();
			notifications = null;
		}finally {

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		return notifications;
	}
	
	public static Notifications deleteNotification(Notifications n){
		Connection currentCon = db.getConnection();
		
		try{
			String query = "delete from notifications where sender = ? and receiver = ? and content = ?";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, n.getSender());
			pstmt.setString(2, n.getReceiver());
			pstmt.setString(3, n.getContent());

			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("Delete failed: An Exception has occurred! "+ ex);
			n = null;
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
		return n;
		
		
	}

	public static void deleteNotifications(){
		Connection currentCon = db.getConnection();
		
		try{
			String query = "delete from notifications";
			PreparedStatement pstmt = currentCon.prepareStatement(query);

			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("Delete failed: An Exception has occurred! "+ ex);
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
		
		
	}
	
	public static void main(String args[]){
		
	}

}
