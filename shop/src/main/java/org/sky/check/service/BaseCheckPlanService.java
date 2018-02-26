package org.sky.check.service;
import org.apache.log4j.Logger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sky.sys.client.SysCommonMapper;
import org.sky.base.client.BaseComCateMapper;
import org.sky.base.model.BaseComCate;
import org.sky.base.model.BaseComCateExample;
import org.sky.check.client.BaseCheckDetailMapper;
import org.sky.check.client.BaseCheckPlanMapper;
import org.sky.sys.exception.ServiceException;
import org.sky.check.model.BaseCheckDetailExample;
import org.sky.check.model.BaseCheckPlan;
import org.sky.check.model.BaseCheckPlanExample;
import org.sky.sys.utils.PageListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSON;

import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.CommonUtils;
import org.sky.sys.utils.JsonUtils;
import org.sky.sys.utils.StringUtils;
import org.sky.sys.utils.TreeStru;
@Service
public class BaseCheckPlanService {
	private final Logger logger=Logger.getLogger(BaseCheckPlanService.class);
	@Autowired
	private BaseCheckPlanMapper basecheckplanmapper;
	@Autowired
	private BaseCheckDetailMapper basecheckdetailmapper;
	@Autowired
	private BaseComCateMapper basecomcatemapper;
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
	*根据主键批量删除盘查计划及盘查明细
	**/
	@Transactional
	public void delBaseCheckPlanById(List<BaseCheckPlan> delList){
		for(BaseCheckPlan del:delList){
			basecheckplanmapper.deleteByPrimaryKey(del.getId());
			BaseCheckDetailExample bcde = new BaseCheckDetailExample();
			bcde.createCriteria().andPlanCodeEqualTo(del.getCode());
			basecheckdetailmapper.deleteByExample(bcde);
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
	@Transactional
	public void createComCheckPlanJOb() {
		Date date = syscommonmapper.querySysDate();
		BaseCheckPlan plan = new BaseCheckPlan();
		
		plan.setName(CommonUtils.formatDate(date, "yyyy-MM-dd")+"盘查计划");
		plan.setState("1");
		plan.setCreater("sys_job");
		plan.setCreateTime(date);
		plan.setUpdater("sys_job");
		plan.setUpdateTime(date);
		//插入文东店
		plan.setId(CommonUtils.getUUID(32));
		plan.setCode("001_"+CommonUtils.formatDate(date, "yyyy-MM-dd"));
		plan.setShopCode("001");
		basecheckplanmapper.insert(plan);
		//插入省图店
		plan.setId(CommonUtils.getUUID(32));
		plan.setCode("002_"+CommonUtils.formatDate(date, "yyyy-MM-dd"));
		plan.setShopCode("002");
		basecheckplanmapper.insert(plan);
		//插入盘点明细
		Map params = new HashMap();
		params.put("date", CommonUtils.formatDate(date, "yyyy-MM-dd"));
		basecheckdetailmapper.insertBaseCheckDetailByPlan(params);
	}
	public List<TreeStru> getBaseCheckPlanCateTree(Map m){
		String planCode = (String)m.get("planCode");
		Map params = new HashMap();
		params.put("planCode",planCode);
		List<Map> list = basecheckdetailmapper.selectBaseCheckPlanComCateList(params);
		if(list.isEmpty()) {
			throw new ServiceException("该盘查计划下没有明细");
		}
		List<String> parCateCodeList = new ArrayList();
		for(Map map:list) {
			String cateCode = (String)map.get("cate_code");
			//添加商品门类的父级
			for(int i=0;i<cateCode.length();i=i+2) {
				String parCode = cateCode.substring(0,i+2);
				if(!parCateCodeList.contains(parCode)) {
					parCateCodeList.add(parCode);
				}
			}
		}
		BaseComCateExample bcce = new BaseComCateExample();
		bcce.createCriteria().andCodeIn(parCateCodeList);
		bcce.setOrderByClause("code,seq");
		List<BaseComCate> parlist = basecomcatemapper.selectByExample(bcce);
		//合并父级商品门类
		for(BaseComCate bcc:parlist) {
			boolean exists=false;
			for(Map map:list) {
				if(map.get("cate_code").equals(bcc.getCode())) {
					exists=true;
				}
			}
			if(!exists) {
				Map parCateMap = new HashMap();
				parCateMap.put("plan_code", planCode);
				parCateMap.put("cate_code", bcc.getCode());
				parCateMap.put("cate_name", bcc.getName());
				parCateMap.put("seq", bcc.getSeq());
				list.add(parCateMap);
			}
		}
		List<TreeStru> treeList = new ArrayList();
		initPlanComCateTree(treeList,list);
		return treeList;
	}
	/**
	 * 递归初始化树
	 * @param treeList
	 * @param list
	 */
	private void initPlanComCateTree(List<TreeStru> treeList,List<Map> list) {
		if(treeList.isEmpty()) {
			for(Map map:list) {
				if(((String)map.get("cate_code")).length()==2) {
					TreeStru ts = new TreeStru();
					ts.setId((String)map.get("cate_code"));
					ts.setText((String)map.get("cate_name")+"["+(String)map.get("cate_code")+"]");
					ts.setSeq((Integer)map.get("seq"));
					ts.setIconCls("icon-box_world");
					ts.setState("closed");
					ts.setData(map);
					treeList.add(ts); 
				}
			}
		}
		//添加下级
		for(TreeStru ts:treeList) {
			List<TreeStru> childTsList = new ArrayList();
			for(Map map:list) {
				String cateCode = (String)map.get("cate_code");
				if(cateCode.startsWith(ts.getId()) && cateCode.length()==(ts.getId().length()+2)) {
					TreeStru childts = new TreeStru();
					childts.setId((String)map.get("cate_code"));
					childts.setText((String)map.get("cate_name")+"["+(String)map.get("cate_code")+"]");
					childts.setSeq((Integer)map.get("seq"));
					childts.setIconCls("icon-box_world");
					childts.setData(map);
					childTsList.add(childts); 
				}
			}
			if(!childTsList.isEmpty()) {
				ts.setChildren(childTsList);
				ts.setState("closed");
				initPlanComCateTree(childTsList,list);
			}else {
				ts.setState("open");
				return;
			}
		}
	}
	public BaseCheckPlan getLasterBaseCheckPlan(String shopCode) {
		BaseCheckPlanExample bcpe = new BaseCheckPlanExample();
		bcpe.createCriteria().andShopCodeEqualTo(shopCode);
		bcpe.setOrderByClause(" create_time desc");
		List<BaseCheckPlan> list = basecheckplanmapper.selectByExample(bcpe);
		if(!list.isEmpty()) {
			return list.get(0);
		}else {
			return null;
		}
	}
}