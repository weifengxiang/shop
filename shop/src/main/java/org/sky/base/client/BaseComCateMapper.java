package org.sky.base.client;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sky.base.model.BaseComCate;
import org.sky.base.model.BaseComCateExample;

public interface BaseComCateMapper {
    long countByExample(BaseComCateExample example);

    int deleteByExample(BaseComCateExample example);

    int deleteByPrimaryKey(String id);

    int insert(BaseComCate record);

    int insertSelective(BaseComCate record);

    List<BaseComCate> selectByExample(BaseComCateExample example);

    BaseComCate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BaseComCate record, @Param("example") BaseComCateExample example);

    int updateByExample(@Param("record") BaseComCate record, @Param("example") BaseComCateExample example);

    int updateByPrimaryKeySelective(BaseComCate record);

    int updateByPrimaryKey(BaseComCate record);
}