package org.sky.sys.service;
import org.apache.log4j.Logger;
import java.sql.Timestamp;
import java.util.List;
import org.sky.sys.client.SysCommonMapper;
import org.sky.sys.client.SysNoticeMapper;
import org.sky.sys.exception.ServiceException;
import org.sky.sys.model.SysNotice;
import org.sky.sys.model.SysNoticeExample;
import org.sky.sys.utils.PageListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.CommonUtils;
import org.sky.sys.utils.Page;
import org.sky.sys.utils.StringUtils;
@Service
public class SysNoticeService {
	private final Logger logger=Logger.getLogger(SysNoticeService.class);
	@Autowired
	private SysNoticeMapper sysnoticemapper;
	@Autowired
	private SysCommonMapper syscommonmapper;
	/**
	*分页查询
	**/
	public PageListData getSysNoticeByPage(SysNoticeExample ep){
		long totalCount = sysnoticemapper.countByExample(ep);
		List list = sysnoticemapper.selectByExample(ep);
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	/**
	*保存列表新增及修改
	**/
	@Transactional
	public void saveSysNotice(List<SysNotice> addlist,
			List<SysNotice> updatelist) throws ServiceException{
		try{
			if(null!=addlist&&addlist.size()>0){
				for(SysNotice add:addlist){
					add.setId(CommonUtils.getUUID(32));
					sysnoticemapper.insertSelective(add);
				}
			}
			if(null!=updatelist&&updatelist.size()>0){
				for(SysNotice update:updatelist){
					sysnoticemapper.updateByPrimaryKeySelective(update);
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
	public void saveAddSysNotice(SysNotice add) throws ServiceException{
		try{
			add.setId(CommonUtils.getUUID(32));
			sysnoticemapper.insertSelective(add);
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
	public void saveAddEditSysNotice(SysNotice edit) throws ServiceException{
		try{
			Timestamp ts = syscommonmapper.queryTimestamp();
			if(StringUtils.isNull(edit.getId())){
				//新增
				edit.setId(CommonUtils.getUUID(32));
				edit.setCreater(BspUtils.getLoginUser().getCode());
				edit.setCreateTime(ts);
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				sysnoticemapper.insertSelective(edit);
			}else{
				//修改
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				sysnoticemapper.updateByPrimaryKeySelective(edit);
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
	public void delSysNoticeById(List<SysNotice> delList){
		for(SysNotice del:delList){
			sysnoticemapper.deleteByPrimaryKey(del.getId());
		}
	}
	/**
	*根据主键查询对象
	**/
	public SysNotice getSysNoticeById(String id){
		SysNotice bean = sysnoticemapper.selectByPrimaryKey(id);
		return bean;
	}
	/**
	 * 查询最新发布通知
	 * @return
	 */
	public SysNotice getLasterSysNotice(){
		SysNoticeExample sne = new SysNoticeExample();
		sne.createCriteria().andStateEqualTo("1");
		sne.setOrderByClause(" PUBTIME desc ");
		Page page = new Page();
		page.setBegin(0);
		page.setRows(1);
		sne.setPage(page);
		List<SysNotice> list = sysnoticemapper.selectByExample(sne);
		if(list.size()>0) {
			return list.get(0);
		}else {
			return new SysNotice();
		}
	}
}