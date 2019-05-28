package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CtlgCodeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("ctlgcodeDAO")
public class CtlgCodeDAO extends EgovAbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<CtlgCodeVO> getCtlgCode(CtlgCodeVO ctlgCodeVO) 
			throws Exception {
		return (List<CtlgCodeVO>) list("ctlgCodeDAO.getCtlgCodeVO", ctlgCodeVO);
		}
}
