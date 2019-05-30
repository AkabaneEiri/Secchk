package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.CheckApprovalVO;
import egovframework.main.service.VO.MemberSearchVO;

public interface CheckApprovalService {
	List<CheckApprovalVO> getCheckApprovalList(MemberSearchVO membersearchVO) throws Exception;

	List<CheckApprovalVO> getApprovalSelect(CheckApprovalVO checkApprovalVO) throws Exception;
	
	CheckApprovalVO findApproveByKey(CheckApprovalVO checkApprovalVO) throws Exception;

	// seungwon
	void insertApproval(CheckApprovalVO checkApprovalVO) throws Exception;
	void ApproveModify(CheckApprovalVO checkApprovalVO) throws Exception;
	
	CheckApprovalVO getApprovalVO(String ctlg_itm_cd) throws Exception;
	
	List<CheckApprovalVO> getLimitCheckApprovalList(String incdt_idtf_cd) throws Exception;

	CheckApprovalVO changetoCode(CheckApprovalVO checkApprovalVO) throws Exception;
	
	// add 190415
	CheckApprovalVO getApprovalByVO(CheckApprovalVO checkApprovalVO) throws Exception;
}
