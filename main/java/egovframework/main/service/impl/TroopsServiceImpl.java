package egovframework.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.main.service.TroopsService;
import egovframework.main.service.VO.TroopsVO;
import egovframework.main.service.common.TroopsDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("troopsService")

public class TroopsServiceImpl  extends EgovAbstractServiceImpl implements TroopsService{

	@Resource(name="troopsDAO")
	private TroopsDAO troopsDAO;

	@Override
	public List<TroopsVO> getTroopsList(TroopsVO troopsVO) throws Exception {
		return troopsDAO.getTroopsList(troopsVO);
	}
}
