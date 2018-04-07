<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/report/oostab.js'></script>
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
<div data-options=" region:'center',iconCls: 'icon-table'" title="断货统计表">
<table  id="listempCheckDetaildg" class="easyui-datagrid" style="width: 100%; height: 100%"
	data-options="
			region:'center',
			fit:true,
			border:false,
			remoteSort : false,
			toolbar: '#tb',
			pagination:false,
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
				<th data-options="field:'CATENAME',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">小类名称</th>
				<th data-options="field:'NAME',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">商品名称</th>
				<th data-options="field:'BAR_CODE',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">条码</th>
				<th data-options="field:'SPEC',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">规格</th>
				<th data-options="field:'RESULT',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">检查结果</th>
		</tr>
	</thead>
</table>
</div>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-excel',plain:true" onclick="expExcel()">导出Excel</a>
</div>
</body>
</html>