package org.sky.base.service;
import org.apache.log4j.Logger;
import java.sql.Timestamp;
import java.util.List;
import org.sky.sys.client.SysCommonMapper;
import org.sky.base.client.BaseComcheckCfgMapper;
import org.sky.sys.exception.ServiceException;
import org.sky.base.model.BaseComcheckCfg;
import org.sky.base.model.BaseComcheckCfgExample;
import org.sky.sys.utils.PageListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.CommonUtils;
import org.sky.sys.utils.StringUtils;
@Service
public class BaseComcheckCfgService {
	private final Logger logger=Logger.getLogger(BaseComcheckCfgService.class);
	@Autowired
	private BaseComcheckCfgMapper basecomcheckcfgmapper;
	@Autowired
	private SysCommonMapper syscommonmapper;
	/**
	*分页查询
	**/
	public PageListData getBaseComcheckCfgByPage(BaseComcheckCfgExample ep){
		long totalCount = basecomcheckcfgmapper.countByExample(ep);
		List list = basecomcheckcfgmapper.selectByExample(ep);
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	/**
	*保存列表新增及修改
	**/
	@Transactional
	public void saveBaseComcheckCfg(List<BaseComcheckCfg> addlist,
			List<BaseComcheckCfg> updatelist) throws ServiceException{
		try{
			if(null!=addlist&&addlist.size()>0){
				for(BaseComcheckCfg add:addlist){
					basecomcheckcfgmapper.insertSelective(add);
				}
			}
			if(null!=updatelist&&updatelist.size()>0){
				for(BaseComcheckCfg update:updatelist){
					basecomcheckcfgmapper.updateByPrimaryKeySelective(update);
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
	public void saveAddBaseComcheckCfg(BaseComcheckCfg add) throws ServiceException{
		try{
			basecomcheckcfgmapper.insertSelective(add);
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
	public void saveAddEditBaseComcheckCfg(BaseComcheckCfg edit) throws ServiceException{
		try{
			Timestamp ts = syscommonmapper.queryTimestamp();
			if(StringUtils.isNull(edit.getId())){
				//新增
				edit.setId(CommonUtils.getUUID(32));
				edit.setCreater(BspUtils.getLoginUser().getCode());
				edit.setCreateTime(ts);
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				basecomcheckcfgmapper.insertSelective(edit);
			}else{
				//修改
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				basecomcheckcfgmapper.updateByPrimaryKeySelective(edit);
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
	public void delBaseComcheckCfgById(List<BaseComcheckCfg> delList){
		for(BaseComcheckCfg del:delList){
			basecomcheckcfgmapper.deleteByPrimaryKey(del.getId());
		}
	}
	/**
	*根据主键查询对象
	**/
	public BaseComcheckCfg getBaseComcheckCfgById(String id){
		BaseComcheckCfg bean = basecomcheckcfgmapper.selectByPrimaryKey(id);
		return bean;
	}
}