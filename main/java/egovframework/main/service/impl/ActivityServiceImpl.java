package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.ActivityService;
import egovframework.main.service.VO.ActivityVO;
import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.UserVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.common.ActivityDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("activityService")
public class ActivityServiceImpl extends EgovAbstractServiceImpl implements ActivityService {
	@Resource(name = "activityDAO")
	private ActivityDAO activityDAO;
	
	public int insertActivity(ActivityVO activityVO) throws Exception
	{
		return 0;
	}
	
	// search Activity
	public ActivityVO getActivityVO(ListsearchVO listsearchVO) throws Exception
	{
		return activityDAO.getActivityVO(listsearchVO);
	}
	
	public ActivityVO getActivityVO(ChecklistItemVO checklistItemVO, String incdt_idtf_cd) throws Exception
	{
		return activityDAO.getActivityVO(checklistItemVO, incdt_idtf_cd);
	}
	
	// search all
	public List<ActivityVO> getActivityListByDate(ListsearchVO listsearchVO) throws Exception
	{
		return activityDAO.getActivityListByDate(listsearchVO);
	}
	
	// time ////////////////
	public int updateStartTime(ActivityVO activityVO) throws Exception
	{
		return activityDAO.updateStartTime(activityVO);
	}
	
	public int updateFinishTime(ActivityVO activityVO) throws Exception
	{
		return activityDAO.updateFinishTime(activityVO);
	}
	
	public int updateState(ActivityVO activityVO) throws Exception {
		return activityDAO.updateState(activityVO);
	}
	
	public List<ActivityVO> getActivityListByLogined(UserVO loginedUser) throws Exception {
		return activityDAO.getActivityListByLogined(loginedUser);
	}
	
	public ActivityVO setActivityChecklistName(ActivityVO activityVO) throws Exception {
		return activityDAO.setActivityChecklistName(activityVO);
	}
	
	public int updateActivityStart(ActivityVO activityVO) throws Exception {
		return activityDAO.updateActivityStart(activityVO);
	}
	
	public int updateActivityFinish(ActivityVO activityVO) throws Exception {
		return activityDAO.updateActivityFinish(activityVO);
	}

	@Override
	public List<ActivityVO> getCommandList(ActivityVO activityVO)
			throws Exception {
		return activityDAO.getCommandList(activityVO);
	}

	@Override
	public List<ActivityVO> getActivitySelect(MemberSearchVO membersearchVO)
			throws Exception {
		return activityDAO.getActivitySelect(membersearchVO);
	}

	@Override
	public void guidncModify1(ActivityVO activityVO) throws Exception {
		 activityDAO.guidncModify1(activityVO);
	}

	@Override
	public void guidncModify2(ActivityVO activityVO) throws Exception {
		 activityDAO.guidncModify2(activityVO);
	}
	@Override
	public void guidncModify3(ActivityVO activityVO) throws Exception {
		 activityDAO.guidncModify3(activityVO);
	}
	
	public void updateActivityCompleteStatus(ActivityVO activityVO) throws Exception {
		activityDAO.updateActivityCompleteStatus(activityVO);
	}
	
	public void finishActivity(ActivityVO activityVO) throws Exception {
		activityDAO.finishActivity(activityVO);
	}
	
	// add 190412 /////////////////////////////
	public ActivityVO getActivityById(String id) throws Exception {
		return activityDAO.getActivityById(id);
	}
	
	public int updateActStartById(String id) throws Exception {
		return activityDAO.updateActStartById(id);
	}
	
	public int updateActFinishById(String id) throws Exception {
		return activityDAO.updateActFinishById(id);
	}
	
	public List<ActivityVO> getActBySearchVO(ListsearchVO listsearchVO) throws Exception {
		return activityDAO.getActBySearchVO(listsearchVO);
	}
	///////////////////////////////////////////
}
