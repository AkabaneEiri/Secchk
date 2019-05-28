package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ChecklistVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.UserVO;

public interface ChecklistService {
	// insert User
	int insertChecklist(ChecklistVO checklistVO) throws Exception;

	// search User
	ChecklistVO getChecklistVO(ListsearchVO listsearchVO) throws Exception;

	// search all
	List<ChecklistVO> getAllChecklist(UserVO userVO) throws Exception;

	List<ChecklistItemVO> getChecklist(ChecklistVO checklistVO) throws Exception;

	void Checklistreset(ChecklistVO checklistVO) throws Exception;

	void ChecklistSelect_Result(ChecklistVO checklistVO) throws Exception;
}
