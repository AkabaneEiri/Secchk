package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CtlgCodeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 체크리스트 코드 정보 처리
 * @기능설명 : 체크리스트 코드 매칭
 * @작성자 : 김준성
 * @작성일 : 2019. 2. 11.
 */
@Repository("ctlgcodeDAO")
public class CtlgCodeDAO extends EgovAbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<CtlgCodeVO> getCtlgCode(CtlgCodeVO ctlgCodeVO) 
			throws Exception {
		return (List<CtlgCodeVO>) list("ctlgCodeDAO.getCtlgCodeVO", ctlgCodeVO);
		}
	
	public List<CtlgCodeVO> getAllCtlgCodeList() throws Exception {
		return (List<CtlgCodeVO>) list("ctlgCodeDAO.getAllCtlgCodeList");
	}
	
	public List<CtlgCodeVO> getCtlgCodeListByLarge(String large) throws Exception {
		return (List<CtlgCodeVO>) list("ctlgCodeDAO.getCtlgCodeListByLarge", large);
	}
	
	public List<CtlgCodeVO> getCtlgCodeListByMiddle(String middle) throws Exception {
		return (List<CtlgCodeVO>) list("ctlgCodeDAO.getCtlgCodeListByMiddle", middle);
	}
	
	public List<CtlgCodeVO> getCtlgCodeByAllCondition(CtlgCodeVO ctlgCodeVO) throws Exception {
		return (List<CtlgCodeVO>) list("ctlgCodeDAO.getCtlgCodeByAllCondition", ctlgCodeVO);
	}
	
	public void insertCtlgCodeVO(CtlgCodeVO ctlgCodeVO) throws Exception {
		insert("ctlgCodeDAO.insertCtlgCodeVO", ctlgCodeVO);
	}
}
