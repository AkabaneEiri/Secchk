package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ChecklistVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 체크리스트 정보 처리
 * @기능설명 : 체크리스트에 대한 정보 입력, 검색 및 삭제
 * @작성자 : 박승원
 * @작성일 : 2019. 2. 18.
 * @변경이력 : 2019. 3. 11. / 박승원
 * @변경내용 : 부대활동 코드에 따른 체크리스트 항목 검색 함수 추가
 * @변경이력 : 2019. 3. 22. / 김준성
 * @변경내용 : 체크리스트 항목 선별에 따른 정보 업데이트 함수 추가
 */
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
