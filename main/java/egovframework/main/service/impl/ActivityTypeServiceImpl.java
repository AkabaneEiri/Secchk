package egovframework.main.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.ActivityTypeService;
import egovframework.main.service.VO.ActivityTypeVO;
import egovframework.main.service.common.ActivityTypeDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("activityTypeService")
public class ActivityTypeServiceImpl extends EgovAbstractServiceImpl implements ActivityTypeService {
	@Resource(name = "activityTypeDAO")
	private ActivityTypeDAO activityTypeDAO;
	
	public ActivityTypeVO getActTypeByCtlg(String ctlg_cd) throws Exception {
		return activityTypeDAO.getActTypeByCtlg(ctlg_cd);
	}
}
