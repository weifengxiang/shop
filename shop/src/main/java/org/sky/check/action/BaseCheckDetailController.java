package org.sky.check.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sky.sys.action.BaseController;
import org.sky.sys.exception.ServiceException;
import org.sky.check.model.BaseCheckDetail;
import org.sky.check.model.BaseCheckDetailExample;
import org.sky.check.model.BaseCheckDetailExample.Criteria;
import org.sky.check.service.BaseCheckDetailService;
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
public class BaseCheckDetailController extends BaseController{
	@Autowired
	private BaseCheckDetailService basecheckdetailService;
	
	public BaseCheckDetailController() {
		// TODO Auto-generated constructor stub
	}
	/**
	*显示盘点详情列表页面
	**/
	@RequestMapping(value = "/base/BaseCheckDetail/initBaseCheckDetailListPage", method = { RequestMethod.GET })
	public String initBaseCheckDetailListPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/check/listbasecheckdetail";
	}
	/**
	 * 盘点详情分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/base/BaseCheckDetail/getBaseCheckDetailByPage", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseCheckDetailByPage(
			HttpServletRequest request, 
			HttpServletResponse response){
		String filter = request.getParameter("filter");
		Map filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= super.getPage(request);
		BaseCheckDetailExample pote= new BaseCheckDetailExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		if(!StringUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		pote.setPage(p);
		PageListData pageData = basecheckdetailService.getBaseCheckDetailByPage(pote);
		return JsonUtils.obj2json(pageData);
	}
	/**
	*显示盘点详情新增页面
	**/
	@RequestMapping(value = "/base/BaseCheckDetail/initAddBaseCheckDetailPage", method = { RequestMethod.GET })
	public String initAddBaseCheckDetailPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/check/editbasecheckdetail";
	}
	/**
	*显示盘点详情修改页面
	**/
	@RequestMapping(value = "/base/BaseCheckDetail/initEditBaseCheckDetailPage", method = { RequestMethod.GET })
	public String initEditBaseCheckDetailPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/check/editbasecheckdetail";
	}
	/**
	*显示盘点详情详细页面
	**/
	@RequestMapping(value = "/base/BaseCheckDetail/initDetailBaseCheckDetailPage", method = { RequestMethod.GET })
	public String initDetailBaseCheckDetailPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/check/detailbasecheckdetail";
	}
	/**
	*保存新增/修改盘点详情
	**/
	@RequestMapping(value = "/base/BaseCheckDetail/saveAddEditBaseCheckDetail", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveAddEditBaseCheckDetail(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			BaseCheckDetail edit = (BaseCheckDetail) getEntityBean(request,BaseCheckDetail.class);
			basecheckdetailService.saveAddEditBaseCheckDetail(edit);
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
	*删除盘点详情
	**/
	@RequestMapping(value = "/base/BaseCheckDetail/delBaseCheckDetail", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String delBaseCheckDetail(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String delRows=request.getParameter("delRows");
			List de=JsonUtils.json2list(delRows, BaseCheckDetail.class);
			basecheckdetailService.delBaseCheckDetailById(de);
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
	*根据主键查询盘点详情
	**/
	@RequestMapping(value = "/base/BaseCheckDetail/getBaseCheckDetailById", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getBaseCheckDetailById(
			HttpServletRequest request, 
			HttpServletResponse response){
		String id = request.getParameter("id");
		BaseCheckDetail bean = basecheckdetailService.getBaseCheckDetailById(id);
		return JsonUtils.obj2json(bean);
	}
}