package org.sky.check.service;
import org.apache.log4j.Logger;
import java.sql.Timestamp;
import java.util.List;
import org.sky.sys.client.SysCommonMapper;
import org.sky.check.client.BaseCheckPlanMapper;
import org.sky.sys.exception.ServiceException;
import org.sky.check.model.BaseCheckPlan;
import org.sky.check.model.BaseCheckPlanExample;
import org.sky.sys.utils.PageListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.CommonUtils;
import org.sky.sys.utils.StringUtils;
@Service
public class BaseCheckPlanService {
	private final Logger logger=Logger.getLogger(BaseCheckPlanService.class);
	@Autowired
	private BaseCheckPlanMapper basecheckplanmapper;
	@Autowired
	private SysCommonMapper syscommonmapper;
	/**
	*分页查询
	**/
	public PageListData getBaseCheckPlanByPage(BaseCheckPlanExample ep){
		long totalCount = basecheckplanmapper.countByExample(ep);
		List list = basecheckplanmapper.selectByExample(ep);
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	/**
	*保存列表新增及修改
	**/
	@Transactional
	public void saveBaseCheckPlan(List<BaseCheckPlan> addlist,
			List<BaseCheckPlan> updatelist) throws ServiceException{
		try{
			if(null!=addlist&&addlist.size()>0){
				for(BaseCheckPlan add:addlist){
					basecheckplanmapper.insertSelective(add);
				}
			}
			if(null!=updatelist&&updatelist.size()>0){
				for(BaseCheckPlan update:updatelist){
					basecheckplanmapper.updateByPrimaryKeySelective(update);
				}
			}
		}catch(Exception e){
			logger.error("保存列表新增及修改执行失败",e);
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
	public void saveAddBaseCheckPlan(BaseCheckPlan add) throws ServiceException{
		try{
			basecheckplanmapper.insertSelective(add);
		}catch(Exception e){
			logger.error("保存添加单个对象执行失败",e);
			if(e.getMessage().contains("违反唯一约束条件")){
				throw new ServiceException("违反唯一约束条件");
			}else{
				throw new ServiceException(e.getMessage());
			}
		}
	}
	/**
	*保存新增/编辑单个对象
	**/
	@Transactional
	public void saveAddEditBaseCheckPlan(BaseCheckPlan edit) throws ServiceException{
		try{
			Timestamp ts = syscommonmapper.queryTimestamp();
			if(StringUtils.isNull(edit.getId())){
				//新增
				edit.setId(CommonUtils.getUUID(32));
				edit.setCreater(BspUtils.getLoginUser().getCode());
				edit.setCreateTime(ts);
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				basecheckplanmapper.insertSelective(edit);
			}else{
				//修改
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				basecheckplanmapper.updateByPrimaryKeySelective(edit);
			}
		}catch(Exception e){
			logger.error("保存新增/编辑单个对象执行失败",e);
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	*根据主键批量删除对象
	**/
	@Transactional
	public void delBaseCheckPlanById(List<BaseCheckPlan> delList){
		for(BaseCheckPlan del:delList){
			basecheckplanmapper.deleteByPrimaryKey(del.getId());
		}
	}
	/**
	*根据主键查询对象
	**/
	public BaseCheckPlan getBaseCheckPlanById(String id){
		BaseCheckPlan bean = basecheckplanmapper.selectByPrimaryKey(id);
		return bean;
	}
	/**
	 * 每周创建盘查计划
	 */
	public void createComCheckPlanJOb() {
		String date =  CommonUtils.formatDate(syscommonmapper.querySysDate());
		
	}
}