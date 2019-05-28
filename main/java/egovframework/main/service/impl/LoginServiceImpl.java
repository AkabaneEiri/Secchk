package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.LoginService;
import egovframework.main.service.VO.UserVO;
import egovframework.main.service.common.UserDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService{

	// DAO
	@Resource(name = "userDAO")
	private UserDAO userDAO;
	
	// user data 입력
	public int insertUser(UserVO userVO) throws Exception {
		return userDAO.insertUser(userVO);
	}
	
	// 군번으로 User 검색
	public UserVO getUserVO(String srvno) throws Exception {
		return userDAO.getUserVO(srvno);
	}
	
	// User 리스트 받아오는 함수
	public List<UserVO> getAllUserList() throws Exception {
		// LoginServce 인터페이스 내에 있는 기능 정의
		return null;
	}
	
	public void updateUserErrCnt(UserVO userVO) throws Exception {
		userDAO.updateUserErrCnt(userVO);
	}
	
	public void updateUserStatus(UserVO userVO) throws Exception {
		userDAO.updateUserStatus(userVO);
	}
	
	public String getUserStatus(String srvno) throws Exception {
		return userDAO.getUserStatus(srvno);
	}
	
	public void IncErr(String srvno) throws Exception {
		userDAO.IncErr(srvno);
	}
	
	public void InitErrCount(UserVO userVO) throws Exception {
		userDAO.InitErrCount(userVO);
	}
	
	public int CountErr(String srvno) throws Exception {
		return userDAO.CountErr(srvno);
	}
	
	public int CountChangePwDate(String srvno) throws Exception {
		return userDAO.CountChangePwDate(srvno);
	}
	
	public void updatePassword(UserVO userVO) throws Exception {
		userDAO.updatePassword(userVO);
	}
	
	public void updateLoginDate(UserVO userVO) throws Exception {
		userDAO.updateLoginDate(userVO);
	}
}
