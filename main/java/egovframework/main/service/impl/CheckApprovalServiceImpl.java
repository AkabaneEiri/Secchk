package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.CheckApprovalService;
import egovframework.main.service.VO.CheckApprovalVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.common.CheckApprovalDAO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@SuppressWarnings("deprecation")
@Service("checkApprovalService")
public class CheckApprovalServiceImpl extends AbstractServiceImpl implements CheckApprovalService{
	@Resource(name="checkApprovalDAO")
	private CheckApprovalDAO checkApprovalDAO;
	
	public List<CheckApprovalVO> getCheckApprovalList(MemberSearchVO membersearchVO) throws Exception{
		return checkApprovalDAO.getCheckApprovalList(membersearchVO);
	}
	public List<CheckApprovalVO> getApprovalSelect(CheckApprovalVO checkApprovalVO) throws Exception{
		return checkApprovalDAO.getCheckApprovalSelect(checkApprovalVO);
	}
	public CheckApprovalVO findApproveByKey(CheckApprovalVO checkApprovalVO) throws Exception{
		return checkApprovalDAO.findApproveByKey(checkApprovalVO);
	}
	
	// seungwon
	public void insertApproval(CheckApprovalVO checkApprovalVO) throws Exception {
		checkApprovalDAO.insertApproval(checkApprovalVO);
	}
	
	@Override
	public void ApproveModify(CheckApprovalVO checkApprovalVO) throws Exception {
		 checkApprovalDAO.ApproveModify(checkApprovalVO);
		
	}
	
	public CheckApprovalVO getApprovalVO(String ctlg_itm_cd) throws Exception {
		return checkApprovalDAO.getApprovalVO(ctlg_itm_cd);
	}
	
	public List<CheckApprovalVO> getLimitCheckApprovalList(String incdt_idtf_cd) throws Exception {
		return checkApprovalDAO.getLimitCheckApprovalList(incdt_idtf_cd);
	}
	@Override
	public CheckApprovalVO changetoCode(CheckApprovalVO checkApprovalVO)
			throws Exception {
		return checkApprovalDAO.changetoCode(checkApprovalVO);
	}
	
	public CheckApprovalVO getApprovalByVO(CheckApprovalVO checkApprovalVO) throws Exception {
		return checkApprovalDAO.getApprovalByVO(checkApprovalVO);
	}
}
