package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.VO.TaskDataVO;
import egovframework.main.service.VO.TaskVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.main.service.VO.ListsearchVO;

@Repository("taskDAO")
public class TaskDAO extends EgovAbstractDAO{

	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskList(MemberSearchVO membersearchVO) throws Exception {
		return (List<TaskVO>) list("taskDAO.selectTaskList", membersearchVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskLarge(MemberSearchVO membersearchVO) throws Exception{
		return (List<TaskVO>) list("taskDAO.selectTaskLarge", membersearchVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskMiddle(MemberSearchVO membersearchVO) throws Exception{
		return (List<TaskVO>) list("taskDAO.selectTaskMiddle", membersearchVO);
	}

	// ������ ���� - insert data
	public int insertTask(TaskVO taskVO) throws Exception {
		return (int) insert("taskDAO.insertTask", taskVO); // insert() is 'int' type function
	}

	public TaskVO getTaskVO(ListsearchVO listsearchVO) throws Exception {
		return (TaskVO) select("taskDAO.selectTask", listsearchVO);
	}
	
	// ����� ��� ������ �˻� - search data	
	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskListVO(String srvno) throws Exception {
		return (List<TaskVO>) list("taskDAO.selectTaskListBySrvno", srvno);
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskListVO(ListsearchVO listsearchVO) throws Exception {
		System.out.println("list check..");
		return (List<TaskVO>) list("taskDAO.selectTaskListByAct", listsearchVO);
	}

	// ��ü ������ ��ȸ -search all
	public List<TaskVO> getAllTaskList() throws Exception {
		return null;
	}
	
	public int updateTaskStart(TaskVO taskVO) throws Exception {
		return (int) update("taskDAO.updateTaskStart", taskVO);
	}
	
	public int updateTaskFinish(TaskVO taskVO) throws Exception {
		return (int) update("taskDAO.updateTaskFinish", taskVO);
	}
	
	public void InsertTask(TaskVO taskVO) {
		 insert("taskDAO.InsertTask",taskVO);
	}

	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskSearch(TaskVO taskVO) {
		return (List<TaskVO>) list("taskDAO.selectTaskOrder", taskVO);
	}
	
	public String getTaskStateCode(String seq) throws Exception {
		return (String) select("taskDAO.selectTaskStateCode", seq);
	}

	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskSearchName(TaskVO taskVO) {
		return (List<TaskVO>) list("taskDAO.getTaskSearchName", taskVO);
	}
	
	// add 190412~ /////////////////////////////
	public int updateTaskStartBySeq(String seq) throws Exception {
		return update("taskDAO.updateTaskStartBySeq", seq);
	}
		
	public int updateTaskFinishBySeq(String seq) throws Exception {
		return update("taskDAO.updateTaskFinishBySeq", seq);
	}
	
	public TaskVO getTaskByTaskData(TaskDataVO taskDataVO) throws Exception {
		return (TaskVO) select("taskDAO.selectTaskByTaskData", taskDataVO);		
	}
	
	public void updateTaskFinishByData(TaskDataVO taskDataVO) throws Exception {
		update("taskDAO.updateTaskFinishByData", taskDataVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskListById(String id) throws Exception {
		return (List<TaskVO>) list("taskDAO.selectTaskListById", id);
	}
	
	// for main
	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskListForMain(String srvno) throws Exception {
		return (List<TaskVO>) list("taskDAO.selectTaskListForMain", srvno);
	}
	////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskSelected(TaskVO taskVO) {
		return (List<TaskVO>) list("taskDAO.getTaskSelected", taskVO);
	}

	@SuppressWarnings("unchecked")
	public List<TaskVO> getActivityRequire(TaskVO taskVO) throws Exception{
		return (List<TaskVO>) list("taskDAO.getActivityRequire", taskVO);
	}

	public void InsertActivity(TaskVO taskVO) throws Exception{
		insert("taskDAO.InsertActivity", taskVO);
	}

	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskSearchNameWithOption(TaskVO taskVO) throws Exception{
		return (List<TaskVO>) list("taskDAO.getTaskSearchNameWithOption", taskVO);
	}

	@SuppressWarnings("unchecked")
	public List<TaskVO> getClsList(TaskVO taskVO) throws Exception{
		return (List<TaskVO>) list("taskDAO.getClsList", taskVO);
	}

	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskSearchOption(TaskVO taskVO) throws Exception{
		return (List<TaskVO>) list("taskDAO.getTaskSearchOption", taskVO);
	}

	@SuppressWarnings("unchecked")
	public List<TaskVO> getctlgCode(MemberSearchVO membersearchVO) throws Exception{
		return (List<TaskVO>) list("taskDAO.getctlgCode", membersearchVO);
	}
	
	///////////
	@SuppressWarnings("unchecked")
	public List<TaskVO> getTaskListByCondition(TaskVO taskVO) throws Exception {
		return (List<TaskVO>) list("taskDAO.getTaskListByCondition", taskVO);
	}

	public void deleteTask(TaskVO taskVO) throws Exception{
		delete("taskDAO.deleteTask", taskVO);
	}

	@SuppressWarnings("unchecked")
	public List<TaskVO> selectId(TaskVO taskVO) {
		return (List<TaskVO>) list("taskDAO.selectId", taskVO);
	}
}
