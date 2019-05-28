package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

// Login Data Object

@Repository("userDAO")
public class UserDAO extends EgovAbstractDAO {
	
	// 데이터 삽입 - insert data
	public int insertUser(UserVO userVO) throws Exception{
		return (int) insert("userDAO.insertUser", userVO); // insert() is 'int' type function
	}
	
	// 한개 데이터 검색 - search data
	public UserVO getUserVO(String srvno) throws Exception{
		return (UserVO) select("userDAO.selectUserBySrvno", srvno);
	}
	
	// 전체 데이터 조회 -search all
	public List<UserVO> getAllUserList() throws Exception{
		return null;
	}
	
	public void updateUserErrCnt(UserVO userVO) throws Exception {
		update("userDAO.updateUserErrCnt", userVO);
	}
	
	public void updateUserStatus(UserVO userVO) throws Exception {
		update("userDAO.updateUserStatus", userVO);
	}

	public String getUserStatus(String srvno) throws Exception {
		String status = "";
		UserVO userVO = getUserVO(srvno);		
		status = userVO.getacc_state_info();
		return status;
	}
	
	public void IncErr(String srvno) throws Exception {
		UserVO userVO = getUserVO(srvno);
		int errCount = (Integer.parseInt(userVO.getLogin_err_rtrv().trim()));
		System.out.println("parseInt - errCount : " + userVO.getLogin_err_rtrv().trim() + " -> " + errCount);
		
					
		if( errCount < 5 ) {
			errCount += 1;
			
			userVO.setLogin_err_rtrv(Integer.toString(errCount));		
			System.out.println("parseString - errCount : " + errCount + " -> " + userVO.getLogin_err_rtrv());
			updateUserErrCnt(userVO);
		}				
		
		if( errCount >= 5 ) {
			// 잠금상태로 변경
			if( userVO.getacc_state_info().equals("K1") ) {
				userVO.setacc_state_info("K2");	
				updateUserStatus(userVO);
			}							
		}		
	}
	
	public void InitErrCount(UserVO userVO) throws Exception {
		update("userDAO.updateInitErrCount", userVO);
	}
	
	public int CountErr(String srvno) throws Exception {
		return (int) select("userDAO.selectUserErrCnt", srvno);
	}
	
	public int CountChangePwDate(String srvno) throws Exception {
		UserVO userVO = getUserVO(srvno);
		
		int result = (int) select("userDAO.selectUserChangePwDate", srvno);
		
		if( result >= 30 ) {
			userVO.setacc_state_info("K2");
			updateUserStatus(userVO);
		}
			
		return result;
	}
	
	public void updatePassword(UserVO userVO) throws Exception {
		update("userDAO.updatePassword", userVO);
	}
	
	// add 190326
	public void updateLoginDate(UserVO userVO) throws Exception {
		update("userDAO.updateLoginDate", userVO);
	}
}
