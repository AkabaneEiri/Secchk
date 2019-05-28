package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.CtlgCodeService;
import egovframework.main.service.VO.CtlgCodeVO;
import egovframework.main.service.common.CtlgCodeDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("CtlgCodeService")
public class CtglCodeServiceImpl extends EgovAbstractServiceImpl implements CtlgCodeService {
	@Resource(name = "ctlgcodeDAO")
	
	private CtlgCodeDAO ctlgcodeDAO;

	@Override
	public List<CtlgCodeVO> getActivityCode(CtlgCodeVO ctlgCodeVO)
			throws Exception {
		return  ctlgcodeDAO.getCtlgCode(ctlgCodeVO);
	}
	
}