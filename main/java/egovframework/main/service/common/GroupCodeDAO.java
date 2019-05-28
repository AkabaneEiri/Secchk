package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CodeSearchVO;
import egovframework.main.service.VO.CodeVO;
import egovframework.main.service.VO.GroupCodeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 분류코드 관리
 * @기능설명 : 분류코드 입력, 검색 및 삭제
 * @작성자 : 박승원
 * @작성일 : 2019. 4. 02.
 */
@Repository("groupCodeDAO")
public class GroupCodeDAO extends EgovAbstractDAO{
	// get all list
	@SuppressWarnings("unchecked")
	public List<GroupCodeVO> getAllGroupCodeList() throws Exception {
		return (List<GroupCodeVO>) list("groupCodeDAO.getAllGroupCodeList");
	}

	// get total count
	public int getTotalCount() throws Exception {
		return (int) select("groupCodeDAO.getTotalCount");
	}
	
	// get list by search condition
	@SuppressWarnings("unchecked")
	public List<GroupCodeVO> getGroupCodeListByName(CodeSearchVO codeSearchVO) throws Exception {
		return (List<GroupCodeVO>) list("groupCodeDAO.selectGroupCodeListByName", codeSearchVO);
	}
	
	// get group code vo
	public GroupCodeVO getGroupCodeVO(String cd) throws Exception {
		return (GroupCodeVO) select("groupCodeDAO.selectGroupCodeVO", cd);
	}
	
	// insert group code
	public void insertGroupCode(GroupCodeVO groupCodeVO) throws Exception {
		insert("groupCodeDAO.insertGroupCode", groupCodeVO);
	}
	
	// modify group code
	public void updateGroupCode(GroupCodeVO groupCodeVO) throws Exception {
		update("groupCodeDAO.updateGroupCode", groupCodeVO);
	}
	
	// delete group code
	public void deleteGroupCode(GroupCodeVO groupCodeVO) throws Exception {
		delete("groupCodeDAO.deleteGroupCode", groupCodeVO);
	}
	
	// get list by sort
	public List<GroupCodeVO> getAllGroupCodeListBySort() throws Exception {		
		return (List<GroupCodeVO>) list("groupCodeDAO.getAllGroupCodeListBySort");
	}
}
