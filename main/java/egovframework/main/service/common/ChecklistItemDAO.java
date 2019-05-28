package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("checklistItemDAO")
public class ChecklistItemDAO extends EgovAbstractDAO {
	// insert Checklist Item
	public int insertChecklistItem(ChecklistItemVO checklistVO) throws Exception
	{
		return 0;
	}
	
	// search Checklist Item
	public ChecklistItemVO getChecklistItemVO(ListsearchVO listsearchVO) throws Exception
	{
		return (ChecklistItemVO) select("checklistItemDAO.selectChecklistItem", listsearchVO);
	}
	
	// search all
	public List<ChecklistItemVO> getAllChecklistItemList() throws Exception
	{
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ChecklistItemVO> ChecklistSelect(ChecklistItemVO checklistItemVO) {
		return (List<ChecklistItemVO>) list("checklistItemDAO.ChecklistSelect", checklistItemVO);
	}

	@SuppressWarnings("unchecked")
	public List<ChecklistItemVO> ChecklistSelecttrue(
			ChecklistItemVO checklistItemVO) {
				return (List<ChecklistItemVO>) list("checklistItemDAO.ChecklistSelecttrue", checklistItemVO);
			}


	@SuppressWarnings("unchecked")
	public List<ChecklistItemVO> ChecklistSelectfalse(
			ChecklistItemVO checklistItemVO)  {
		return (List<ChecklistItemVO>) list("checklistItemDAO.ChecklistSelectfalse", checklistItemVO);
	}

	public void ChecklistUpdate(ChecklistItemVO checklistItemVO) throws Exception{
		update("checklistItemDAO.ChecklistUpdate", checklistItemVO);
		
	}

	public void ChecklistReset(ChecklistItemVO checklistItemVO) throws Exception{
		update("checklistItemDAO.ChecklistReset", checklistItemVO);
		
	}

	public void ChecklistInsert(ChecklistItemVO checklistItemVO) throws Exception{
		insert("checklistItemDAO.ChecklistInsert", checklistItemVO);
		
	}

	public void insertCodeListInExcel(List<ChecklistItemVO> checklist) {
		for(int i = 0; i < checklist.size(); ++i) {
			insert("checklistItemDAO.ChecklistInsert_Excel", checklist.get(i));
		}
		
	}

	public ChecklistItemVO getChecklistCode(ChecklistItemVO checklistItemVO) {
		return (ChecklistItemVO) select("checklistItemDAO.getChecklistCode", checklistItemVO);
		
	}

	@SuppressWarnings("unchecked")
	public List<ChecklistItemVO> ChecklistPreSelect(
			ChecklistItemVO checklistItemVO) throws Exception{
		return (List<ChecklistItemVO>) list("checklistItemDAO.ChecklistPreSelect", checklistItemVO);
	}

}
