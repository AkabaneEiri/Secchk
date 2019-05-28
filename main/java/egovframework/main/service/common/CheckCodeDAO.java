package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CheckCodeVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("checkCodeDAO")
public class CheckCodeDAO extends EgovAbstractDAO {
	@SuppressWarnings("unchecked")
	public List<CheckCodeVO> getCheckList(MemberSearchVO membersearchVO) throws Exception
	{
		return (List<CheckCodeVO>) list("checkCodeDAO.selectCheckList", membersearchVO);
	}
}
