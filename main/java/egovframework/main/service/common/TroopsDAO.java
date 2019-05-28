package egovframework.main.service.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.main.service.VO.TroopsVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("troopsDAO")
public class TroopsDAO extends EgovAbstractDAO{

	@SuppressWarnings("unchecked")
	public List<TroopsVO> getTroopsList(TroopsVO troopsVO) {
		return  (List<TroopsVO>) list("troopsDAO.getTroopsList", troopsVO);
	}

}
