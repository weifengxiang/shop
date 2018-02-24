package org.sky.sys.utils.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.sky.check.service.BaseCheckPlanService;
import org.sky.sys.utils.BspUtils;
/**
 * 创建每天盘查计划（每周一执行一次）
 * @author weifx
 *
 */
public class QuartzCreateComCheckJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		BaseCheckPlanService basecheckplanservice = BspUtils.getBean(BaseCheckPlanService.class);
		basecheckplanservice.createComCheckPlanJOb();
	}

}
