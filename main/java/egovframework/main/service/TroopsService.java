package egovframework.main.service;

import java.util.List;

import egovframework.main.service.VO.TroopsVO;

public interface TroopsService {

	List<TroopsVO> getTroopsList(TroopsVO troopsVO) throws Exception;

}
