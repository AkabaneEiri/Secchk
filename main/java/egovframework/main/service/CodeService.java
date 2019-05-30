package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.CodeSearchVO;
import egovframework.main.service.VO.CodeVO;

public interface CodeService {
	// insert VO
	void insertCodeVO(CodeVO codeVO) throws Exception;
	
	// insert code date from excel
	void insertCodeListInExcel(List<CodeVO> codeList) throws Exception;
	
	// get all VO list
	List<CodeVO> getAllCodeList() throws Exception;		
	
	// get VO list by search condition
	List<CodeVO> getCodeList(CodeSearchVO codeSearchVO) throws Exception;
	
	// get list count by search condition
	int getCodeListCnt(CodeSearchVO codeSearchVO) throws Exception;
	
	// get codeVO by cd
	CodeVO getCodeVO(String cd) throws Exception;
	
	// update code data
	void updateCodeVO(CodeVO codeVO) throws Exception;
	
	// get total count
	int getAllCodeListCnt() throws Exception;
	
	// delete code
	void deleteCodeVO(CodeVO codeVO) throws Exception;
	
	// get list by grp_cd
	List<CodeVO> getCodeListByDvs(String grp_cd) throws Exception;
}
