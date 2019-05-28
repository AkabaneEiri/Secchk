package egovframework.main.sessionManager;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionManager implements HttpSessionBindingListener {
	
	private static SessionManager sessionManager = null;
	
	@SuppressWarnings("rawtypes")
	private static Hashtable loginUsers = new Hashtable();
	
	private SessionManager() {
		
		super();
		
	}
	
	public static synchronized SessionManager getInstance() {
		if(sessionManager == null) {
			
			sessionManager = new SessionManager();
			
		}
		
		return sessionManager;
	}
	
	@SuppressWarnings("unchecked")
	public void valueBound(HttpSessionBindingEvent event) {
		
		loginUsers.put(event.getSession(), event.getName());
						
	}
	
	public void valueUnbound(HttpSessionBindingEvent event) {
		
		loginUsers.remove(event.getSession());
				
	}
	
	@SuppressWarnings("rawtypes")
	public boolean isUsing(HttpSession sess, String srvno) {
		
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		boolean yn = false;
		
		while(e.hasMoreElements()) {
			
			session = (HttpSession)e.nextElement();
						
			if(loginUsers.get(session).equals(srvno)) {
				
				if(session == sess) {
					
					yn = false;
					
				} else {
				
					yn = true;
					
				}				
				
			} 
			
		}	
		
		return yn;
				
	}
	
	public void setSession(HttpSession session, String srvno) {
		
		session.setAttribute(srvno, this);
		
		session.setAttribute("id", srvno);

	}
@SuppressWarnings("rawtypes")
	public void removeSession(String srvno) {
		
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		
		while(e.hasMoreElements()) {
			
			session = (HttpSession)e.nextElement();
			
			if(loginUsers.get(session).equals(srvno)) {
				
				
				session.invalidate();
				
			}
			
		}
				
	}
		
	public String getSrvno(String sessionID) {
		
		return (String)loginUsers.get(sessionID);
		
	}
	
}
