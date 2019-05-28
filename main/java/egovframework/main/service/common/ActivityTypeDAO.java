package egovframework.main.service.common;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.ActivityTypeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 안전관리활동 코드 정보 처리
 * @기능설명 : 안전관리활동 정보와 코드정보를 매칭
 * @작성자 : 박승원
 * @작성일 : 2019. 4. 15.
 */

@Repository("activityTypeDAO")
public class ActivityTypeDAO extends EgovAbstractDAO {
	public ActivityTypeVO getActTypeByCtlg(String ctlg_cd) throws Exception {
		return (ActivityTypeVO) select("activityTypeDAO.selectActTypeByCtlg", ctlg_cd);
	}
}
