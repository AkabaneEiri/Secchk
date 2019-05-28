package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.ClsVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @기능명 : 분류별 관계 정보
 * @기능설명 : 분류별 관계에 대한 정보 입력, 검색 및 삭제
 * @작성자 : 박승원
 * @작성일 : 2019. 5. 16.
 */
@Repository("clsDAO")
public class ClsDAO extends EgovAbstractDAO {
	
	// insert cls vo
	public void insertClsVO(ClsVO clsVO) throws Exception {
		insert("clsDAO.insertClsVO", clsVO);
	}
	
	// get all cls list
	public List<ClsVO> getAllClsList() throws Exception {
		return (List<ClsVO>) list("clsDAO.getAllClsList");
	}
}
