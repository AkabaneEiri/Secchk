package egovframework.main.sessionManager;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionStateListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("session create");
		System.out.println("session max time : " + event.getSession().getMaxInactiveInterval());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("session timeout");
	}

}
