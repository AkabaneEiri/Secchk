package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.CodeSearchVO;
import egovframework.main.service.VO.GroupCodeVO;

public interface GroupCodeService {
	// get all list
	List<GroupCodeVO> getAllGroupCodeList() throws Exception;
	
	// get total count
	int getTotalCount() throws Exception;
	
	// get list by search condition
	List<GroupCodeVO> getGroupCodeListByName(CodeSearchVO codeSearchVO) throws Exception;
	
	// get group code vo
	GroupCodeVO getGroupCodeVO(String cd) throws Exception;
	
	// insert group code
	void insertGroupCode(GroupCodeVO groupCodeVO) throws Exception;

	// modify group code
	void updateGroupCode(GroupCodeVO groupCodeVO) throws Exception;

	// delete group code
	void deleteGroupCode(GroupCodeVO groupCodeVO) throws Exception;
	
	// get list by sort
	List<GroupCodeVO> getAllGroupCodeListBySort() throws Exception;
}
