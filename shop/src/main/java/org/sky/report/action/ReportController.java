package org.sky.report.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sky.check.model.BaseCheckPlan;
import org.sky.check.model.BaseCheckPlanExample;
import org.sky.check.service.BaseCheckPlanService;
import org.sky.sys.action.BaseController;
import org.sky.sys.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * @ClassName:  ReportController   
 * @Description:TODO(统计报表)   
 * @author: weifx 
 * @date:   2018年4月4日 下午10:35:50   
 * @version V1.0    
 * @Copyright: 2018 XXX. All rights reserved.
 */
@Controller
public class ReportController extends BaseController{
	
	@Autowired
	private BaseCheckPlanService cpService;
	/**
	 * 员工检查情况表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/report/empchecktab/initPage", method = { RequestMethod.GET })
	public String initBaseCommodityListPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/report/empchecktab";
	}
	@RequestMapping(value = "/report/checkplan/getCheckPlanByOrganCode", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getCheckPlanByOrganCode(
			HttpServletRequest request, HttpServletResponse response) {
		String filter = request.getParameter("filter");
		Map filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		BaseCheckPlanExample ep = new BaseCheckPlanExample();
		ep.createCriteria();
		ep.integratedQuery(filterMap);
		ep.setOrderByClause("create_time desc");
		List<BaseCheckPlan> cpList = cpService.selectBaseCheckPlanByExample(ep);
		return JsonUtils.obj2json(cpList);
	}

}
