package org.sky.base.client;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sky.base.model.BaseCommodity;
import org.sky.base.model.BaseCommodityExample;

public interface BaseCommodityMapper {
    long countByExample(BaseCommodityExample example);

    int deleteByExample(BaseCommodityExample example);

    int deleteByPrimaryKey(String id);

    int insert(BaseCommodity record);

    int insertSelective(BaseCommodity record);

    List<BaseCommodity> selectByExample(BaseCommodityExample example);

    BaseCommodity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BaseCommodity record, @Param("example") BaseCommodityExample example);

    int updateByExample(@Param("record") BaseCommodity record, @Param("example") BaseCommodityExample example);

    int updateByPrimaryKeySelective(BaseCommodity record);

    int updateByPrimaryKey(BaseCommodity record);
}