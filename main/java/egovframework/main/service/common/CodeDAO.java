package egovframework.main.service.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CodeSearchVO;
import egovframework.main.service.VO.CodeVO;
import egovframework.main.util.PaginationDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@SuppressWarnings("unused")
@Repository("codeDAO")
public class CodeDAO extends EgovAbstractDAO {	
	public void insertCodeVO(CodeVO codeVO) throws Exception {
		insert("codeDAO.insertCodeVO", codeVO);
	}
	
	public void insertCodeListInExcel(List<CodeVO> codeList) throws Exception {
		for(int i = 0; i < codeList.size(); ++i) {
			insert("codeDAO.insertCodeVO", codeList.get(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CodeVO> getAllCodeList() throws Exception {		
		return (List<CodeVO>) list("codeDAO.selectAllCodeList");
	}
	
	@SuppressWarnings("unchecked")
	public List<CodeVO> getCodeList(CodeSearchVO codeSearchVO) throws Exception {
		return (List<CodeVO>) list("codeDAO.selectCodeList", codeSearchVO);
	}
	
	public int getCodeListCnt(CodeSearchVO codeSearchVO) throws Exception {
		return (int) select("codeDAO.countSelectedCodeList", codeSearchVO);
	}	
	
	public CodeVO getCodeVO(String cd) throws Exception {
		return (CodeVO) select("codeDAO.getCodeVO", cd);
	}
	
	public void updateCodeVO(CodeVO codeVO) throws Exception {
		update("codeDAO.updateCodeVO", codeVO);
	}
	
	public int getAllCodeListCnt() throws Exception {
		return (int) select("codeDAO.countAllCodeList");
	}
	
	public void deleteCodeVO(CodeVO codeVO) throws Exception {
		delete("codeDAO.deleteCodeVO", codeVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<CodeVO> getCodeListByDvs(String grp_cd) throws Exception {
		return (List<CodeVO>) list("codeDAO.selectCodeListByDvs", grp_cd);
	}
}
