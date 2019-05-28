package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.TaskDataVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("taskDataDAO")
public class TaskDataDAO extends EgovAbstractDAO {

	// junsung ////////////////////////

	@SuppressWarnings("unchecked")
	public List<TaskDataVO> ChecklistPreset_search(TaskDataVO taskDataVO) throws Exception
	{
		return (List<TaskDataVO>) list("taskDataDAO.ChecklistPreset_Search", taskDataVO);
	}
	@SuppressWarnings("unchecked")
	public List<TaskDataVO> getTaskDataList(TaskDataVO taskDataVO) throws Exception{
		return (List<TaskDataVO>) list("taskDataDAO.getTaskDataList", taskDataVO);
	}
	public void ChecklistPreset_Update(TaskDataVO taskDataVO) {
		update("taskDataDAO.ChecklistPreset_Update", taskDataVO);
	}
	public void InsertTask(TaskDataVO taskDataVO) {
		update("taskDataDAO.InsertTask", taskDataVO);
		
	}

	// /////////////////////////////////

	// seungwon ///////////////////////
	
	public TaskDataVO getTaskDataVO(TaskDataVO taskDataVO) throws Exception {
		System.out.println("in dao");
		return (TaskDataVO) select("taskDataDAO.selectTaskDataVO", taskDataVO);
	}
	
	public void updateYNState(TaskDataVO taskDataVO) throws Exception {
		update("taskDataDAO.updateYNState", taskDataVO);
	}
	
	public TaskDataVO getTaskDataBySeq(String seq) throws Exception {
		return (TaskDataVO) select("taskDataDAO.selectTaskDataBySeq", seq);
	}	
	
	public List<TaskDataVO> getTaskDataListById(String id) throws Exception {
		return (List<TaskDataVO>) list("taskDataDAO.selectTaskDataListById", id);
	}
	
	public void updateCommanderGuideById(TaskDataVO taskDataVO) throws Exception {
		update("taskDataDAO.updateCommanderGuideById", taskDataVO);
	}
	
	public void updateCommanderGuide1_ById(TaskDataVO taskDataVO) throws Exception {
		update("taskDataDAO.updateCommanderGuide1_ById", taskDataVO);
	}
	
	public void updateCommanderGuide2_ById(TaskDataVO taskDataVO) throws Exception {
		update("taskDataDAO.updateCommanderGuide2_ById", taskDataVO);
	}
	
	public void updateCommanderGuide3_ById(TaskDataVO taskDataVO) throws Exception {
		update("taskDataDAO.updateCommanderGuide3_ById", taskDataVO);
	}
	
	// /////////////////////////////////
}
