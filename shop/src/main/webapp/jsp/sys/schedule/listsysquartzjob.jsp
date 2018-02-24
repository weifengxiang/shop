<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/sys/schedule/listsysquartzjob.js'></script>
<script type="text/javascript">
var SYS_JOBSTATUS=<%=EnumUtils.getEnums("SYS.JOBSTATUS") %>
$(function() {
	init();
});
</script>
</head>
<body class="easyui-layout" style="width: 100%; height: 100%; padding: 0; border: 0">
<div data-options="region:'north',split:false,collapsible:false,iconCls:'icon-search'" class="easyui-panel" title="查询条件"
	   style="width: 100%; height: 63px; padding: 0px;" cellpadding="0">
<table class='noborder'>
	<tr>
		<td  style="text-align: right;"><label>任务ID:</label></td>
		<td><input  class="easyui-textbox" data-options="prompt:'输入任务ID'"name="q_id"  id="q_id" ></input></td>				
		<td  style="text-align: right;"><label>任务名称:</label></td>
		<td><input  class="easyui-textbox" data-options="prompt:'输入任务名称'"name="q_jobName"  id="q_jobName" ></input></td>				
		<td  style="text-align: right;"><label>任务组:</label></td>
		<td><input  class="easyui-textbox" data-options="prompt:'输入任务组'"name="q_jobGroupName"  id="q_jobGroupName" ></input></td>				
		<td  style="text-align: right;"><label>任务执行类:</label></td>
		<td><input  class="easyui-textbox" data-options="prompt:'输入任务执行类'"name="q_jobClass"  id="q_jobClass" ></input></td>										
		<td><a href="javascript:searchButton()"class="easyui-linkbutton" plain="true" iconCls="icon-search">查询</a></td>
	</tr>
</table>
</div>
<div data-options=" region:'center'," title="定时任务管理">
<table  id="listsysquartzjobdg" class="easyui-datagrid" style="width: 100%; height: 100%"
	data-options="
			region:'center',
			iconCls: 'icon-edit',
			remoteSort : false,
			toolbar: '#tb',
			pagination:true,
			rownumbers: true,
			checkbox:true,
			singleSelect:true,
			selectOnCheck:false,
			checkOnSelect:false,
			onDblClickRow:function(rowIndex, rowData){
								//beginEdit('listpubquartzjobdg',rowIndex);
						  },
			onLoadSuccess : function () {
        		$(this).datagrid('fixRownumber');
        		$(this).datagrid('doCellTip',{'max-width':'200px','delay':500});
    		}
		">
	<thead>
		<tr>
			<th data-options="field: 'checked', checkbox:true"></th>
				<th data-options="field:'id',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true,
							validType:['length[0,50]','notChinese']
						}}">任务ID</th>
				<th data-options="field:'jobName',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true,
							validType:['length[0,50]']
						}}">任务名称</th>
				<th data-options="field:'jobGroupName',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true,
							validType:['length[0,100]','notChinese']
						}}">任务组</th>
				<th data-options="field:'jobClass',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true,
							validType:['length[0,500]','notChinese']
						}}">任务执行类</th>
				<th data-options="field:'cronExpression',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true,
							validType:['length[0,50]','notChinese']
						}}">触发时间</th>
				<th data-options="field:'status',width:50,
					formatter:function(value,row){
						return SKY.formatterEnum(value,row,SYS_JOBSTATUS);
					}">状态</th>
				<th data-options="field:'note',width:180,
				editor:{
						type:'textbox',
						options:{
						validType:['length[0,200]'],
						}}">备注</th>
		</tr>
	</thead>
</table>
</div>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true" onclick="addSysQuartzJob()">增加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-20130408025545236_easyicon_net_30',plain:true" onclick="delSysQuartzJob()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit',plain:true" onclick="editSysQuartzJob()">修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-save',plain:true" onclick="saveSysQuartzJob()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-resultset_last',plain:true" onclick="startSysQuartzJob()">启动任务</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-stop',plain:true" onclick="stopSysQuartzJob()">停止任务</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-01',plain:true" onclick="pauseSysQuartzJob()">暂停任务</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-resultset_next',plain:true" onclick="resumeSysQuartzJob()">恢复任务</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-next_green',plain:true" onclick="runSysQuartzJob()">立即执行(一次)</a>
</div>
</body>
</html>