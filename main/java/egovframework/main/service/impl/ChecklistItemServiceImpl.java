package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.ChecklistItemService;
import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.common.ChecklistItemDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("checklistItemService")
public class ChecklistItemServiceImpl extends EgovAbstractServiceImpl implements ChecklistItemService{
	@Resource(name = "checklistItemDAO")
	private ChecklistItemDAO checklistItemDAO;
	
	// insert Checklist Item
	public int insertChecklistItem(ChecklistItemVO checklistItemVO) throws Exception
	{
		return 0;
	}

	// search Checklist Item
	public ChecklistItemVO getChecklistItemVO(ListsearchVO listsearchVO) throws Exception
	{
		return checklistItemDAO.getChecklistItemVO(listsearchVO);
	}

	// search all
	public List<ChecklistItemVO> getAllChecklistItemList() throws Exception
	{
		return null;
	}

	@Override
	public List<ChecklistItemVO> ChecklistSelect(ChecklistItemVO checklistItemVO)
			throws Exception {
		return checklistItemDAO.ChecklistSelect(checklistItemVO);
	}

	@Override
	public List<ChecklistItemVO> ChecklistSelecttrue(
			ChecklistItemVO checklistItemVO) throws Exception {
		return checklistItemDAO.ChecklistSelecttrue(checklistItemVO);
	}

	@Override
	public List<ChecklistItemVO> ChecklistSelectfalse(
			ChecklistItemVO checklistItemVO) throws Exception {
		return checklistItemDAO.ChecklistSelectfalse(checklistItemVO);
	}

	@Override
	public void ChecklistUpdate(ChecklistItemVO checklistItemVO)
			throws Exception {
		checklistItemDAO.ChecklistUpdate(checklistItemVO);
		
	}

	@Override
	public void Checklistreset(ChecklistItemVO checklistItemVO)
			throws Exception {
		checklistItemDAO.ChecklistReset(checklistItemVO);
		
	}

	@Override
	public void ChecklistInsert(ChecklistItemVO checklistItemVO)
			throws Exception {
		checklistItemDAO.ChecklistInsert(checklistItemVO);
		
	}

	@Override
	public void insertCodeListInExcel(List<ChecklistItemVO> checklist)
			throws Exception {
		checklistItemDAO.insertCodeListInExcel(checklist);
		
	}

	@Override
	public ChecklistItemVO getChecklistCode(ChecklistItemVO checklistItemVO)
			throws Exception {
		return checklistItemDAO.getChecklistCode(checklistItemVO);
	}

	@Override
	public List<ChecklistItemVO> ChecklistPreSelect(
			ChecklistItemVO checklistItemVO) throws Exception {
		return checklistItemDAO.ChecklistPreSelect(checklistItemVO);
	}
}
