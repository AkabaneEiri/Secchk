package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.UserVO;

//컨트롤러에서 사용할 함수 목록
public interface LoginService {
	
	// insert User
	int insertUser(UserVO userVO) throws Exception;
	
	// search User
	UserVO getUserVO(String srvno) throws Exception;
	
	// search all
	List<UserVO> getAllUserList() throws Exception;
	
	// update err cnt
	void updateUserErrCnt(UserVO userVO) throws Exception;
	
	// update user stat
	void updateUserStatus(UserVO userVO) throws Exception;
	
	// get userStatus
	String getUserStatus(String srvno) throws Exception;
	
	// ++errCount
	void IncErr(String srvno) throws Exception;
	
	// init err cnt
	void InitErrCount(UserVO userVO) throws Exception;
	
	// get errCount
	int CountErr(String srvno) throws Exception;
	
	// get term from change date
	int CountChangePwDate(String srvno) throws Exception;
	
	// change pw
	void updatePassword(UserVO userVO) throws Exception;
	
	// update login date
	void updateLoginDate(UserVO userVO) throws Exception;
}
