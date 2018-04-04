<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/report/empchecktab.js'></script>
<script type="text/javascript">
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
		<th><label>门店:</label></th>
		<td><input id="organTree" ></input></td>				
		<th><label>检查批次:</label></th>
		<td><input id='checkPlan' class="easyui-combobox"></input></td>				
	</tr>
</table>
</div>
<div data-options=" region:'center',iconCls: 'icon-table'" title="员工检查情况表">
<table  id="listbasecheckplandg" class="easyui-datagrid" style="width: 100%; height: 100%"
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
								//SKY_EASYUI.beginEdit('listbasecheckplandg',rowIndex);
						  },
			onLoadSuccess : function () {
        		$(this).datagrid('fixRownumber');
        		$(this).datagrid('doCellTip',{'max-width':'200px','delay':500});
    		}
		">
	<thead>
		<tr>
			<th data-options="field: 'checked', checkbox:true"></th>
				<th data-options="field:'code',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">编号</th>
				<th data-options="field:'name',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">名称</th>
				<th data-options="field:'shopName',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">检查门店</th>
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
		data-options="iconCls:'icon-excel',plain:true" onclick="detailBaseCheckPlan()">导出Excel</a>
</div>
</body>
</html>