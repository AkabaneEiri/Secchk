package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.CtlgCodeVO;

public interface CtlgCodeService {
	List<CtlgCodeVO> getActivityCode(CtlgCodeVO ctlgCodeVO) throws Exception;

	List<CtlgCodeVO> getAllCtlgCodeList() throws Exception;
	
	List<CtlgCodeVO> getCtlgCodeListByLarge(String large) throws Exception;
	
	List<CtlgCodeVO> getCtlgCodeListByMiddle(String middle) throws Exception;
	
	List<CtlgCodeVO> getCtlgCodeByAllCondition(CtlgCodeVO ctlgCodeVO) throws Exception;
	
	void insertCtlgCodeVO(CtlgCodeVO ctlgCodeVO) throws Exception;
}
