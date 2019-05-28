package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.TaskService;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.VO.TaskDataVO;
import egovframework.main.service.VO.TaskVO;
import egovframework.main.service.common.TaskDAO;
import egovframework.main.service.VO.ListsearchVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("taskService")

public class TaskServiceImpl  extends EgovAbstractServiceImpl implements TaskService{
	
	@Resource(name="taskDAO")
	private TaskDAO taskDAO;
	public List<TaskVO> getTaskList(MemberSearchVO membersearchVO) throws Exception {
		return taskDAO.getTaskList(membersearchVO);
	}
	public List<TaskVO> getTaskLarge(MemberSearchVO membersearchVO) throws Exception{
		return taskDAO.getTaskLarge(membersearchVO);
	}
	public List<TaskVO> getTaskMiddle(MemberSearchVO membersearchVO) throws Exception{
		return taskDAO.getTaskMiddle(membersearchVO);
	}
	
	public int insertTask(TaskVO taskVO) throws Exception{
		 return taskDAO.insertTask(taskVO);
	}
		
	public TaskVO getTaskVO(ListsearchVO listsearchVO) throws Exception {
		return taskDAO.getTaskVO(listsearchVO);
	}

	// �Ѱ� ������ �˻� - search data
	public List<TaskVO> getTaskListVO(String srvno) throws Exception{
		return taskDAO.getTaskListVO(srvno);
	}
	
	public List<TaskVO> getTaskListVO(ListsearchVO listsearchVO) throws Exception {
		return taskDAO.getTaskListVO(listsearchVO);
	}

	// ��ü ������ ��ȸ -search all
	public List<TaskVO> getAllTaskList() throws Exception{
		return taskDAO.getAllTaskList();
	}
	
	public int updateTaskStart(TaskVO taskVO) throws Exception{
		return taskDAO.updateTaskStart(taskVO);
	}
	
	public int updateTaskFinish(TaskVO taskVO) throws Exception{
		return taskDAO.updateTaskFinish(taskVO);
	}
	
	public void InsertTask(TaskVO taskVO) throws Exception{
		taskDAO.InsertTask(taskVO);
	}
	@Override
	public List<TaskVO> getTaskSearch(TaskVO taskVO) throws Exception {
		return taskDAO.getTaskSearch(taskVO);
	}
	
	public String getTaskStateCode(String seq) throws Exception {
		return taskDAO.getTaskStateCode(seq);
	}
	@Override
	public List<TaskVO> getTaskSearchName(TaskVO taskVO) throws Exception {
		return taskDAO.getTaskSearchName(taskVO);
	}
	
	
	// add 190412~ /////////////////////////////
	public int updateTaskStartBySeq(String seq) throws Exception {
		return taskDAO.updateTaskStartBySeq(seq);
	}
		
	public int updateTaskFinishBySeq(String seq) throws Exception {
		return taskDAO.updateTaskFinishBySeq(seq);
	}
	
	public TaskVO getTaskByTaskData(TaskDataVO taskDataVO) throws Exception {
		return taskDAO.getTaskByTaskData(taskDataVO);		
	}
	
	public void updateTaskFinishByData(TaskDataVO taskDataVO) throws Exception {
		taskDAO.updateTaskFinishByData(taskDataVO);
	}
	
	public List<TaskVO> getTaskListById(String id) throws Exception {
		return taskDAO.getTaskListById(id);
	}
	
	//for main
	public List<TaskVO> getTaskListForMain(String srvno) throws Exception {
		return taskDAO.getTaskListForMain(srvno);
	}
	////////////////////////////////////////////
	@Override
	public List<TaskVO> getTaskSelected(TaskVO taskVO) throws Exception {
		return taskDAO.getTaskSelected(taskVO);
	}
	@Override
	public List<TaskVO> getActivityRequire(TaskVO taskVO) throws Exception {
		return taskDAO.getActivityRequire(taskVO);
	}
	@Override
	public void InsertActivity(TaskVO taskVO) throws Exception {
		taskDAO.InsertActivity(taskVO);
	}
	@Override
	public List<TaskVO> getTaskSearchNameWithOption(TaskVO taskVO)
			throws Exception {
		return taskDAO.getTaskSearchNameWithOption(taskVO);
	}
	@Override
	public List<TaskVO> getClsList(TaskVO taskVO) throws Exception {
		return taskDAO.getClsList(taskVO) ;
	}
	@Override
	public List<TaskVO> getTaskSearchOption(TaskVO taskVO) throws Exception {
		return taskDAO.getTaskSearchOption(taskVO);
	}
	@Override
	public List<TaskVO> getctlgCode(MemberSearchVO membersearchVO)
			throws Exception {
		return taskDAO.getctlgCode(membersearchVO);
	}
	
	public List<TaskVO> getTaskListByCondition(TaskVO taskVO) throws Exception {
		return taskDAO.getTaskListByCondition(taskVO);
	}
	@Override
	public void deleteTask(TaskVO taskVO) throws Exception {
		taskDAO.deleteTask(taskVO);
	}
	@Override
	public List<TaskVO> selectId(TaskVO taskVO) throws Exception {
		return taskDAO.selectId(taskVO);
	}
}
