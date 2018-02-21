package org.sky.base.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sky.sys.action.BaseController;
import org.sky.sys.exception.ServiceException;
import org.sky.base.model.BaseCommodity;
import org.sky.base.model.BaseCommodityExample;
import org.sky.base.model.BaseCommodityExample.Criteria;
import org.sky.base.service.BaseCommodityService;
import org.sky.sys.utils.JsonUtils;
import org.sky.sys.utils.Page;
import org.sky.sys.utils.PageListData;
import org.sky.sys.utils.ResultData;
import org.sky.sys.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
@Controller
public class BaseCommodityController extends BaseController{
	@Autowired
	private BaseCommodityService basecommodityService;
	
	public BaseCommodityController() {
		// TODO Auto-generated constructor stub
	}
	/**
	*显示鍟嗗搧绠＄悊列表页面
	**/
	@RequestMapping(value = "/sys/BaseCommodity/initBaseCommodityListPage", method = { RequestMethod.GET })
	public String initBaseCommodityListPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/listbasecommodity";
	}
	/**
	 * 鍟嗗搧绠＄悊分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/BaseCommodity/getBaseCommodityByPage", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseCommodityByPage(
			HttpServletRequest request, 
			HttpServletResponse response){
		String filter = request.getParameter("filter");
		Map filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= super.getPage(request);
		BaseCommodityExample pote= new BaseCommodityExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		if(!StringUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		pote.setPage(p);
		PageListData pageData = basecommodityService.getBaseCommodityByPage(pote);
		return JsonUtils.obj2json(pageData);
	}
	/**
	*显示鍟嗗搧绠＄悊新增页面
	**/
	@RequestMapping(value = "/sys/BaseCommodity/initAddBaseCommodityPage", method = { RequestMethod.GET })
	public String initAddBaseCommodityPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/editbasecommodity";
	}
	/**
	*显示鍟嗗搧绠＄悊修改页面
	**/
	@RequestMapping(value = "/sys/BaseCommodity/initEditBaseCommodityPage", method = { RequestMethod.GET })
	public String initEditBaseCommodityPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/editbasecommodity";
	}
	/**
	*显示鍟嗗搧绠＄悊详细页面
	**/
	@RequestMapping(value = "/sys/BaseCommodity/initDetailBaseCommodityPage", method = { RequestMethod.GET })
	public String initDetailBaseCommodityPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/detailbasecommodity";
	}
	/**
	*保存新增/修改鍟嗗搧绠＄悊
	**/
	@RequestMapping(value = "/sys/BaseCommodity/saveAddEditBaseCommodity", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveAddEditBaseCommodity(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			BaseCommodity edit = (BaseCommodity) getEntityBean(request,BaseCommodity.class);
			basecommodityService.saveAddEditBaseCommodity(edit);
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
	*删除鍟嗗搧绠＄悊
	**/
	@RequestMapping(value = "/sys/BaseCommodity/delBaseCommodity", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String delBaseCommodity(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String delRows=request.getParameter("delRows");
			List de=JsonUtils.json2list(delRows, BaseCommodity.class);
			basecommodityService.delBaseCommodityById(de);
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
	*根据主键查询鍟嗗搧绠＄悊
	**/
	@RequestMapping(value = "/sys/BaseCommodity/getBaseCommodityById", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseCommodityById(
			HttpServletRequest request, 
			HttpServletResponse response){
		String id = request.getParameter("id");
		BaseCommodity bean = basecommodityService.getBaseCommodityById(id);
		return JsonUtils.obj2json(bean);
	}
}