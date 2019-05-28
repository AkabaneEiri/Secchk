package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ChecklistVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("checklistDAO")
public class ChecklistDAO extends EgovAbstractDAO {
	public int insertChecklist(ChecklistVO checklistVO) throws Exception
	{
		return 0;
	}
	
	// search User
	public ChecklistVO getChecklistVO(ListsearchVO listsearchVO) throws Exception
	{
		return (ChecklistVO) select("checklistDAO.selectChecklist", listsearchVO);
	}
	
	// search all
	@SuppressWarnings("unchecked")
	public List<ChecklistVO> getAllChecklist(UserVO userVO) throws Exception
	{
		return (List<ChecklistVO>) list("checklistDAO.selectAllChecklist", userVO);
	}

	@SuppressWarnings("unchecked")
	public List<ChecklistItemVO> getChecklistVO(ChecklistVO checklistVO) throws Exception{
		return (List<ChecklistItemVO>) list("checklistDAO.getChecklist", checklistVO);
	}

	public void Checklistreset(ChecklistVO checklistVO) throws Exception{
		update("checklistDAO.Checklistreset", checklistVO);
		
	}

	public void ChecklistSelect_Result(ChecklistVO checklistVO) throws Exception{
		update("checklistDAO.ChecklistSelect_Result", checklistVO);
		
	}
}
