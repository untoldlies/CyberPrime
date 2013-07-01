package cyberprime.entities;

public class Sessions {
	
	private String sessionId;
	private String clientId;
	
	
	public Sessions(){
		super();
	}
	
	public Sessions(String sessionId, String clientId) {
		this();
		this.sessionId = sessionId;
		this.clientId = clientId;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	

}
