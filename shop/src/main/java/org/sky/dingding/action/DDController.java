package org.sky.dingding.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sky.check.model.BaseCheckPlan;
import org.sky.check.service.BaseCheckPlanService;
import org.sky.dingding.service.DDService;
import org.sky.sys.action.BaseController;
import org.sky.sys.exception.ServiceException;
import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.JsonUtils;
import org.sky.sys.utils.ResultData;
import org.sky.sys.utils.StringUtils;
import org.sky.sys.utils.TreeStru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 * 钉钉业务Controller
 * @author weifx
 *
 */
@Controller
public class DDController extends BaseController {
	@Autowired
	private DDService ddservice;
	@Autowired
	private BaseCheckPlanService basecheckplanservice;
	/**
	 * 
	 * @param state
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dd/DDController/main", method = { RequestMethod.GET })
	public ModelAndView main(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/dingding/main");
		return mv;
	}
	/**
	 * 商品盘查
	 * @param name
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dd/DDController/check", method = { RequestMethod.GET })
	public ModelAndView check(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String params = request.getParameter("params");
		Map paramMap = JsonUtils.json2map(params);
		//盘查页面获取最新的计划编号
		String organCode = BspUtils.getLoginUser().getOrganCode();
		BaseCheckPlan bcp = basecheckplanservice.getLasterBaseCheckPlan(organCode);
		mv.addObject("checkPlan", bcp);
		mv.addObject("params", paramMap);
		mv.setViewName("jsp/dingding/check");
		return mv;
	}
	/**
	 * 盘点计划商品门类树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dd/DDController/getBaseCheckPlanCateTree", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody List<TreeStru> getBaseCheckPlanCateTree(HttpServletRequest request, 
			HttpServletResponse response){
		String data= request.getParameter("data");
		Map dataMap=null;
		if(!StringUtils.isNull(data)){
			dataMap = JsonUtils.json2map(data);
		}
		return ddservice.getBaseCheckPlanCateTree(dataMap);
	}
	/**
	 * 显示盘点商品详情列表页面
	 * @param planCode 计划编号
	 * @param cateCode 商品门类编号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dd/DDController/initBaseCheckDetailListPage/{planCode}/{cateCode}/{state}", method = { RequestMethod.GET })
	public ModelAndView initBaseCheckDetailListPage(
			@PathVariable String planCode,
			@PathVariable String cateCode,
			@PathVariable String state,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("planCode", planCode);
		mv.addObject("cateCode", cateCode);
		mv.addObject("state", state);
		mv.setViewName("jsp/dingding/commodity");
		return mv;
	}
	/**
	*商品盘查
	**/
	@RequestMapping(value = "/dd/DDController/{id}/{result}", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveCheckPlan(
			@PathVariable String id,
			@PathVariable String result,
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			ddservice.check(id, result);
			rd.setCode(ResultData.code_success);
			rd.setName("盘点成功");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd.setCode(ResultData.code_error);
			rd.setName("盘点失败<br>"+e.getMessage());
		}
		return JsonUtils.obj2json(rd);
	}
}
