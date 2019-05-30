package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.CodeService;
import egovframework.main.service.VO.CodeSearchVO;
import egovframework.main.service.VO.CodeVO;
import egovframework.main.service.common.CodeDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("codeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService{
	@Resource(name = "codeDAO")
	private CodeDAO codeDAO;
	
	public void insertCodeVO(CodeVO codeVO) throws Exception {
		codeDAO.insertCodeVO(codeVO);
	}
	
	public void insertCodeListInExcel(List<CodeVO> codeList) throws Exception {
		codeDAO.insertCodeListInExcel(codeList);
	}
	
	public List<CodeVO> getAllCodeList() throws Exception {
		return codeDAO.getAllCodeList();
	}
	
	public List<CodeVO> getCodeList(CodeSearchVO codeSearchVO) throws Exception {
		return codeDAO.getCodeList(codeSearchVO);
	}
	
	public int getCodeListCnt(CodeSearchVO codeSearchVO) throws Exception {
		return codeDAO.getCodeListCnt(codeSearchVO);
	}
	
	public CodeVO getCodeVO(String cd) throws Exception {
		return codeDAO.getCodeVO(cd);
	}
	
	public void updateCodeVO(CodeVO codeVO) throws Exception {
		codeDAO.updateCodeVO(codeVO);
	}
	
	public int getAllCodeListCnt() throws Exception {
		return codeDAO.getAllCodeListCnt();
	}
	
	public void deleteCodeVO(CodeVO codeVO) throws Exception {
		codeDAO.deleteCodeVO(codeVO);
	}
	
	public List<CodeVO> getCodeListByDvs(String grp_cd) throws Exception {
		return codeDAO.getCodeListByDvs(grp_cd);
	}
}
