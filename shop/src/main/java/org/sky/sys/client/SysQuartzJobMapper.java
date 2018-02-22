package org.sky.sys.client;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sky.sys.model.SysQuartzJob;
import org.sky.sys.model.SysQuartzJobExample;

public interface SysQuartzJobMapper {
    long countByExample(SysQuartzJobExample example);

    int deleteByExample(SysQuartzJobExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysQuartzJob record);

    int insertSelective(SysQuartzJob record);

    List<SysQuartzJob> selectByExample(SysQuartzJobExample example);

    SysQuartzJob selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysQuartzJob record, @Param("example") SysQuartzJobExample example);

    int updateByExample(@Param("record") SysQuartzJob record, @Param("example") SysQuartzJobExample example);

    int updateByPrimaryKeySelective(SysQuartzJob record);

    int updateByPrimaryKey(SysQuartzJob record);
}