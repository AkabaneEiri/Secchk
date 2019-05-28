package egovframework.main.service;

import egovframework.main.service.VO.ActivityTypeVO;

public interface ActivityTypeService {
	ActivityTypeVO getActTypeByCtlg(String ctlg_cd) throws Exception;
}
