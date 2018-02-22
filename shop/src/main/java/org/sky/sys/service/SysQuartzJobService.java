package org.sky.sys.service;

import java.sql.Timestamp;
import java.util.List;

import org.sky.sys.client.SysCommonMapper;
import org.sky.sys.client.SysQuartzJobMapper;
import org.sky.sys.exception.ServiceException;
import org.sky.sys.model.SysQuartzJob;
import org.sky.sys.model.SysQuartzJobExample;
import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.PageListData;
import org.sky.sys.utils.schedule.QuartzManager;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
* @Title: SysQuartzJobService.java 
* @Package org.sky.sys.service 
* @Description: TODO(定时任务Service) 
* @author 位峰祥   
* @date 2016-6-2 下午10:46:09 
* @version V1.0
 */
@Service
public class SysQuartzJobService {
	private final Logger logger=Logger.getLogger(SysQuartzJobService.class);
	@Autowired
	private SysCommonMapper commonMapper;
	@Autowired
	private SysQuartzJobMapper sysquartzjobmapper;
	/**
	*分页查询
	**/
	public PageListData getSysQuartzJobByPage(SysQuartzJobExample ep){
		long totalCount = sysquartzjobmapper.countByExample(ep);
		List<SysQuartzJob> list = sysquartzjobmapper.selectByExample(ep);
		for(SysQuartzJob job:list){
			job.setStatus(QuartzManager.getTriggerState(job));
		}
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	/**
	*保存列表新增及修改
	**/
	@Transactional
	public void saveSysQuartzJob(List<SysQuartzJob> addlist,
			List<SysQuartzJob> updatelist) throws ServiceException{
		Timestamp ts = commonMapper.queryTimestamp();
		try{
			if(null!=addlist&&addlist.size()>0){
				for(SysQuartzJob add:addlist){
					boolean isvaild = CronExpression.isValidExpression(add.getCronExpression());
					if(!isvaild){
						logger.error("调度时间格式不正确");
						throw new ServiceException("调度时间格式不正确");
					}
					Class.forName(add.getJobClass());
					add.setCreater(BspUtils.getLoginUser().getCode());
					add.setUpdater(BspUtils.getLoginUser().getCode());
					add.setCreateTime(ts);
					add.setUpdateTime(ts);
					sysquartzjobmapper.insertSelective(add);
				}
			}
			if(null!=updatelist&&updatelist.size()>0){
				for(SysQuartzJob update:updatelist){
					boolean isvaild = CronExpression.isValidExpression(update.getCronExpression());
					if(!isvaild){
						logger.error("调度时间格式不正确");
						throw new ServiceException("调度时间格式不正确");
					}
					String status = QuartzManager.getTriggerState(update);
					Class.forName(update.getJobClass());
					if(!"NONE".equals(status)){
						logger.error("请先将任务停止后在进行修改");
						throw new ServiceException("请先将任务停止后在进行修改");
					}
					update.setUpdater(BspUtils.getLoginUser().getCode());
					update.setUpdateTime(ts);
					sysquartzjobmapper.updateByPrimaryKeySelective(update);
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			if(e.getMessage().contains("的值太大")){
				throw new ServiceException("输入的字段值过长！");
			}
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	*保存添加单个对象
	**/
	@Transactional
	public void saveAddSysQuartzJob(SysQuartzJob add) throws ServiceException{
		try{
			sysquartzjobmapper.insertSelective(add);
		}catch(Exception e){
			logger.error(e.getMessage());
			if(e.getMessage().contains("违反唯一约束条件")){
				throw new ServiceException("违反唯一约束条件");
			}else{
				throw new ServiceException(e.getMessage());
			}
		}
	}
	/**
	*保存编辑单个对象
	**/
	@Transactional
	public void saveEditSysQuartzJob(SysQuartzJob edit) throws ServiceException{
		try{
			sysquartzjobmapper.updateByPrimaryKeySelective(edit);
		}catch(Exception e){
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	*根据主键批量删除对象
	**/
	@Transactional
	public void delSysQuartzJobById(List<SysQuartzJob> delList){
		for(SysQuartzJob del:delList){
			String status = QuartzManager.getTriggerState(del);
			if(!"NONE".equals(status)){
				logger.error("请先将任务停止后在进行删除");
				throw new ServiceException("请先将任务停止后在进行删除");
			}
			sysquartzjobmapper.deleteByPrimaryKey(del.getId());
		}
	}
	/**
	*根据主键查询对象
	**/
	public SysQuartzJob getSysQuartzJobById(String id){
		SysQuartzJob bean = sysquartzjobmapper.selectByPrimaryKey(id);
		return bean;
	}
}