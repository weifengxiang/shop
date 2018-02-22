package org.sky.sys.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sky.sys.action.BaseController;
import org.sky.sys.exception.ServiceException;
import org.sky.sys.model.SysDict;
import org.sky.sys.model.SysQuartzJob;
import org.sky.sys.model.SysQuartzJobExample;
import org.sky.sys.model.SysQuartzJobExample.Criteria;
import org.sky.sys.service.SysQuartzJobService;
import org.sky.sys.utils.JsonUtils;
import org.sky.sys.utils.Page;
import org.sky.sys.utils.PageListData;
import org.sky.sys.utils.ResultData;
import org.sky.sys.utils.StringUtils;
import org.sky.sys.utils.schedule.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 * 
* @Title: SysQuartzJobController.java 
* @Package org.sky.sys.action 
* @Description: TODO(定时任务Controller) 
* @author 位峰祥   
* @date 2016-6-2 下午10:31:32 
* @version V1.0
 */
@Controller
public class SysQuartzJobController extends BaseController{
	@Autowired
	private SysQuartzJobService sysquartzjobservice;
	
	public SysQuartzJobController() {
		// TODO Auto-generated constructor stub
	}
	/**
	 *  "显示定时任务页面"
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/initSysQuartzJob", method = { RequestMethod.GET })
	public String initSysQuartzJob(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {
		return "jsp/sys/schedule/listsysquartzjob";
	}
	/**
	 * 定时任务分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/getSysQuartzJobByPage", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getSysQuartzJobByPage(
			HttpServletRequest request, 
			HttpServletResponse response){
		String filter = request.getParameter("filter");
		Map filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= super.getPage(request);
		SysQuartzJobExample pote= new SysQuartzJobExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		if(!StringUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		pote.setPage(p);
		PageListData pageData = sysquartzjobservice.getSysQuartzJobByPage(pote);
		return JsonUtils.obj2json(pageData);
	}
	
	/**
	 * "保存新增定时任务"
	 * @param add
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/saveAddSysQuartzJob", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveAddSysQuartzJob(@ModelAttribute SysQuartzJob add,
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			sysquartzjobservice.saveAddSysQuartzJob(add);
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
	 * "保存定时任务"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/saveSysQuartzJob", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String saveSysQuartzJob(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String insertRows=request.getParameter("insertRows");
			String updateRows=request.getParameter("updateRows");
			List is=JsonUtils.json2list(insertRows, SysQuartzJob.class);
			List up=JsonUtils.json2list(updateRows, SysQuartzJob.class);
			sysquartzjobservice.saveSysQuartzJob(is,up);
			rd.setCode(ResultData.code_success);
			rd.setName("保存成功");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd.setCode(ResultData.code_error);
			rd.setName("保存失败<br>"+e.getMessage());
		}
		return JsonUtils.obj2json(rd);
	}
	/**
	 * "删除定时任务"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/delSysQuartzJob", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String delSysQuartzJob(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String delRows=request.getParameter("delRows");
			List de=JsonUtils.json2list(delRows,SysQuartzJob.class);
			sysquartzjobservice.delSysQuartzJobById(de);
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
	 * "根据主键查询定时任务"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/getSysQuartzJobById", method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getSysQuartzJobById(
			HttpServletRequest request, 
			HttpServletResponse response){
		String id = request.getParameter("id");
		SysQuartzJob bean = sysquartzjobservice.getSysQuartzJobById(id);
		return JsonUtils.obj2json(bean);
	}
	/**
	 * "启动定时任务"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/runSysQuartzJob", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String runSysQuartzJob(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String jobStr=request.getParameter("job");
			List<SysQuartzJob> list=JsonUtils.json2list(jobStr, SysQuartzJob.class);
			//先移除　再启动
			for(SysQuartzJob job:list){
				QuartzManager.runJob(job);
			}
			rd.setCode(ResultData.code_success);
			rd.setName("启动成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd.setCode(ResultData.code_error);
			rd.setName("启动失败<br>"+e.getMessage());
		}
		return JsonUtils.obj2json(rd);
	}
	/**
	 * "停止定时任务"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/stopSysQuartzJob", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String stopSysQuartzJob(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String jobStr=request.getParameter("job");
			List<SysQuartzJob> list=JsonUtils.json2list(jobStr, SysQuartzJob.class);
			//先移除　再启动
			for(SysQuartzJob job:list){
				QuartzManager.delJob(job);
			}
			rd.setCode(ResultData.code_success);
			rd.setName("停止成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd.setCode(ResultData.code_error);
			rd.setName("停止失败<br>"+e.getMessage());
		}
		return JsonUtils.obj2json(rd);
	}
	/**
	 * "暂停定时任务"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/pauseSysQuartzJob", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String pauseSysQuartzJob(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String jobStr=request.getParameter("job");
			List<SysQuartzJob> list=JsonUtils.json2list(jobStr, SysQuartzJob.class);
			for(SysQuartzJob job:list){
				QuartzManager.pauseJob(job);
			}
			rd.setCode(ResultData.code_success);
			rd.setName("暂停成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd.setCode(ResultData.code_error);
			rd.setName("暂停失败<br>"+e.getMessage());
		}
		return JsonUtils.obj2json(rd);
	}
	/**
	 * "恢复定时任务"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/resumeSysQuartzJob", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String resumeSysQuartzJob(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String jobStr=request.getParameter("job");
			List<SysQuartzJob> list=JsonUtils.json2list(jobStr, SysQuartzJob.class);
			for(SysQuartzJob job:list){
				QuartzManager.resumeJob(job);
			}
			rd.setCode(ResultData.code_success);
			rd.setName("恢复成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd.setCode(ResultData.code_error);
			rd.setName("恢复失败<br>"+e.getMessage());
		}
		return JsonUtils.obj2json(rd);
	}
	/**
	 * "立即执行定时任务"
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/SysQuartzJob/triggerSysQuartzJob", method =RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String triggerSysQuartzJob(
			HttpServletRequest request, 
			HttpServletResponse response){
		ResultData rd= new ResultData();
		try {
			String jobStr=request.getParameter("job");
			List<SysQuartzJob> list=JsonUtils.json2list(jobStr, SysQuartzJob.class);
			for(SysQuartzJob job:list){
				QuartzManager.triggerJob(job);
			}
			rd.setCode(ResultData.code_success);
			rd.setName("执行成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rd.setCode(ResultData.code_error);
			rd.setName("执行失败<br>"+e.getMessage());
		}
		return JsonUtils.obj2json(rd);
	}
}