package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.ChecklistService;
import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ChecklistVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.UserVO;
import egovframework.main.service.common.ChecklistDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("checklistService")
public class ChecklistServiceImpl extends EgovAbstractServiceImpl implements ChecklistService {
	
	@Resource(name = "checklistDAO")
	private ChecklistDAO checklistDAO;

	// insert User
	public int insertChecklist(ChecklistVO checklistVO) throws Exception {
		return 0;
	}

	// search User
	public ChecklistVO getChecklistVO(ListsearchVO listsearchVO) throws Exception {
		return checklistDAO.getChecklistVO(listsearchVO);
	}

	// search all
	public List<ChecklistVO> getAllChecklist(UserVO userVO) throws Exception {
		return checklistDAO.getAllChecklist(userVO);
	}

	@Override
	public List<ChecklistItemVO> getChecklist(ChecklistVO checklistVO)
			throws Exception {
		return checklistDAO.getChecklistVO(checklistVO);
	}

	@Override
	public void Checklistreset(ChecklistVO checklistVO) throws Exception {
		checklistDAO.Checklistreset(checklistVO);
		
	}

	@Override
	public void ChecklistSelect_Result(ChecklistVO checklistVO)
			throws Exception {
		checklistDAO.ChecklistSelect_Result(checklistVO);
		
	}
}
