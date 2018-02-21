package org.sky.base.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sky.sys.action.BaseController;
import org.sky.sys.exception.ServiceException;
import org.sky.base.model.BaseComCate;
import org.sky.base.model.BaseComCateExample;
import org.sky.base.model.BaseComCateExample.Criteria;
import org.sky.base.service.BaseComCateService;
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
public class BaseComCateController extends BaseController{
	@Autowired
	private BaseComCateService basecomcateService;
	
	public BaseComCateController() {
		// TODO Auto-generated constructor stub
	}
	/**
	*显示商品门类管理列表页面
	**/
	@RequestMapping(value = "/sys/BaseComCate/initBaseComCateListPage", method = { RequestMethod.GET })
	public String initBaseComCateListPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/listbasecomcate";
	}
	/**
	 * 商品门类管理分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/BaseComCate/getBaseComCateByPage", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseComCateByPage(
			HttpServletRequest request, 
			HttpServletResponse response){
		String filter = request.getParameter("filter");
		Map filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= super.getPage(request);
		BaseComCateExample pote= new BaseComCateExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		if(!StringUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		pote.setPage(p);
		PageListData pageData = basecomcateService.getBaseComCateByPage(pote);
		return JsonUtils.obj2json(pageData);
	}
	/**
	*显示商品门类管理新增页面
	**/
	@RequestMapping(value = "/sys/BaseComCate/initAddBaseComCatePage", method = { RequestMethod.GET })
	public String initAddBaseComCatePage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/editbasecomcate";
	}
	/**
	*显示商品门类管理修改页面
	**/
	@RequestMapping(value = "/sys/BaseComCate/initEditBaseComCatePage", method = { RequestMethod.GET })
	public String initEditBaseComCatePage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/editbasecomcate";
	}
	/**
	*显示商品门类管理详细页面
	**/
	@RequestMapping(value = "/sys/BaseComCate/initDetailBaseComCatePage", method = { RequestMethod.GET })
	public String initDetailBaseComCatePage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/detailbasecomcate";
	}
	/**
	*保存新增/修改商品门类管理
	**/
	@RequestMapping(value = "/sys/BaseComCate/saveAddEditBaseComCate", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveAddEditBaseComCate(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			BaseComCate edit = (BaseComCate) getEntityBean(request,BaseComCate.class);
			basecomcateService.saveAddEditBaseComCate(edit);
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
	*删除商品门类管理
	**/
	@RequestMapping(value = "/sys/BaseComCate/delBaseComCate", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String delBaseComCate(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String delRows=request.getParameter("delRows");
			List de=JsonUtils.json2list(delRows, BaseComCate.class);
			basecomcateService.delBaseComCateById(de);
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
	*根据主键查询商品门类管理
	**/
	@RequestMapping(value = "/sys/BaseComCate/getBaseComCateById", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseComCateById(
			HttpServletRequest request, 
			HttpServletResponse response){
		String id = request.getParameter("id");
		BaseComCate bean = basecomcateService.getBaseComCateById(id);
		return JsonUtils.obj2json(bean);
	}
}