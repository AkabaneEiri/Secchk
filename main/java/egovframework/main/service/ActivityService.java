package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.ActivityVO;
import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.UserVO;
import egovframework.main.service.VO.MemberSearchVO;

public interface ActivityService {
	int insertActivity(ActivityVO activityVO) throws Exception;
	
	// search Activity
	ActivityVO getActivityVO(ListsearchVO listsearchVO) throws Exception;
	ActivityVO getActivityVO(ChecklistItemVO checklistItemVO, String incdt_idtf_cd) throws Exception;
	
	// search all
	List<ActivityVO> getActivityListByDate(ListsearchVO listsearchVO) throws Exception;
	
	// time insert
	int updateStartTime(ActivityVO activityVO) throws Exception;		// start	
	int updateFinishTime(ActivityVO activityVO) throws Exception;		// finish
	int updateState(ActivityVO activityVO) throws Exception;			// update state_cd
	
	List<ActivityVO> getActivityListByLogined(UserVO loginedUser) throws Exception;
	ActivityVO setActivityChecklistName(ActivityVO activityVO) throws Exception;
	
	int updateActivityStart(ActivityVO activityVO) throws Exception;
	int updateActivityFinish(ActivityVO activityVO) throws Exception;

	//search CommanderGuide
	List<ActivityVO> getCommandList(ActivityVO activityVO) throws Exception;

	List<ActivityVO> getActivitySelect(MemberSearchVO membersearchVO) throws Exception;

	void guidncModify1(ActivityVO activityVO) throws Exception;
	void guidncModify2(ActivityVO activityVO) throws Exception;
	void guidncModify3(ActivityVO activityVO) throws Exception;
	
	// update complete status
	void updateActivityCompleteStatus(ActivityVO activityVO) throws Exception;
	
	// finish activity
	void finishActivity(ActivityVO activityVO) throws Exception;
	
	
	// add 190412 /////////////////////////////
	ActivityVO getActivityById(String id) throws Exception;

	int updateActStartById(String id) throws Exception;

	int updateActFinishById(String id) throws Exception;
	// /////////////////////////////////////////
}
