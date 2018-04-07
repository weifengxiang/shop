package org.sky.report.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sky.report.client.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName:  ReportService   
 * @Description:TODO(统计报表服务类)   
 * @author: weifx 
 * @date:   2018年4月7日 下午9:07:19   
 * @version V1.0    
 * @Copyright: 2018 XXX. All rights reserved.
 */
@Service
public class ReportService {
	@Autowired
	private ReportMapper reportMapper;
	/**
	 * 员工检查情况表
	 * @param shopCode(门店编号)
	 * @param planCode(计划编号)
	 * @return
	 */
	public List selectEmpCheckDetail(String shopCode,String planCode) {
		List res = null;
		Map params = new HashMap();
		params.put("shopCode", shopCode);
		params.put("planCode", planCode);
		res = reportMapper.selectEmpCheckDetail(params);
		return res;
	}

}
