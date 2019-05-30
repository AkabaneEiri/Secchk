package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.RequestChecklistVO;
import egovframework.main.service.VO.UserVO;

public interface RequestChecklistService {
	// 요청 insert
	void insertRequestChecklist(RequestChecklistVO requestChecklistVO) throws Exception;
	
	// 요청한 체크리스트 불러옴
	List<RequestChecklistVO> getRequestedList(UserVO userVO) throws Exception;
	
	List<RequestChecklistVO> getChecklistRequest(RequestChecklistVO requestChecklistVO) throws Exception;

	void RequestDeny(RequestChecklistVO requestChecklistVO) throws Exception;

	void RequestCreate(RequestChecklistVO requestChecklistVO) throws Exception;

	List<ChecklistItemVO> getCheckCreate(ChecklistItemVO checklistItemVO) throws Exception;

	void RequestCreateNew(ChecklistItemVO checklistItemVO) throws Exception;

	// add 190325 get list in index.do
	List<RequestChecklistVO> getLimitReqCheckist(String incdt_idtf_cd) throws Exception;

	RequestChecklistVO getCheckRequestSelect(RequestChecklistVO requestChecklistVO) throws Exception;
	
	// get by seq
	RequestChecklistVO getRqstChkBySeq(String seq) throws Exception;

	void RequestAccept(RequestChecklistVO requestChecklistVO) throws Exception;

	List<ChecklistItemVO> getChecklist(ChecklistItemVO checklistItemVO)throws Exception;

}
