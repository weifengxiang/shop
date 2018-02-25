package org.sky.check.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sky.sys.action.BaseController;
import org.sky.sys.exception.ServiceException;
import org.sky.check.model.BaseCheckPlan;
import org.sky.check.model.BaseCheckPlanExample;
import org.sky.check.model.BaseCheckPlanExample.Criteria;
import org.sky.check.service.BaseCheckPlanService;
import org.sky.sys.utils.JsonUtils;
import org.sky.sys.utils.Page;
import org.sky.sys.utils.PageListData;
import org.sky.sys.utils.ResultData;
import org.sky.sys.utils.StringUtils;
import org.sky.sys.utils.TreeStru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
@Controller
public class BaseCheckPlanController extends BaseController{
	@Autowired
	private BaseCheckPlanService basecheckplanService;
	
	public BaseCheckPlanController() {
		// TODO Auto-generated constructor stub
	}
	/**
	*显示盘点计划列表页面
	**/
	@RequestMapping(value = "/base/BaseCheckPlan/initBaseCheckPlanListPage", method = { RequestMethod.GET })
	public String initBaseCheckPlanListPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/check/listbasecheckplan";
	}
	/**
	 * 盘点计划分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/base/BaseCheckPlan/getBaseCheckPlanByPage", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseCheckPlanByPage(
			HttpServletRequest request, 
			HttpServletResponse response){
		String filter = request.getParameter("filter");
		Map filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= super.getPage(request);
		BaseCheckPlanExample pote= new BaseCheckPlanExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		if(!StringUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		pote.setPage(p);
		PageListData pageData = basecheckplanService.getBaseCheckPlanByPage(pote);
		return JsonUtils.obj2json(pageData);
	}
	/**
	 * 盘点计划商品门类树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/base/BaseCheckPlan/getBaseCheckPlanCateTree", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody List<TreeStru> getBaseCheckPlanCateTree(HttpServletRequest request, 
			HttpServletResponse response){
		String data= request.getParameter("data");
		Map dataMap=null;
		if(!StringUtils.isNull(data)){
			dataMap = JsonUtils.json2map(data);
		}
		return basecheckplanService.getBaseCheckPlanCateTree(dataMap);
	}
	/**
	*显示盘点计划新增页面
	**/
	@RequestMapping(value = "/base/BaseCheckPlan/initAddBaseCheckPlanPage", method = { RequestMethod.GET })
	public String initAddBaseCheckPlanPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/check/editbasecheckplan";
	}
	/**
	*显示盘点计划修改页面
	**/
	@RequestMapping(value = "/base/BaseCheckPlan/initEditBaseCheckPlanPage", method = { RequestMethod.GET })
	public String initEditBaseCheckPlanPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/check/editbasecheckplan";
	}
	/**
	*显示盘点计划详细页面
	**/
	@RequestMapping(value = "/base/BaseCheckPlan/initDetailBaseCheckPlanPage", method = { RequestMethod.GET })
	public String initDetailBaseCheckPlanPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/check/detailbasecheckplan";
	}
	/**
	*保存新增/修改盘点计划
	**/
	@RequestMapping(value = "/base/BaseCheckPlan/saveAddEditBaseCheckPlan", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveAddEditBaseCheckPlan(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			BaseCheckPlan edit = (BaseCheckPlan) getEntityBean(request,BaseCheckPlan.class);
			basecheckplanService.saveAddEditBaseCheckPlan(edit);
			rd.setCode(ResultData.code_success);
			rd.setName("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd.setCode(ResultData.code_error);
			rd.setName("保存失败<br>"+e.getMessage());
		}
		return JsonUtils.obj2json(rd);
	}
	/**
	*删除盘点计划
	**/
	@RequestMapping(value = "/base/BaseCheckPlan/delBaseCheckPlan", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String delBaseCheckPlan(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String delRows=request.getParameter("delRows");
			List de=JsonUtils.json2list(delRows, BaseCheckPlan.class);
			basecheckplanService.delBaseCheckPlanById(de);
			rd.setCode(ResultData.code_success);
			rd.setName("删除成功");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd.setCode(ResultData.code_error);
			rd.setName("删除失败<br>"+e.getMessage());
		}
		return JsonUtils.obj2json(rd);
	}
	/**
	*根据主键查询盘点计划
	**/
	@RequestMapping(value = "/base/BaseCheckPlan/getBaseCheckPlanById", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseCheckPlanById(
			HttpServletRequest request, 
			HttpServletResponse response){
		String id = request.getParameter("id");
		BaseCheckPlan bean = basecheckplanService.getBaseCheckPlanById(id);
		return JsonUtils.obj2json(bean);
	}
}