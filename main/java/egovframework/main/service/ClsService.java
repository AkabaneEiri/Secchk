package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.ClsVO;

public interface ClsService {
	void insertClsVO(ClsVO clsVO) throws Exception;
	
	List<ClsVO> getAllClsList() throws Exception;
}
