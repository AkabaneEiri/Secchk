package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 체크리스트 항목 정보 처리
 * @기능설명 : 체크리스트 항목에 대한 정보 입력, 검색 및 삭제
 * @작성자 : 박승원
 * @작성일 : 2019. 2. 21.
 * @변경이력 : 2019. 3. 10. / 김준성
 * @변경내용 : 체크리스트 항목 성별 정보 입력기능 수정
 */
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
		return (List<ChecklistItemVO>) list("checklistItemDAO.getAllCtlg");
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

	public ChecklistItemVO getMaxItemCode(String ctlg_cd) throws Exception {
		return (ChecklistItemVO) select("checklistItemDAO.getMaxItemCode", ctlg_cd);
	}	

	public List<ChecklistItemVO> getEmptyItemCodeList(String ctlg_cd) throws Exception{
		return (List<ChecklistItemVO>) list("checklistItemDAO.getEmptyItemCodeList", ctlg_cd);
	}
	
	public ChecklistItemVO getCtlgCodeByItemCode(String ctlg_itm_cd) throws Exception {
		return (ChecklistItemVO) select("checklistItemDAO.getCtlgCodeByItemCode", ctlg_itm_cd);
	}
}
