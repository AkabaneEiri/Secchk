package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.ListsearchVO;
import egovframework.main.service.VO.MemberSearchVO;
import egovframework.main.service.VO.TaskDataVO;
import egovframework.main.service.VO.TaskVO;

public interface TaskService {
	List<TaskVO> getTaskList(MemberSearchVO membersearchVO) throws Exception;
	List<TaskVO> getTaskLarge(MemberSearchVO membersearchVO) throws Exception;
	List<TaskVO> getTaskMiddle(MemberSearchVO membersearchVO) throws Exception;

	// seungwon
	// ������ ���� - insert data
	int insertTask(TaskVO taskVO) throws Exception;	
	
	TaskVO getTaskVO(ListsearchVO listsearchVO) throws Exception;
	
	// �Ѱ� ������ �˻� - search data
	List<TaskVO> getTaskListVO(String srvno) throws Exception;
	
	// �δ�Ȱ�� ����͸��� ����˻�
	List<TaskVO> getTaskListVO(ListsearchVO listsearchVO) throws Exception;
	
	// ��ü ������ ��ȸ -search all
	List<TaskVO> getAllTaskList() throws Exception;
	
	int updateTaskStart(TaskVO taskVO) throws Exception;
	
	int updateTaskFinish(TaskVO taskVO) throws Exception;
	void InsertTask(TaskVO taskVO) throws Exception;
	List<TaskVO> getTaskSearch(TaskVO taskVO) throws Exception;
	
	// get task state code
	String getTaskStateCode(String seq) throws Exception;
	List<TaskVO> getTaskSearchName(TaskVO taskVO) throws Exception;
	
	
	// add 190412~ /////////////////////////////
	int updateTaskStartBySeq(String seq) throws Exception;
	
	int updateTaskFinishBySeq(String seq) throws Exception;
	
	TaskVO getTaskByTaskData(TaskDataVO taskDataVO) throws Exception;
	
	void updateTaskFinishByData(TaskDataVO taskDataVO) throws Exception;
	
	List<TaskVO> getTaskListById(String id) throws Exception;
	
	//for main
	List<TaskVO> getTaskListForMain(String srvno) throws Exception;
	////////////////////////////////////////////
	List<TaskVO> getTaskSelected(TaskVO taskVO) throws Exception;
}
