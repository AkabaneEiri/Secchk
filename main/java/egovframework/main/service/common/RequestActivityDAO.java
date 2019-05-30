package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.RequestActivityVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("requestActivityDAO")
public class RequestActivityDAO extends EgovAbstractDAO {
	// insert vo
	public void insertRequestActivityVO(RequestActivityVO requestActivityVO) throws Exception {
		insert("requestActivityDAO.insertRequestVO", requestActivityVO);
	}
	
	// get vo
	public RequestActivityVO getRequestActivityVO() throws Exception {
		return null;
	}

	// get list
	@SuppressWarnings("unchecked")
	public List<RequestActivityVO> getRequestActivityListVO(String srvno) throws Exception {
		return (List<RequestActivityVO>) list("requestActivityDAO.getRequestListBySrvno", srvno);
	}

	@SuppressWarnings("unchecked")
	public List<RequestActivityVO> getRequestlist(
			RequestActivityVO requestActivityVO) throws Exception{
		return (List<RequestActivityVO>) list("requestActivityDAO.getRequestlist", requestActivityVO);
	}

	public void RequestActivityResult(RequestActivityVO requestActivityVO) throws Exception{
		update("requestActivityDAO.RequestActivityResult", requestActivityVO);	
	}
	
	// get limit
	@SuppressWarnings("unchecked")
	public List<RequestActivityVO> getLimitReqActList(String incdt_idtf_cd) throws Exception {
		return (List<RequestActivityVO>) list("requestActivityDAO.getLimitReqActList", incdt_idtf_cd);
	}
	
	// get vo by seq
	public RequestActivityVO getRqstActBySeq(String seq) throws Exception {
		return (RequestActivityVO) select("requestActivityDAO.getRqstActBySeq",	seq);
	}
}
