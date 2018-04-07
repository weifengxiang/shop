package org.sky.report.client;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.sky.check.model.BaseCheckDetail;
import org.sky.check.model.BaseCheckDetailExample;

public interface ReportMapper {
    /**
     * 员工检查情况表
     * @param params
     * @return
     */
    List<Map> selectEmpCheckDetail(@Param("params") Map params);
}