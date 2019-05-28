package egovframework.main.service.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CodeSearchVO;
import egovframework.main.service.VO.CodeVO;
import egovframework.main.util.PaginationDAO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 코드정보 처리
 * @기능설명 : 코드 정보에 대한 입력, 검색 및 삭제
 * @작성자 : 박승원
 * @작성일 : 2019. 2. 27.
 * @변경이력 : 2019. 3. 13. / 박승원
 * @변경내용 : 코드 정보에 대한 분류코드 기능 추가
 */
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
	
	// search duplicate code
	public CodeVO searchDuplicateCode(CodeVO codeVO) throws Exception {
		return (CodeVO) select("codeDAO.searchDuplicateCode", codeVO);
	}
}
