package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.RequestChecklistVO;
import egovframework.main.service.VO.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("requestChecklistDAO")
public class RequestChecklistDAO  extends EgovAbstractDAO {
	public void insertRequestChecklist(RequestChecklistVO requestChecklistVO) throws Exception {
		insert("requestChecklistDAO.insertRequestChecklist", requestChecklistVO);
	}

	@SuppressWarnings("unchecked")
	public List<RequestChecklistVO> getRequestedList(UserVO userVO) throws Exception {		
		return (List<RequestChecklistVO>) list("requestChecklistDAO.getRequestedListByLogined", userVO);
	}
	
	@SuppressWarnings("unchecked")
    public List<RequestChecklistVO> getChecklistRequest( RequestChecklistVO requestChecklistVO) throws Exception{
        return (List<RequestChecklistVO>) list("requestChecklistDAO.getChecklistRequest", requestChecklistVO);
    }


	public void RequestDeny(RequestChecklistVO requestChecklistVO) throws Exception{
		update("RequestChecklistDAO.RequestDeny",requestChecklistVO);
	}

	public void RequestCreate(RequestChecklistVO requestChecklistVO) throws Exception{
		update("RequestChecklistDAO.RequestCreate", requestChecklistVO);
		
	}

	@SuppressWarnings("unchecked")
	public List<ChecklistItemVO> getCheckCreate(ChecklistItemVO checklistItemVO) throws Exception{
		return (List<ChecklistItemVO>) list("requestChecklistDAO.getCheckCreate", checklistItemVO);
	}

	public void RequestCreateNew(ChecklistItemVO checklistItemVO) throws Exception{
		insert("RequestChecklistDAO.RequestCreateNew", checklistItemVO);
	}
	
	// add 190325
	@SuppressWarnings("unchecked")
	public List<RequestChecklistVO> getLimitReqCheckist(String incdt_idtf_cd) throws Exception {		
		return (List<RequestChecklistVO>) list("requestChecklistDAO.getLimitReqCheckist", incdt_idtf_cd);
	}

	public RequestChecklistVO getCheckRequestSelect(
			RequestChecklistVO requestChecklistVO) {
		return (RequestChecklistVO) list("requestChecklistDAO.getChecklistRequest" );
	}
	
	public RequestChecklistVO getRqstChkBySeq(String seq) throws Exception {
		return (RequestChecklistVO) select("requestChecklistDAO.getRqstChkBySeq", seq);
	}

	public void RequestAccept(RequestChecklistVO requestChecklistVO) {
		update("RequestChecklistDAO.RequestAccept", requestChecklistVO);
		
	}

	@SuppressWarnings("unchecked")
	public List<ChecklistItemVO> getChecklist(ChecklistItemVO checklistItemVO) throws Exception{
		return (List<ChecklistItemVO>) list("requestChecklistDAO.getChecklist", checklistItemVO);
	}

	// get count
	public int getNewCount(UserVO userVO) throws Exception {
		return (Integer) select("requestChecklistDAO.getNewCount", userVO);
	}
	
	// get limit list for main
	@SuppressWarnings("unchecked")
	public List<RequestChecklistVO> getRequestedListForMain(String srvno) throws Exception {
		return (List<RequestChecklistVO>) list("requestChecklistDAO.getRequestedListForMain", srvno);
	}

	@SuppressWarnings("unchecked")
	public List<RequestChecklistVO> searchRequest(
			RequestChecklistVO requestChecklistVO)throws Exception {
		// TODO 자동 생성된 메소드 스텁
		return (List<RequestChecklistVO>) list("requestChecklistDAO.searchRequest", requestChecklistVO);
	}
	
	public List<RequestChecklistVO> getListByCondition(RequestChecklistVO requestChecklistVO) throws Exception {
		return (List<RequestChecklistVO>) list("requestChecklistDAO.getListByCondition", requestChecklistVO);
	}
}