package org.sky.dingding.service;

import org.apache.log4j.Logger;
import org.sky.check.client.BaseCheckDetailMapper;
import org.sky.check.model.BaseCheckDetail;
import org.sky.sys.client.SysCommonMapper;
import org.sky.sys.exception.ServiceException;
import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 钉钉服务层
 * @author weifx
 *
 */
@Service
public class DDService {
	private Logger logger = Logger.getLogger(DDService.class);
	@Autowired
	private BaseCheckDetailMapper basecheckdetailmapper;
	@Autowired
	private SysCommonMapper syscommonmapper; 
	/**
	 * 钉钉盘点
	 */
	@Transactional
	public void check(String id,String result) throws ServiceException{
		try {
			BaseCheckDetail bcd = new BaseCheckDetail();
			bcd.setId(id);
			bcd.setUpdater(BspUtils.getLoginUser().getCode());
			bcd.setUpdateTime(syscommonmapper.querySysDate());
			bcd.setResult(result);
			bcd.setState("1");
			basecheckdetailmapper.updateByPrimaryKeySelective(bcd);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
