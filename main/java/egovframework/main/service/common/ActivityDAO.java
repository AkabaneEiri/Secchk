package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.ActivityVO;
import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.UserVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 안전관리활동 정보 처리
 * @기능설명 : 안전관리활동에 대한 정보 입력, 검색 및 삭제
 * @작성자 : 박승원
 * @작성일 : 2019. 2. 25.
 * @변경이력 : 2019. 3. 10. / 박승원
 * @변경내용 : 사용자정보에 맞는 리스트 정보 검색기능 추가
 * @변경이력 : 2019. 4. 12. / 박승원
 * @변경내용 : id를 이용한 검색기능 추가
 */
@Repository("activityDAO")
public class ActivityDAO extends EgovAbstractDAO {
	public int insertActivity(ActivityVO activityVO) throws Exception {
		return 0;
	}

	// search Activity
	public ActivityVO getActivityVO(ListsearchVO listsearchVO) throws Exception {
		return (ActivityVO) select("activityDAO.selectActivity", listsearchVO);
	}

	// checklistItem 을 가지고 있는 부대활동 검색
	public ActivityVO getActivityVO(ChecklistItemVO checklistItemVO, String incdt_idtf_cd) throws Exception {
		@SuppressWarnings("unchecked")
		List<ActivityVO> tempList = (List<ActivityVO>) list("activityDAO.selectActivityListByIncdt", incdt_idtf_cd);
		System.out.println(tempList.get(0).getIncdt_actvt_type_cd());

		ActivityVO tempVO = null;
		System.out.println(select("activityDAO.selectCodeByItemCode", checklistItemVO));
		String tempStr = (String) select("activityDAO.selectCodeByItemCode", checklistItemVO);
		System.out.println("code : "+tempStr);

		for (int i = 0; i < tempList.size(); ++i) {
			System.out.println(tempList.get(i).getIncdt_actvt_type_cd());
			if (tempList.get(i).getIncdt_actvt_type_cd().equals(tempStr))
				tempVO = tempList.get(i);
		}
		System.out.println(tempVO.getState_cd());

		return tempVO;
	}

	// search all
	@SuppressWarnings("unchecked")
	public List<ActivityVO> getActivityListByDate(ListsearchVO listsearchVO)
			throws Exception {
		return (List<ActivityVO>) list("activityDAO.selectActivityListByDate", listsearchVO);
	}

	// time ////////////////////////
	public int updateStartTime(ActivityVO activityVO) throws Exception {
		return (int) update("activityDAO.updateStartTime", activityVO);
	}

	public int updateFinishTime(ActivityVO activityVO) throws Exception {
		return (int) update("activityDAO.updateFinishTime", activityVO);
	}

	public int updateState(ActivityVO activityVO) throws Exception {
		return (int) update("activityDAO.updateItemState", activityVO);
	}
	
	// 부대코드로 활동 리스트 검색
	@SuppressWarnings("unchecked")
	public List<ActivityVO> getActivityListByLogined(UserVO loginedUser) throws Exception {
		System.out.println("start..");
		List<ActivityVO> tempList = (List<ActivityVO>) list("activityDAO.selectActivityListByLogined", loginedUser);
		
//		if(tempList != null && tempList.size() != 0) {
//			System.out.println("in if..");
//			
//			ActivityVO tempAct = null;
//			for(int i = 0; i < tempList.size(); ++i) {
//				System.out.println("in for..");
//				
//				tempAct = setActivityChecklistName(tempList.get(i));
//				
//				System.out.println(tempAct.getIncdt_actvt_type_cd_nm());
//				
//				tempList.set(i, tempAct);
//				
//				System.out.println("out for..");
//			}
//			System.out.println("out if..");
//		}
		System.out.println("return..");
		return tempList;
	}
	
	public ActivityVO setActivityChecklistName(ActivityVO activityVO) throws Exception {
		return (ActivityVO) select("activityDAO.selectListNameInActivity", activityVO);
	}
	
	public int updateActivityStart(ActivityVO activityVO) throws Exception {
		return (int) update("activityDAO.updateActivityStart", activityVO);
	}
	
	public int updateActivityFinish(ActivityVO activityVO) throws Exception {
		return (int) update("activityDAO.updateActivityFinish", activityVO);
	}

	//getCommandList
	@SuppressWarnings("unchecked")
	public List<ActivityVO> getCommandList(ActivityVO activityVO) 
		throws Exception {
		return (List<ActivityVO>) list("activityDAO.selectCommandList", activityVO);
	}

	@SuppressWarnings("unchecked")
	public List<ActivityVO> getActivitySelect(MemberSearchVO membersearchVO) 
		throws Exception {
		return (List<ActivityVO>) list("activityDAO.getActivitySelect", membersearchVO);
	}

	public Object guidncModify1(ActivityVO activityVO) 
		throws Exception{
		return  update("activityDAO.guidncModify1", activityVO);
	}

	public Object guidncModify2(ActivityVO activityVO) 
			throws Exception{
			return  update("activityDAO.guidncModify2", activityVO);
		}
	public Object guidncModify3(ActivityVO activityVO) 
			throws Exception{
			return  update("activityDAO.guidncModify3", activityVO);
		}
	
	
	public void updateActivityCompleteStatus(ActivityVO activityVO) throws Exception {
		update("activityDAO.updateActivityCompleteStatus", activityVO);
	}
	
	public void finishActivity(ActivityVO activityVO) throws Exception {
		update("activityDAO.updateFinishActivity", activityVO);
	}
	
	// add 190412 /////////////////////////////
	public ActivityVO getActivityById(String id) throws Exception {
		return (ActivityVO) select("activityDAO.selectActivityById", id);
	}
	
	public int updateActStartById(String id) throws Exception {
		return (int) update("activityDAO.updateActStartById", id);
	}
	
	public int updateActFinishById(String id) throws Exception {
		return (int) update("activityDAO.updateActFinishById", id);
	}
	
	public List<ActivityVO> getActBySearchVO(ListsearchVO listsearchVO) throws Exception {
		return (List<ActivityVO>) list("activityDAO.getActBySearchVO", listsearchVO);
	}
	///////////////////////////////////////////
}
