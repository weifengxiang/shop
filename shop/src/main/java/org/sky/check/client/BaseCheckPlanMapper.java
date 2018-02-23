package org.sky.check.client;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sky.check.model.BaseCheckPlan;
import org.sky.check.model.BaseCheckPlanExample;

public interface BaseCheckPlanMapper {
    long countByExample(BaseCheckPlanExample example);

    int deleteByExample(BaseCheckPlanExample example);

    int deleteByPrimaryKey(String id);

    int insert(BaseCheckPlan record);

    int insertSelective(BaseCheckPlan record);

    List<BaseCheckPlan> selectByExample(BaseCheckPlanExample example);

    BaseCheckPlan selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BaseCheckPlan record, @Param("example") BaseCheckPlanExample example);

    int updateByExample(@Param("record") BaseCheckPlan record, @Param("example") BaseCheckPlanExample example);

    int updateByPrimaryKeySelective(BaseCheckPlan record);

    int updateByPrimaryKey(BaseCheckPlan record);
}