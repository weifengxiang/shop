package org.sky.base.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sky.sys.action.BaseController;
import org.sky.sys.exception.ServiceException;
import org.sky.base.model.BaseComcheckCfg;
import org.sky.base.model.BaseComcheckCfgExample;
import org.sky.base.model.BaseComcheckCfgExample.Criteria;
import org.sky.base.service.BaseComcheckCfgService;
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
public class BaseComcheckCfgController extends BaseController{
	@Autowired
	private BaseComcheckCfgService basecomcheckcfgService;
	
	public BaseComcheckCfgController() {
		// TODO Auto-generated constructor stub
	}
	/**
	*显示商品盘点设置列表页面
	**/
	@RequestMapping(value = "/base/BaseComcheckCfg/initBaseComcheckCfgListPage", method = { RequestMethod.GET })
	public String initBaseComcheckCfgListPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/comcheckcfg/listbasecomcheckcfg";
	}
	/**
	 * 商品盘点设置树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/base/BaseComcheckCfg/getBaseComcheckCfg", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody List<TreeStru> getBaseComcheckCfg(HttpServletRequest request, 
			HttpServletResponse response){
		String data= request.getParameter("data");
		Map dataMap=null;
		if(!StringUtils.isNull(data)){
			dataMap = JsonUtils.json2map(data);
		}
		return basecomcheckcfgService.getBaseComcheckCfg(dataMap);
	}
	/**
	 * 商品盘点设置分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/base/BaseComcheckCfg/getBaseComcheckCfgByPage", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseComcheckCfgByPage(
			HttpServletRequest request, 
			HttpServletResponse response){
		String filter = request.getParameter("filter");
		Map filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= super.getPage(request);
		BaseComcheckCfgExample pote= new BaseComcheckCfgExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		if(!StringUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		pote.setPage(p);
		PageListData pageData = basecomcheckcfgService.getBaseComcheckCfgByPage(pote);
		return JsonUtils.obj2json(pageData);
	}
	/**
	*显示商品盘点设置新增页面
	**/
	@RequestMapping(value = "/base/BaseComcheckCfg/initAddBaseComcheckCfgPage", method = { RequestMethod.GET })
	public String initAddBaseComcheckCfgPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/comcheckcfg/editbasecomcheckcfg";
	}
	/**
	*显示商品盘点设置修改页面
	**/
	@RequestMapping(value = "/base/BaseComcheckCfg/initEditBaseComcheckCfgPage", method = { RequestMethod.GET })
	public String initEditBaseComcheckCfgPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/comcheckcfg/editbasecomcheckcfg";
	}
	/**
	*显示商品盘点设置详细页面
	**/
	@RequestMapping(value = "/base/BaseComcheckCfg/initDetailBaseComcheckCfgPage", method = { RequestMethod.GET })
	public String initDetailBaseComcheckCfgPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/base/comcheckcfg/detailbasecomcheckcfg";
	}
	/**
	*保存新增/修改商品盘点设置
	**/
	@RequestMapping(value = "/base/BaseComcheckCfg/saveAddEditBaseComcheckCfg", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveAddEditBaseComcheckCfg(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			BaseComcheckCfg edit = (BaseComcheckCfg) getEntityBean(request,BaseComcheckCfg.class);
			basecomcheckcfgService.saveAddEditBaseComcheckCfg(edit);
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
	*删除商品盘点设置
	**/
	@RequestMapping(value = "/base/BaseComcheckCfg/delBaseComcheckCfg", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String delBaseComcheckCfg(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String delRows=request.getParameter("delRows");
			List de=JsonUtils.json2list(delRows, BaseComcheckCfg.class);
			basecomcheckcfgService.delBaseComcheckCfgById(de);
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
	*根据主键查询商品盘点设置
	**/
	@RequestMapping(value = "/base/BaseComcheckCfg/getBaseComcheckCfgById", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseComcheckCfgById(
			HttpServletRequest request, 
			HttpServletResponse response){
		String id = request.getParameter("id");
		BaseComcheckCfg bean = basecomcheckcfgService.getBaseComcheckCfgById(id);
		return JsonUtils.obj2json(bean);
	}
}