package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.TaskDataService;
import egovframework.main.service.VO.TaskDataVO;
import egovframework.main.service.common.TaskDataDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("taskDataService")
public class TaskDataServiceImpl extends EgovAbstractServiceImpl implements TaskDataService{
	@Resource(name = "taskDataDAO")
	private TaskDataDAO taskDataDAO;
	// junsung ////////////////////////
	@Override
	public void ChecklistPreset_Update(TaskDataVO taskDataVO) throws Exception {
		taskDataDAO.ChecklistPreset_Update(taskDataVO);
	}
	@Override
	public List<TaskDataVO> ChecklistPreset_Search(TaskDataVO taskDataVO)
			throws Exception {
		return taskDataDAO.ChecklistPreset_search(taskDataVO);
	}
	@Override
	public List<TaskDataVO> getTaskDataList(TaskDataVO taskDataVO)
			throws Exception {
		return taskDataDAO.getTaskDataList(taskDataVO);
	}
	@Override
	public void InsertTask(TaskDataVO taskDataVO) throws Exception {
		taskDataDAO.InsertTask(taskDataVO);
	}

	
	// /////////////////////////////////

	// seungwon ///////////////////////
	
	public TaskDataVO getTaskDataVO(TaskDataVO taskDataVO) throws Exception {
		return taskDataDAO.getTaskDataVO(taskDataVO);
	}
	
	public void updateYNState(TaskDataVO taskDataVO) throws Exception {
		taskDataDAO.updateYNState(taskDataVO);
	}
	
	public TaskDataVO getTaskDataBySeq(String seq) throws Exception {
		return taskDataDAO.getTaskDataBySeq(seq);
	}
	
	public List<TaskDataVO> getTaskDataListById(String id) throws Exception {
		return taskDataDAO.getTaskDataListById(id);
	}
	
	public void updateCommanderGuideById(TaskDataVO taskDataVO) throws Exception {
		taskDataDAO.updateCommanderGuideById(taskDataVO);
	}
	
	public void updateCommanderGuide1_ById(TaskDataVO taskDataVO) throws Exception {
		taskDataDAO.updateCommanderGuide1_ById(taskDataVO);
	}
	
	public void updateCommanderGuide2_ById(TaskDataVO taskDataVO) throws Exception {
		taskDataDAO.updateCommanderGuide2_ById(taskDataVO);
	}
	
	public void updateCommanderGuide3_ById(TaskDataVO taskDataVO) throws Exception {
		taskDataDAO.updateCommanderGuide3_ById(taskDataVO);
	}

	// /////////////////////////////////
}
