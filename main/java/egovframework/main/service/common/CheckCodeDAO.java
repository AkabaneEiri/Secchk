package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CheckCodeVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 체크리스트 코드 정보 처리
 * @기능설명 : 체크리스트 코드 정보와 맞는 코드리스트 검색
 * @작성자 : 김준성
 * @작성일 : 2019. 2. 14.
 */
@Repository("checkCodeDAO")
public class CheckCodeDAO extends EgovAbstractDAO {
	@SuppressWarnings("unchecked")
	public List<CheckCodeVO> getCheckList(MemberSearchVO membersearchVO) throws Exception
	{
		return (List<CheckCodeVO>) list("checkCodeDAO.selectCheckList", membersearchVO);
	}
}
