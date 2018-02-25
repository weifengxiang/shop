<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/sys/notice/listsysnotice.js'></script>
<script type="text/javascript">
var NOTICE_STATE=<%=EnumUtils.getEnums("NOTICE.STATE") %>;
$(function() {
	init();
});
</script> 
</head>
<body class="easyui-layout" style="width: 100%; height: 100%; padding: 0; border: 0"
	  data-options='border:false,fit:true'>
<div data-options="region:'north',split:false,collapsible:false,iconCls:'icon-search'" class="easyui-panel" title="查询条件"
	   style="width:100%; height:63px; padding:0px;" cellpadding="0">
<table class='noborder'>
	<tr style="height: 34px">
		<th><label>标题:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入标题'" name="q_title"  id="q_title" ></input></td>				
		<th><label>内容:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入内容'" name="q_content"  id="q_content" ></input></td>				
		<th><label>发布人:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入发布人'" name="q_publisher"  id="q_publisher" ></input></td>				
		<th><label>发布日期:</label></th>
		<td><input  class="easyui-datebox" data-options="prompt:'输入发布日期'" name="q_pubtime"  id="q_pubtime" ></input></td>				
		<th><label>状态:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入状态'" name="q_state"  id="q_state" ></input></td>				
		<td><a href="javascript:searchButton()"class="easyui-linkbutton" plain="true" iconCls="icon-search">查询</a></td>
	</tr>
</table>
</div>
<div data-options=" region:'center',iconCls: 'icon-table'" title="通知管理">
<table  id="listsysnoticedg" class="easyui-datagrid" style="width: 100%; height: 100%"
	data-options="
			region:'center',
			fit:true,
			border:false,
			remoteSort : false,
			toolbar: '#tb',
			pagination:true,
			rownumbers: true,
			checkbox:true,
			singleSelect:true,
			selectOnCheck:false,
			checkOnSelect:false,
			onDblClickRow:function(rowIndex, rowData){
								//SKY_EASYUI.beginEdit('listsysnoticedg',rowIndex);
						  },
			onLoadSuccess : function () {
        		$(this).datagrid('fixRownumber');
        		$(this).datagrid('doCellTip',{'max-width':'200px','delay':500});
    		}
		">
	<thead>
		<tr>
			<th data-options="field: 'checked', checkbox:true"></th>
				<th data-options="field:'title',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">标题</th>
				<th data-options="field:'content',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">内容</th>
				<th data-options="field:'publisher',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">发布人</th>
				<th data-options="field:'pubtime',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">发布日期</th>
				<th data-options="field:'state',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}},
				formatter:function(value,row){
						  	 return SKY.formatterEnum(value,row,NOTICE_STATE);
						 }">状态</th>
				<th data-options="field:'note',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">备注</th>
		</tr>
	</thead>
</table>
</div>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true" onclick="addSysNotice()">增加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit',plain:true" onclick="editSysNotice()">修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-20130408025545236_easyicon_net_30',plain:true" onclick="delSysNotice()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-06',plain:true" onclick="detailSysNotice()">查看明细</a>
</div>
</body>
</html>