package cyberprime.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cyberprime.entities.Clients;
import cyberprime.entities.Sessions;

public class SessionsDAO {

	
private static DBController db = new DBController();
	
	
	public static Sessions createSession(Sessions session) {
		Connection currentCon = db.getConnection();
		try{
			String query = "insert into sessions(sessionid,clientid) " +
					"values(?,?)";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			// inserting values
			pstmt.setString(1, session.getSessionId());
			pstmt.setString(2, session.getClientId());
			pstmt.executeUpdate();
		
	}catch (Exception ex) {

			System.out.println("Create session failed: An Exception has occurred! "+ ex);
			session = null;
			
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
		return session;
	}
	
	public static Sessions searchSessions(Sessions session){
		Connection currentCon = db.getConnection();
		Sessions s = new Sessions();
		ResultSet rs = null;
		
		try{
			String query="select * from sessions where clientId = ?;";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1,session.getClientId());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				s.setSessionId(rs.getString(1));
				s.setClientId(rs.getString(2));
			}

		}catch(Exception ex){
			ex.printStackTrace();
			s = null;
		}finally {

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		return s;
	}
	
	public static Sessions deleteSession(Sessions session){
		Connection currentCon = db.getConnection();
		
		try{
			String query = "delete from sessions where sessionId = ?";
			PreparedStatement pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, session.getSessionId());

			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("Delete failed: An Exception has occurred! "+ ex);
			session = null;
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
		return session;
		
		
	}

}
