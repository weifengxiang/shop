package org.sky.base.service;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
import org.sky.sys.utils.ReadExcel;
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
	/**
	 * 商品信息导入
	 * @param filepath
	 * @param full
	 * @return
	 * @throws ServiceException
	 */
	public int impExcelBaseCommodity(String filepath,boolean full) throws ServiceException{
		List<List<Object>> results=null;
		try {
			results = ReadExcel.readExcel(new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		if (results == null || results.size() < 2) {
			new ServiceException("Excel为空！");
		} else if (results.size() > 30001) {
			new ServiceException("当前记录数：" + results.size() + "，Excel一次最多导入30000条数据！");
		} else {
			List<Object> titles = results.remove(0);
			List<String> ts = new ArrayList<>();
			for (Object title : titles) {
				ts.add((String) title);
			}
			if (org.apache.commons.lang.StringUtils.isBlank(ts.get(ts.size() - 1))) {
				ts.remove(ts.size() - 1);
			}
			//全量更新则删除相同类型 相同单位并且是Excel导入的数据的数据
			if(full){
				BaseCommodityExample bce = new BaseCommodityExample();
				basecommoditymapper.deleteByExample(bce);
			}
			Date date = CommonUtils.getCurrentDbDate();
			for(Object obj:results){
				LinkedList<String> data = (LinkedList)obj;
				BaseCommodity comm = new BaseCommodity();
				comm.setId(CommonUtils.getUUID(32));
				comm.setCode(data.get(1));
				comm.setBarCode(data.get(1));
				comm.setName(data.get(2));
				comm.setUnit(data.get(3));
				comm.setSpec(data.get(4));
				comm.setPriceMethod(data.get(5));
				comm.setCateCode(data.get(6).split("-")[0].trim());
				comm.setCreater(BspUtils.getLoginUser().getCode());
				comm.setCreateTime(date);
				comm.setUpdater(BspUtils.getLoginUser().getCode());
				comm.setUpdateTime(date);
				basecommoditymapper.insertSelective(comm);
			}
			return results.size();
		}
		return 0;
	}
}