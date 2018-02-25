package org.sky.sys.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sky.sys.action.BaseController;
import org.sky.sys.exception.ServiceException;
import org.sky.sys.model.SysNotice;
import org.sky.sys.model.SysNoticeExample;
import org.sky.sys.model.SysNoticeExample.Criteria;
import org.sky.sys.service.SysNoticeService;
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
public class SysNoticeController extends BaseController{
	@Autowired
	private SysNoticeService sysnoticeService;
	
	public SysNoticeController() {
		// TODO Auto-generated constructor stub
	}
	/**
	*显示通知列表页面
	**/
	@RequestMapping(value = "/base/SysNotice/initSysNoticeListPage", method = { RequestMethod.GET })
	public String initSysNoticeListPage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/sys/notice/listsysnotice";
	}
	/**
	 * 通知分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/base/SysNotice/getSysNoticeByPage", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getSysNoticeByPage(
			HttpServletRequest request, 
			HttpServletResponse response){
		String filter = request.getParameter("filter");
		Map filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= super.getPage(request);
		SysNoticeExample pote= new SysNoticeExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		if(!StringUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		pote.setPage(p);
		PageListData pageData = sysnoticeService.getSysNoticeByPage(pote);
		return JsonUtils.obj2json(pageData);
	}
	/**
	*显示通知新增页面
	**/
	@RequestMapping(value = "/base/SysNotice/initAddSysNoticePage", method = { RequestMethod.GET })
	public String initAddSysNoticePage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/sys/notice/editsysnotice";
	}
	/**
	*显示通知修改页面
	**/
	@RequestMapping(value = "/base/SysNotice/initEditSysNoticePage", method = { RequestMethod.GET })
	public String initEditSysNoticePage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/sys/notice/editsysnotice";
	}
	/**
	*显示通知详细页面
	**/
	@RequestMapping(value = "/base/SysNotice/initDetailSysNoticePage", method = { RequestMethod.GET })
	public String initDetailSysNoticePage(
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/sys/notice/detailsysnotice";
	}
	/**
	*保存新增/修改通知
	**/
	@RequestMapping(value = "/base/SysNotice/saveAddEditSysNotice", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveAddEditSysNotice(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			SysNotice edit = (SysNotice) getEntityBean(request,SysNotice.class);
			sysnoticeService.saveAddEditSysNotice(edit);
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
	*删除通知
	**/
	@RequestMapping(value = "/base/SysNotice/delSysNotice", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String delSysNotice(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String delRows=request.getParameter("delRows");
			List de=JsonUtils.json2list(delRows, SysNotice.class);
			sysnoticeService.delSysNoticeById(de);
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
	*根据主键查询通知
	**/
	@RequestMapping(value = "/base/SysNotice/getSysNoticeById", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getSysNoticeById(
			HttpServletRequest request, 
			HttpServletResponse response){
		String id = request.getParameter("id");
		SysNotice bean = sysnoticeService.getSysNoticeById(id);
		return JsonUtils.obj2json(bean);
	}
	/**
	*查询最新的消息通知
	**/
	@RequestMapping(value = "/base/SysNotice/getLasterSysNotice", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getLasterSysNotice(
			HttpServletRequest request, 
			HttpServletResponse response){
		SysNotice bean = sysnoticeService.getLasterSysNotice();
		return JsonUtils.obj2json(bean);
	}
}