package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.RequestActivityService;
import egovframework.main.service.VO.RequestActivityVO;
import egovframework.main.service.common.RequestActivityDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("requestActivityService")
public class RequestActivityServiceImpl extends EgovAbstractServiceImpl implements RequestActivityService {
	@Resource(name = "requestActivityDAO")
	private RequestActivityDAO requestActivityDAO;
	
	public void insertRequestActivityVO(RequestActivityVO requestActivityVO) throws Exception {
		requestActivityDAO.insertRequestActivityVO(requestActivityVO);
	}
		
	public RequestActivityVO getRequestActivityVO() throws Exception {
		return requestActivityDAO.getRequestActivityVO(); 
	}

	public List<RequestActivityVO> getRequestActivityListVO(String srvno) throws Exception {
		return requestActivityDAO.getRequestActivityListVO(srvno);
	}

	@Override
	public List<RequestActivityVO> getRequestList(
			RequestActivityVO requestActivityVO) throws Exception {
		return requestActivityDAO.getRequestlist(requestActivityVO);
	}

	@Override
	public void RequestActivityResult(RequestActivityVO requestActivityVO)
			throws Exception {
		requestActivityDAO.RequestActivityResult(requestActivityVO);
		
	}
	
	// get limit
	public List<RequestActivityVO> getLimitReqActList(String incdt_idtf_cd)	throws Exception {
		return requestActivityDAO.getLimitReqActList(incdt_idtf_cd);
	}
	
	public RequestActivityVO getRqstActBySeq(String seq) throws Exception {
		return requestActivityDAO.getRqstActBySeq(seq);
	}
}
