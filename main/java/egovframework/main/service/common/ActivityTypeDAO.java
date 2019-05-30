package egovframework.main.service.common;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.ActivityTypeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("activityTypeDAO")
public class ActivityTypeDAO extends EgovAbstractDAO {
	public ActivityTypeVO getActTypeByCtlg(String ctlg_cd) throws Exception {
		return (ActivityTypeVO) select("activityTypeDAO.selectActTypeByCtlg", ctlg_cd);
	}
}
