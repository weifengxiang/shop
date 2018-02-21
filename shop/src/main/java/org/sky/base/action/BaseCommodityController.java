package org.sky.base.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sky.sys.action.BaseController;
import org.sky.sys.exception.ServiceException;
import org.sky.base.model.BaseComCate;
import org.sky.base.model.BaseCommodity;
import org.sky.base.model.BaseCommodityExample;
import org.sky.base.model.BaseCommodityExample.Criteria;
import org.sky.base.service.BaseComCateService;
import org.sky.base.service.BaseCommodityService;
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
public class BaseCommodityController extends BaseController{
	@Autowired
	private BaseCommodityService basecommodityService;
	@Autowired
	private BaseComCateService basecomcateService;
	
	public BaseCommodityController() {
		// TODO Auto-generated constructor stub
	}
	/**
	*显示商品管理列表页面
	**/
	@RequestMapping(value = "/base/BaseCommodity/initBaseCommodityListPage", method = { RequestMethod.GET })
	public String initBaseCommodityListPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/listbasecommodity";
	}
	/**
	 * 商品类别树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/base/BaseCommodity/getComCateTree", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody List<TreeStru> getComCateTree(HttpServletRequest request, 
			HttpServletResponse response){
		String data= request.getParameter("data");
		Map dataMap=null;
		if(!StringUtils.isNull(data)){
			dataMap = JsonUtils.json2map(data);
		}
		return basecommodityService.getComCateTreeData(dataMap);
	}
	/**
	*显示商品门类管理新增页面
	**/
	@RequestMapping(value = "/base/BaseCommodity/initAddBaseComCatePage", method = { RequestMethod.GET })
	public String initAddBaseComCatePage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/editbasecomcate";
	}
	/**
	*显示商品门类管理修改页面
	**/
	@RequestMapping(value = "/base/BaseCommodity/initEditBaseComCatePage", method = { RequestMethod.GET })
	public String initEditBaseComCatePage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/editbasecomcate";
	}
	/**
	*显示商品门类管理详细页面
	**/
	@RequestMapping(value = "/base/BaseCommodity/initDetailBaseComCatePage", method = { RequestMethod.GET })
	public String initDetailBaseComCatePage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/detailbasecomcate";
	}
	/**
	*保存新增/修改商品门类管理
	**/
	@RequestMapping(value = "/base/BaseCommodity/saveAddEditBaseComCate", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
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
	@RequestMapping(value = "/base/BaseCommodity/delBaseComCate", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String delBaseComCate(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String data=request.getParameter("data");
			BaseComCate de=JsonUtils.json2pojo(data, BaseComCate.class);
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
	@RequestMapping(value = "/base/BaseCommodity/getBaseComCateById", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseComCateById(
			HttpServletRequest request, 
			HttpServletResponse response){
		String id = request.getParameter("id");
		BaseComCate bean = basecomcateService.getBaseComCateById(id);
		return JsonUtils.obj2json(bean);
	}
	/**
	 * 商品管理分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/base/BaseCommodity/getBaseCommodityByPage", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
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
	*显示商品管理新增页面
	**/
	@RequestMapping(value = "/base/BaseCommodity/initAddBaseCommodityPage", method = { RequestMethod.GET })
	public String initAddBaseCommodityPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/editbasecommodity";
	}
	/**
	*显示商品管理修改页面
	**/
	@RequestMapping(value = "/base/BaseCommodity/initEditBaseCommodityPage", method = { RequestMethod.GET })
	public String initEditBaseCommodityPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/editbasecommodity";
	}
	/**
	*显示商品管理详细页面
	**/
	@RequestMapping(value = "/base/BaseCommodity/initDetailBaseCommodityPage", method = { RequestMethod.GET })
	public String initDetailBaseCommodityPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/commodity/detailbasecommodity";
	}
	/**
	*保存新增/修改商品管理
	**/
	@RequestMapping(value = "/base/BaseCommodity/saveAddEditBaseCommodity", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
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
	*删除商品管理
	**/
	@RequestMapping(value = "/base/BaseCommodity/delBaseCommodity", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
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
	*根据主键查询商品管理
	**/
	@RequestMapping(value = "/base/BaseCommodity/getBaseCommodityById", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseCommodityById(
			HttpServletRequest request, 
			HttpServletResponse response){
		String id = request.getParameter("id");
		BaseCommodity bean = basecommodityService.getBaseCommodityById(id);
		return JsonUtils.obj2json(bean);
	}
}