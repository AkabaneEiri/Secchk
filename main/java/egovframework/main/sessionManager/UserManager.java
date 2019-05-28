package egovframework.main.sessionManager;

import egovframework.main.service.common.UserDAO;

public class UserManager {
	private UserDAO userDAO = null;
	
	public UserManager() {
		userDAO = new UserDAO();
	}
	
	public String getUserStatus(String srvno) throws Exception {
		return userDAO.getUserStatus(srvno);
	}
	
	public void IncErr(String srvno) throws Exception {
		userDAO.IncErr(srvno);
	}
	
	public int CountErr(String srvno) throws Exception {
		return userDAO.CountErr(srvno);
	}
	
	public int CountChangePwDate(String srvno) throws Exception {
		return userDAO.CountChangePwDate(srvno);
	}
}
