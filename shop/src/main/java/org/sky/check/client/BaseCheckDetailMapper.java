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
     * 根据盘查计划生成盘查明细
     * @param params
     */
    void insertBaseCheckDetailByPlan(@Param("params") Map params);
}