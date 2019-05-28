package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.TaskDataVO;

public interface TaskDataService {
	// junsung ////////////////////////
	void ChecklistPreset_Update(TaskDataVO taskDataVO) throws Exception;
	
	List<TaskDataVO> ChecklistPreset_Search(TaskDataVO taskDataVO)throws Exception;
	List<TaskDataVO> getTaskDataList(TaskDataVO taskDataVO)throws Exception;
	///////////////////////////////////

	void InsertTask(TaskDataVO taskDataVO) throws Exception;

	

	// seungwon ///////////////////////
	
	TaskDataVO getTaskDataVO(TaskDataVO taskDataVO) throws Exception;
	
	void updateYNState(TaskDataVO taskDataVO) throws Exception;
	
	TaskDataVO getTaskDataBySeq(String seq) throws Exception;
	
	List<TaskDataVO> getTaskDataListById(String id) throws Exception;
	
	void updateCommanderGuideById(TaskDataVO taskDataVO) throws Exception;
	
	void updateCommanderGuide1_ById(TaskDataVO taskDataVO) throws Exception;	
	void updateCommanderGuide2_ById(TaskDataVO taskDataVO) throws Exception;
	void updateCommanderGuide3_ById(TaskDataVO taskDataVO) throws Exception;
	
	///////////////////////////////////
}
