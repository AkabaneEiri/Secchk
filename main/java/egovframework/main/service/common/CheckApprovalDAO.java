package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CheckApprovalVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

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
}
