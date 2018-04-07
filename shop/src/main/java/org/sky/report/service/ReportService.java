package org.sky.report.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sky.report.client.ReportMapper;
import org.sky.sys.utils.BigExcel;
import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.CommonUtils;
import org.sky.sys.utils.ConfUtils;
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
	/**
	 * 员工检查情况表生成Excel
	 * @param shopCode(门店编号)
	 * @param planCode(计划编号)
	 * @return
	 */
	public String createEmpCheckDetailExcel(String shopCode,String planCode) {
		String filepath=null;
		List<Map> res = null;
		Map params = new HashMap();
		params.put("shopCode", shopCode);
		params.put("planCode", planCode);
		res = reportMapper.selectEmpCheckDetail(params);
		int i=1;
		for(Map m:res) {
			m.put("IDX",i);
			i++;
		}
		filepath = ConfUtils.getValue("temp_dir")+File.separator+"empcheck"
				   +File.separator+BspUtils.getLoginUser().getCode()+".xls";
		String[] titles={"序号","员工","小类名称","需检查总数","已检查数量","检查完成率（%）"};
		String[] fields={"IDX","CHECKER_NAME","CATENAME","TOTAL","FINISH","RATE"};
		BigExcel.createExcel(filepath,CommonUtils.getCurrentDate("yyyy-MM-dd")+"员工检查情况表", titles,fields,res);
		return filepath;
	}

}
