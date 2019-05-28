package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.VO.MemberVO;

public interface MemberService {
	List<MemberVO> getMemberList(MemberSearchVO membersearchVO) throws Exception;
	int getMemberListTotCnt(MemberSearchVO membersearchVo) throws Exception;
	void insertMember(MemberVO memberVO) throws Exception;
	List<MemberVO> ModifySelect(MemberSearchVO membersearchVO) throws Exception;
	void DeleteMember(MemberVO memberVO) throws Exception;
	List<MemberVO> getMemberselect(MemberVO memberVO)throws Exception;
	void ModifyMember(MemberVO memberVO) throws Exception;
	List<MemberVO> getManagerList(MemberSearchVO membersearchVO) throws Exception;
	
}