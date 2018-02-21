package org.sky.base.service;
import org.apache.log4j.Logger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sky.sys.client.SysCommonMapper;
import org.sky.base.client.BaseComCateMapper;
import org.sky.base.client.BaseCommodityMapper;
import org.sky.sys.exception.ServiceException;
import org.sky.base.model.BaseComCate;
import org.sky.base.model.BaseComCateExample;
import org.sky.base.model.BaseCommodity;
import org.sky.base.model.BaseCommodityExample;
import org.sky.sys.utils.PageListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.CommonUtils;
import org.sky.sys.utils.StringUtils;
import org.sky.sys.utils.TreeStru;
@Service
public class BaseCommodityService {
	private final Logger logger=Logger.getLogger(BaseCommodityService.class);
	@Autowired
	private BaseCommodityMapper basecommoditymapper;
	@Autowired
	private BaseComCateMapper basecomcatemapper;
	@Autowired
	private SysCommonMapper syscommonmapper;
	/**
	*分页查询
	**/
	public PageListData getBaseCommodityByPage(BaseCommodityExample ep){
		long totalCount = basecommoditymapper.countByExample(ep);
		List list = basecommoditymapper.selectByExample(ep);
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	/**
	 * 查询商品类别树
	 * @param m
	 * @return
	 */
	public List<TreeStru> getComCateTreeData(Map m){
		List<TreeStru> tslist = new ArrayList();
		String code = (String)m.get("code");
		BaseComCateExample pcce = new BaseComCateExample();
		pcce.createCriteria().andParCodeEqualTo(code);
		pcce.setOrderByClause("seq asc");
		List<BaseComCate> list=basecomcatemapper.selectByExample(pcce);
		for(BaseComCate bcc:list){
			TreeStru ts = new TreeStru();
			ts.setId(bcc.getCode());
			ts.setText(bcc.getName()+"["+bcc.getCode()+"]");
			ts.setSeq(bcc.getSeq());
			ts.setIconCls("icon-box_world");
			if(bcc.getChildCount()>0){
				ts.setState("closed");
			}else{
				ts.setState("open");
			}
			ts.setData(bcc);
			tslist.add(ts); 
		}
		return tslist;
	}
	/**
	*保存列表新增及修改
	**/
	@Transactional
	public void saveBaseCommodity(List<BaseCommodity> addlist,
			List<BaseCommodity> updatelist) throws ServiceException{
		try{
			if(null!=addlist&&addlist.size()>0){
				for(BaseCommodity add:addlist){
					basecommoditymapper.insertSelective(add);
				}
			}
			if(null!=updatelist&&updatelist.size()>0){
				for(BaseCommodity update:updatelist){
					basecommoditymapper.updateByPrimaryKeySelective(update);
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
	public void saveAddBaseCommodity(BaseCommodity add) throws ServiceException{
		try{
			basecommoditymapper.insertSelective(add);
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
	public void saveAddEditBaseCommodity(BaseCommodity edit) throws ServiceException{
		try{
			Timestamp ts = syscommonmapper.queryTimestamp();
			if(StringUtils.isNull(edit.getId())){
				//新增
				edit.setId(CommonUtils.getUUID(32));
				edit.setCreater(BspUtils.getLoginUser().getCode());
				edit.setCreateTime(ts);
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				basecommoditymapper.insertSelective(edit);
			}else{
				//修改
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				basecommoditymapper.updateByPrimaryKeySelective(edit);
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
	public void delBaseCommodityById(List<BaseCommodity> delList){
		for(BaseCommodity del:delList){
			basecommoditymapper.deleteByPrimaryKey(del.getId());
		}
	}
	/**
	*根据主键查询对象
	**/
	public BaseCommodity getBaseCommodityById(String id){
		BaseCommodity bean = basecommoditymapper.selectByPrimaryKey(id);
		return bean;
	}
}