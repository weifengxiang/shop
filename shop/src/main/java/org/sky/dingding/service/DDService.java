package org.sky.dingding.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sky.base.client.BaseComCateMapper;
import org.sky.base.model.BaseComCate;
import org.sky.base.model.BaseComCateExample;
import org.sky.check.client.BaseCheckDetailMapper;
import org.sky.check.model.BaseCheckDetail;
import org.sky.sys.client.SysCommonMapper;
import org.sky.sys.exception.ServiceException;
import org.sky.sys.utils.BspUtils;
import org.sky.sys.utils.CommonUtils;
import org.sky.sys.utils.StringUtils;
import org.sky.sys.utils.TreeStru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 钉钉服务层
 * @author weifx
 *
 */
@Service
public class DDService {
	private Logger logger = Logger.getLogger(DDService.class);
	@Autowired
	private BaseCheckDetailMapper basecheckdetailmapper;
	@Autowired
	private BaseComCateMapper basecomcatemapper;
	@Autowired
	private SysCommonMapper syscommonmapper; 
	/**
	 * 查看当前登录用户下的商品门类树
	 * @param m
	 * @return
	 */
	public List<TreeStru> getBaseCheckPlanCateTree(Map m){
		String planCode = (String)m.get("planCode");
		String state = (String)m.get("state");
		Map params = new HashMap();
		params.put("planCode",planCode);
		if(!StringUtils.isNull(state)) {
			params.put("state",state);
		}
		params.put("checker",BspUtils.getLoginUser().getCode());
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
	 * 钉钉盘点
	 */
	@Transactional
	public void check(String id,String result) throws ServiceException{
		try {
			BaseCheckDetail bcd = new BaseCheckDetail();
			bcd.setId(id);
			bcd.setUpdater(BspUtils.getLoginUser().getCode());
			bcd.setUpdateTime(syscommonmapper.querySysDate());
			bcd.setResult(result);
			bcd.setState("1");
			basecheckdetailmapper.updateByPrimaryKeySelective(bcd);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
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
}
