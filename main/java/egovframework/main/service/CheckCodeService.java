package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.CheckCodeVO;
import egovframework.main.service.VO.MemberSearchVO;

public interface CheckCodeService {
	List<CheckCodeVO> getCheckList(MemberSearchVO membersearchVO) throws Exception;
	
	}



