package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.MemberService;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.VO.MemberVO;
import egovframework.main.service.common.MemberDAO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

	@SuppressWarnings("deprecation")
	@Service("memberService")
	public class MemberServiceImpl extends AbstractServiceImpl implements MemberService{
		
		@Resource(name="memberDAO")
		private MemberDAO memberDAO;
		public List<MemberVO> getMemberList(MemberSearchVO membersearchVO) throws Exception
		{
			return memberDAO.getMemberList(membersearchVO);

		}
		
		public int getMemberListTotCnt(MemberSearchVO membersearchVO) throws Exception
		{
			return memberDAO.getMemberListTotCnt(membersearchVO);
		}
		
		public List<MemberVO> getMemberSelect(MemberSearchVO membersearchVO) throws Exception
		{
			return memberDAO.getMemberSelect(membersearchVO);
		}
		
		public void insertMember(MemberVO memberVO) throws Exception
		{
			memberDAO.insertMember(memberVO);
		}
		
		public List<MemberVO> ModifySelect(MemberSearchVO membersearchVO) throws Exception
		{
			return memberDAO.getMemberSelect(membersearchVO);
		}

		public void DeleteMember(MemberVO memberVO) throws Exception 
		{
			memberDAO.DeleteMember(memberVO);
		}

		@Override
		public List<MemberVO> getMemberselect(MemberVO memberVO)
				throws Exception {
			return memberDAO.getUserselect(memberVO);
		}

		@Override
		public void ModifyMember(MemberVO memberVO) throws Exception {
			memberDAO.Modifymember(memberVO);
			
		}
	}

