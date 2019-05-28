package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.CheckApprovalVO;
import egovframework.main.service.VO.RequestActivityVO;
import egovframework.main.service.VO.RequestChecklistVO;
import egovframework.main.service.VO.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 수시 안전관리활동 요청 관리
 * @기능설명 : 수시 안전관리활동 요청 입력, 검색 및 삭제
 * @작성자 : 박승원
 * @작성일 : 2019. 2. 23.
 */
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
	
	// get count
	public int getNewCount(UserVO userVO) throws Exception {
		return (Integer) select("requestActivityDAO.getNewCount", userVO);
	}
	
	// get limit list for main
	@SuppressWarnings("unchecked")
	public List<RequestActivityVO> getRequestedListForMain(String srvno) throws Exception {
		return (List<RequestActivityVO>) list("requestActivityDAO.getRequestedListForMain", srvno);
	}

	@SuppressWarnings("unchecked")
	public List<RequestActivityVO> searchApproval(
			RequestActivityVO requestActivityVO) throws Exception{
		return (List<RequestActivityVO>) list("requestActivityDAO.searchApproval", requestActivityVO);
	}
	
	public List<RequestActivityVO> getListByCondition(RequestActivityVO requestActivityVO) throws Exception {
		return (List<RequestActivityVO>) list("requestActivityDAO.getListByCondition", requestActivityVO);
	}
}
