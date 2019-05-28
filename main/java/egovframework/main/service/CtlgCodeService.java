package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.CtlgCodeVO;

public interface CtlgCodeService {
	List<CtlgCodeVO> getActivityCode(CtlgCodeVO ctlgCodeVO) throws Exception;

}
