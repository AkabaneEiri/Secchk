package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ListsearchVO;

public interface ChecklistItemService {
	// insert Checklist Item
	int insertChecklistItem(ChecklistItemVO checklistItemVO) throws Exception;
	
	// search Checklist Item
	ChecklistItemVO getChecklistItemVO(ListsearchVO listsearchVO) throws Exception;
	
	// search all
	List<ChecklistItemVO> getAllChecklistItemList() throws Exception;

	List<ChecklistItemVO> ChecklistSelect(ChecklistItemVO checklistItemVO) throws Exception;

	List<ChecklistItemVO> ChecklistSelecttrue(ChecklistItemVO checklistItemVO) throws Exception;

	List<ChecklistItemVO> ChecklistSelectfalse(ChecklistItemVO checklistItemVO) throws Exception;

	void ChecklistUpdate(ChecklistItemVO checklistItemVO) throws Exception;

	void Checklistreset(ChecklistItemVO checklistItemVO)throws Exception;

	void ChecklistInsert(ChecklistItemVO checklistItemVO) throws Exception;

	void insertCodeListInExcel(List<ChecklistItemVO> checklist)throws Exception;

	ChecklistItemVO getChecklistCode(ChecklistItemVO checklistItemVO) throws Exception;

	List<ChecklistItemVO> ChecklistPreSelect(ChecklistItemVO checklistItemVO) throws Exception;
	
	ChecklistItemVO getMaxItemCode(String ctlg_cd) throws Exception;
	
	List<ChecklistItemVO> getEmptyItemCodeList(String ctlg_cd) throws Exception;
	
	ChecklistItemVO getCtlgCodeByItemCode(String ctlg_itm_cd) throws Exception;
}
