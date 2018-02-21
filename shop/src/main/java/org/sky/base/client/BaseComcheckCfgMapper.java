package org.sky.base.client;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sky.base.model.BaseComcheckCfg;
import org.sky.base.model.BaseComcheckCfgExample;

public interface BaseComcheckCfgMapper {
    long countByExample(BaseComcheckCfgExample example);

    int deleteByExample(BaseComcheckCfgExample example);

    int deleteByPrimaryKey(String id);

    int insert(BaseComcheckCfg record);

    int insertSelective(BaseComcheckCfg record);

    List<BaseComcheckCfg> selectByExample(BaseComcheckCfgExample example);

    BaseComcheckCfg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BaseComcheckCfg record, @Param("example") BaseComcheckCfgExample example);

    int updateByExample(@Param("record") BaseComcheckCfg record, @Param("example") BaseComcheckCfgExample example);

    int updateByPrimaryKeySelective(BaseComcheckCfg record);

    int updateByPrimaryKey(BaseComcheckCfg record);
}