package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.CheckApprovalVO;
import egovframework.main.service.VO.RequestActivityVO;
import egovframework.main.service.VO.UserVO;

public interface RequestActivityService {
	
	// insert
	void insertRequestActivityVO(RequestActivityVO requestActivityVO) throws Exception;
	
	// get vo
	RequestActivityVO getRequestActivityVO() throws Exception;
	
	// get list
	List<RequestActivityVO> getRequestActivityListVO(String srvno) throws Exception;

	List<RequestActivityVO> getRequestList(RequestActivityVO requestActivityVO) throws Exception;

	void RequestActivityResult(RequestActivityVO requestActivityVO)throws Exception;
	
	// get limit
	List<RequestActivityVO> getLimitReqActList(String incdt_idtf_cd) throws Exception;
	
	RequestActivityVO getRqstActBySeq(String seq) throws Exception;
	
	// get count
	int getNewCount(UserVO userVO) throws Exception;
	
	// get limit list for main
	List<RequestActivityVO> getRequestedListForMain(String srvno) throws Exception;

	List<RequestActivityVO> searchApproval(RequestActivityVO requestActivityVO) throws Exception;
	
	List<RequestActivityVO> getListByCondition(RequestActivityVO requestActivityVO) throws Exception;
}
