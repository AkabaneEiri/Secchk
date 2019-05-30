package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;






import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.VO.MemberVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("memberDAO")
public class MemberDAO extends EgovAbstractDAO{
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMemberList(MemberSearchVO membersearchVO) throws Exception
	{
		return (List<MemberVO>) list("memberDAO.selectMemberList",membersearchVO);
		
	
	}
	@SuppressWarnings("deprecation")
	public int getMemberListTotCnt(MemberSearchVO membersearchVO) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("memberDAO.selectMemberListTotCnt",membersearchVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMemberSelect(MemberSearchVO membersearchVO) throws Exception
	{
		return (List<MemberVO>) list("memberDAO.selectMemberModify", membersearchVO);
	}
	
	public void insertMember(MemberVO memberVO) throws Exception{
		insert("memberDAO.insertMember",memberVO);
	}
	
	public void DeleteMember(MemberVO memberVO) throws Exception{
		delete("memberDAO.DeleteMember", memberVO);
	}
	@SuppressWarnings("unchecked")
	public List<MemberVO> getUserselect(MemberVO memberVO) {
		return (List<MemberVO>) list("memberDAO.getUserselect", memberVO);
	}
	public void Modifymember(MemberVO memberVO) throws Exception{
		update("memberDAO.Modifymember", memberVO);
	}
}
