package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.CheckCodeService;
import egovframework.main.service.VO.CheckCodeVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.common.CheckCodeDAO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@SuppressWarnings("deprecation")
@Service("CheckCodeService")
public class CheckCodeServiceImpl extends AbstractServiceImpl implements CheckCodeService{
	@Resource(name="checkCodeDAO")
	private CheckCodeDAO checkCodeDAO;
	
	public List<CheckCodeVO> getCheckList(MemberSearchVO membersearchVO) throws Exception
	{
		return checkCodeDAO.getCheckList(membersearchVO);
	}
}
