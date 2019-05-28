package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.SptlyCodeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("sptlyCodeDAO")
public class SptlyCodeDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public List<SptlyCodeVO> getCtlgDataList(SptlyCodeVO sptlyCodeVO) {
		// TODO 자동 생성된 메소드 스텁
		return (List<SptlyCodeVO>) list("sptlyCodeDAO.getCtlgDataList", sptlyCodeVO);
	}

	@SuppressWarnings("unchecked")
	public List<SptlyCodeVO> getActivityCtlgList(SptlyCodeVO sptlyCodeVO) {
		return (List<SptlyCodeVO>) list("sptlyCodeDAO.getActivityCtlgList", sptlyCodeVO);
	}

	public void ChecklistPreset_Update(SptlyCodeVO sptlyCodeVO) {
		update("sptlyCodeDAO.ChecklistPreset_Update", sptlyCodeVO);
	}

	public void ChecklistReset(SptlyCodeVO sptlyCodeVO) {
		update("sptlyCodeDAO.ChecklistReset", sptlyCodeVO);
		
	}

	@SuppressWarnings("unchecked")
	public List<SptlyCodeVO> getTaskDataList(SptlyCodeVO sptlyCodeVO) {
		return (List<SptlyCodeVO>) list("sptlyCodeDAO.getTaskDataList", sptlyCodeVO);
	}

	@SuppressWarnings("unchecked")
	public List<SptlyCodeVO> ctlgReset(SptlyCodeVO sptlyCodeVO) throws Exception{
		return (List<SptlyCodeVO>) list("sptlyCodeDAO.ctlgReset", sptlyCodeVO);
	}
}
