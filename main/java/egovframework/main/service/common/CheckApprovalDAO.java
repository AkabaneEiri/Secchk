package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CheckApprovalVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.VO.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 조치요청 정보 처리
 * @기능설명 : 조치요청에 대한 정보 입력, 검색 및 삭제
 * @작성자 : 김준성
 * @작성일 : 2019. 2. 13.
 * @변경이력 : 2019. 3. 18. / 김준성
 * @변경내용 : 조치요청 입력기능 수정
 * @변경이력 : 2019. 4. 25. / 박승원
 * @변경내용 : 사용자 정보에 맞는 조치요청 갯수 파악 기능 추가
 */
@Repository("checkApprovalDAO")
public class CheckApprovalDAO extends EgovAbstractDAO{
	@SuppressWarnings("unchecked")
	public List<CheckApprovalVO> getCheckApprovalList(MemberSearchVO membersearchVO) throws Exception{
		return (List<CheckApprovalVO>) list("checkApprovalDAO.getCheckApprovalList", membersearchVO);
	}

	@SuppressWarnings("unchecked")
	public List<CheckApprovalVO> getCheckApprovalSelect(
			CheckApprovalVO checkApprovalVO) throws Exception{
		return (List<CheckApprovalVO>) list("checkApprovalDAO.getCheckApprovalSelect", checkApprovalVO);
	}
	
	@SuppressWarnings("deprecation")
	public CheckApprovalVO findApproveByKey(CheckApprovalVO checkApprovalVO) throws Exception{
		return (CheckApprovalVO) selectByPk("checkApprovalDAO.selectApprovalByKey", checkApprovalVO);
	}
	
	// seungwon
	public void insertApproval(CheckApprovalVO checkApprovalVO) throws Exception {
		// date type change
		System.out.println(checkApprovalVO.getActvt_date());
		String tempDate = checkApprovalVO.getActvt_date();
		
		checkApprovalVO.setActvt_date(tempDate.substring(0, 10));
		
		System.out.println(checkApprovalVO.getActvt_date());
		
		insert("checkApprovalDAO.insertApproval", checkApprovalVO);
	}

	public void ApproveModify(CheckApprovalVO checkApprovalVO) throws Exception{
			update("checkApprovalDAO.ApproveModify", checkApprovalVO);
	}
	
	public CheckApprovalVO getApprovalVO(String ctlg_itm_cd) throws Exception {
		return (CheckApprovalVO) select("checkApprovalDAO.selectApprovalVO", ctlg_itm_cd);
	}
	
	@SuppressWarnings("unchecked")
	public List<CheckApprovalVO> getLimitCheckApprovalList(String incdt_idtf_cd) throws Exception {
		return (List<CheckApprovalVO>) list("checkApprovalDAO.selectLimitCheckApprovalList", incdt_idtf_cd);
	}

	@SuppressWarnings("deprecation")
	public CheckApprovalVO changetoCode(CheckApprovalVO checkApprovalVO) {
		return (CheckApprovalVO) selectByPk("checkApprovalDAO.changetoCode", checkApprovalVO);
	}
	
	// add 190415
	public CheckApprovalVO getApprovalByVO(CheckApprovalVO checkApprovalVO) throws Exception {
		return (CheckApprovalVO) select("checkApprovalDAO.selectApprovalByVO", checkApprovalVO);
	}
	
	// get count
	public int getNewCount(UserVO userVO) throws Exception {
		return (Integer) select("checkApprovalDAO.getNewCount", userVO);
	}

	@SuppressWarnings("unchecked")
	public List<CheckApprovalVO> searchApproval(CheckApprovalVO checkApprovalVO) {
		return (List<CheckApprovalVO>) list("checkApprovalDAO.searchApproval", checkApprovalVO);
	}
	
	// get rqst ugcy in task date page
	public CheckApprovalVO getApprovalByTaskData(ListsearchVO listsearchVO) throws Exception {
		return (CheckApprovalVO) select("checkApprovalDAO.getApprovalByTaskData", listsearchVO);
	}
}
