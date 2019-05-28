package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.GroupCodeService;
import egovframework.main.service.VO.CodeSearchVO;
import egovframework.main.service.VO.GroupCodeVO;
import egovframework.main.service.common.GroupCodeDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("groupCodeService")
public class GroupCodeServiceImpl extends EgovAbstractServiceImpl implements GroupCodeService{
	@Resource(name = "groupCodeDAO")
	private GroupCodeDAO groupCodeDAO;
	
	// get all list
	public List<GroupCodeVO> getAllGroupCodeList() throws Exception {
		return groupCodeDAO.getAllGroupCodeList();
	}

	// get total count
	public int getTotalCount() throws Exception {
		return groupCodeDAO.getTotalCount();
	}
	
	// get list by name
	public List<GroupCodeVO> getGroupCodeListByName(CodeSearchVO codeSearchVO) throws Exception {
		return groupCodeDAO.getGroupCodeListByName(codeSearchVO);
	}
	
	// get group code vo
	public GroupCodeVO getGroupCodeVO(String cd) throws Exception {
		return groupCodeDAO.getGroupCodeVO(cd);
	}		
	
	// insert group code
	public void insertGroupCode(GroupCodeVO groupCodeVO) throws Exception {
		groupCodeDAO.insertGroupCode(groupCodeVO);
	}

	// modify group code
	public void updateGroupCode(GroupCodeVO groupCodeVO) throws Exception {
		groupCodeDAO.updateGroupCode(groupCodeVO);
	}

	// delete group code
	public void deleteGroupCode(GroupCodeVO groupCodeVO) throws Exception {
		groupCodeDAO.deleteGroupCode(groupCodeVO);
	}
}
