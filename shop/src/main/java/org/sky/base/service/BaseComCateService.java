package org.sky.base.service;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.sky.sys.client.SysCommonMapper;
import org.sky.base.client.BaseComCateMapper;
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
@Service
public class BaseComCateService {
	private final Logger logger=Logger.getLogger(BaseComCateService.class);
	@Autowired
	private BaseComCateMapper basecomcatemapper;
	@Autowired
	private SysCommonMapper syscommonmapper;
	/**
	*分页查询
	**/
	public PageListData getBaseComCateByPage(BaseComCateExample ep){
		long totalCount = basecomcatemapper.countByExample(ep);
		List list = basecomcatemapper.selectByExample(ep);
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	/**
	*保存列表新增及修改
	**/
	@Transactional
	public void saveBaseComCate(List<BaseComCate> addlist,
			List<BaseComCate> updatelist) throws ServiceException{
		try{
			if(null!=addlist&&addlist.size()>0){
				for(BaseComCate add:addlist){
					basecomcatemapper.insertSelective(add);
				}
			}
			if(null!=updatelist&&updatelist.size()>0){
				for(BaseComCate update:updatelist){
					basecomcatemapper.updateByPrimaryKeySelective(update);
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
	public void saveAddBaseComCate(BaseComCate add) throws ServiceException{
		try{
			basecomcatemapper.insertSelective(add);
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
	public void saveAddEditBaseComCate(BaseComCate edit) throws ServiceException{
		try{
			Timestamp ts = syscommonmapper.queryTimestamp();
			if(StringUtils.isNull(edit.getId())){
				//新增
				edit.setId(CommonUtils.getUUID(32));
				edit.setCreater(BspUtils.getLoginUser().getCode());
				edit.setCreateTime(ts);
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				basecomcatemapper.insertSelective(edit);
			}else{
				//修改
				edit.setUpdater(BspUtils.getLoginUser().getCode());
				edit.setUpdateTime(ts);
				basecomcatemapper.updateByPrimaryKeySelective(edit);
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
	public void delBaseComCateById(BaseComCate del){
		basecomcatemapper.deleteByPrimaryKey(del.getId());
	}
	/**
	*根据主键查询对象
	**/
	public BaseComCate getBaseComCateById(String id){
		BaseComCate bean = basecomcatemapper.selectByPrimaryKey(id);
		return bean;
	}
	/**
	 * 商品类别导入
	 * @param filepath
	 * @param full
	 * @return
	 * @throws ServiceException
	 */
	public int impExcelComCate(String filepath,boolean full) throws ServiceException{
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
				BaseComCateExample bce = new BaseComCateExample();
				basecomcatemapper.deleteByExample(bce);
			}
			Date date = CommonUtils.getCurrentDbDate();
			for(Object obj:results){
				LinkedList<String> data = (LinkedList)obj;
				BaseComCate bcc = new BaseComCate();
				bcc.setId(CommonUtils.getUUID(32));
				bcc.setCode(data.get(1));
				//bcc.setRate(new BigDecimal(data.get(2)));
				bcc.setName(data.get(3));
				if(2==bcc.getCode().length()) {
					bcc.setParCode("root");
				}else {
					bcc.setParCode(bcc.getCode().substring(0, bcc.getCode().length()-2));
				}
				bcc.setCreater(BspUtils.getLoginUser().getCode());
				bcc.setCreateTime(date);
				bcc.setUpdater(BspUtils.getLoginUser().getCode());
				bcc.setUpdateTime(date);
				String seq="";
				if("root".equals(bcc.getParCode())) {
					seq=bcc.getCode();
				}else {
					seq = bcc.getCode().substring(bcc.getCode().length()-2);
				}
				if(!StringUtils.isNumeric(seq)) {
					seq="99";
				}
				bcc.setSeq(Integer.parseInt(seq));
				basecomcatemapper.insertSelective(bcc);
			}
			return results.size();
		}
		return 0;
	}
}