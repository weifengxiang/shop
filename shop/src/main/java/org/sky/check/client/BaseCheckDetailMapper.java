package org.sky.check.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.sky.check.model.BaseCheckDetail;
import org.sky.check.model.BaseCheckDetailExample;

public interface BaseCheckDetailMapper {
    long countByExample(BaseCheckDetailExample example);

    int deleteByExample(BaseCheckDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(BaseCheckDetail record);

    int insertSelective(BaseCheckDetail record);

    List<BaseCheckDetail> selectByExample(BaseCheckDetailExample example);

    BaseCheckDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BaseCheckDetail record, @Param("example") BaseCheckDetailExample example);

    int updateByExample(@Param("record") BaseCheckDetail record, @Param("example") BaseCheckDetailExample example);

    int updateByPrimaryKeySelective(BaseCheckDetail record);

    int updateByPrimaryKey(BaseCheckDetail record);
    /**
     * 
     * @param params
     */
    void insertBaseCheckDetailByPlan(@Param("params") Map params);
    /**
     * 盘查计划下的商品门类列表
     * @param params
     * @return
     */
    List<Map> selectBaseCheckPlanComCateList(@Param("params") Map params);
}