package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.SptlyCodeVO;



public interface SptlyCodeService {

	List<SptlyCodeVO> getCtlgDataList(SptlyCodeVO sptlyCodeVO) throws Exception;
	List<SptlyCodeVO> getActivityCtlgList(SptlyCodeVO sptlyCodeVO) throws Exception;
	void ChecklistPreset_Update(SptlyCodeVO sptlyCodeVO)throws Exception;
	void Checklistreset(SptlyCodeVO sptlyCodeVO) throws Exception;
	List<SptlyCodeVO> getTaskDataList(SptlyCodeVO sptlyCodeVO) throws Exception;
	List<SptlyCodeVO> ctlgReset(SptlyCodeVO sptlyCodeVO) throws Exception;
	
}
