package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.ClsService;
import egovframework.main.service.VO.ClsVO;
import egovframework.main.service.common.ClsDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("clsService")
public class ClsServiceImpl extends EgovAbstractServiceImpl implements ClsService {
	@Resource(name = "clsDAO")
	private ClsDAO clsDAO;
	
	public void insertClsVO(ClsVO clsVO) throws Exception {
		clsDAO.insertClsVO(clsVO);
	}
	
	public List<ClsVO> getAllClsList() throws Exception {
		return clsDAO.getAllClsList();
	}
}
