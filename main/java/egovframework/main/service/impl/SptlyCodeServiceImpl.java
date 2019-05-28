package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.SptlyCodeService;
import egovframework.main.service.VO.SptlyCodeVO;
import egovframework.main.service.common.SptlyCodeDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;



@Service("sptlyCodeService")
public class SptlyCodeServiceImpl extends EgovAbstractServiceImpl implements SptlyCodeService{
	@Resource(name ="sptlyCodeDAO")
		private SptlyCodeDAO sptlyCodeDAO;

	@Override
	public List<SptlyCodeVO> getCtlgDataList(SptlyCodeVO sptlyCodeVO)
			throws Exception {
		return sptlyCodeDAO.getCtlgDataList(sptlyCodeVO);
	}

	@Override
	public List<SptlyCodeVO> getActivityCtlgList(SptlyCodeVO sptlyCodeVO)
			throws Exception {
		return sptlyCodeDAO.getActivityCtlgList(sptlyCodeVO);
	}

	@Override
	public void ChecklistPreset_Update(SptlyCodeVO sptlyCodeVO) throws Exception {
		sptlyCodeDAO.ChecklistPreset_Update(sptlyCodeVO);
		
	}

	@Override
	public void Checklistreset(SptlyCodeVO sptlyCodeVO) throws Exception {
		sptlyCodeDAO.ChecklistReset(sptlyCodeVO);
		
	}

	@Override
	public List<SptlyCodeVO> getTaskDataList(SptlyCodeVO sptlyCodeVO)
			throws Exception {
		return sptlyCodeDAO.getTaskDataList(sptlyCodeVO);
	}

	@Override
	public List<SptlyCodeVO> ctlgReset(SptlyCodeVO sptlyCodeVO) throws Exception {
		return sptlyCodeDAO.ctlgReset(sptlyCodeVO);
	}

}
