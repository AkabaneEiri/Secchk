package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.RequestChecklistService;
import egovframework.main.service.VO.ChecklistItemVO;
import egovframework.main.service.VO.RequestChecklistVO;
import egovframework.main.service.VO.UserVO;
import egovframework.main.service.common.RequestChecklistDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("requestChecklistService")
public class RequestChecklistServiceImpl extends EgovAbstractServiceImpl implements RequestChecklistService {
	@Resource(name="requestChecklistDAO")
	private RequestChecklistDAO requestChecklistDAO;
	
	public void insertRequestChecklist(RequestChecklistVO requestChecklistVO) throws Exception {
		requestChecklistDAO.insertRequestChecklist(requestChecklistVO);
	}
	
	public List<RequestChecklistVO> getRequestedList(UserVO userVO) throws Exception {
		return requestChecklistDAO.getRequestedList(userVO);
	}
	
	public List<RequestChecklistVO> getChecklistRequest(RequestChecklistVO requestChecklistVO) throws Exception{
        return requestChecklistDAO.getChecklistRequest(requestChecklistVO);
    }

	@Override
	public void RequestDeny(RequestChecklistVO requestChecklistVO) throws Exception{
		requestChecklistDAO.RequestDeny(requestChecklistVO);
		
	}

	@Override
	public void RequestCreate(RequestChecklistVO requestChecklistVO)
			throws Exception {
		requestChecklistDAO.RequestCreate(requestChecklistVO);
		
	}

	@Override
	public List<ChecklistItemVO> getCheckCreate(ChecklistItemVO checklistItemVO)
			throws Exception {
			return requestChecklistDAO.getCheckCreate(checklistItemVO);
		
	}

	@Override
	public void RequestCreateNew(ChecklistItemVO checklistItemVO)
			throws Exception {
			requestChecklistDAO.RequestCreateNew(checklistItemVO);
		
	}
	
	// add 190325
	public List<RequestChecklistVO> getLimitReqCheckist(String incdt_idtf_cd) throws Exception {		
		return requestChecklistDAO.getLimitReqCheckist(incdt_idtf_cd);
	}

	@Override
	public RequestChecklistVO getCheckRequestSelect(
			RequestChecklistVO requestChecklistVO) throws Exception {
		// TODO 자동 생성된 메소드 스텁
		return requestChecklistDAO.getCheckRequestSelect(requestChecklistVO);
	}
	
	public RequestChecklistVO getRqstChkBySeq(String seq) throws Exception {
		return requestChecklistDAO.getRqstChkBySeq(seq);
	}

	@Override
	public void RequestAccept(RequestChecklistVO requestChecklistVO)
			throws Exception {
		 requestChecklistDAO.RequestAccept(requestChecklistVO);
		
	}

	@Override
	public List<ChecklistItemVO> getChecklist(ChecklistItemVO checklistItemVO)
			throws Exception {
		return requestChecklistDAO.getChecklist(checklistItemVO);
	}

}


