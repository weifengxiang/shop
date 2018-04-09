<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/report/empchecktab.js'></script>
<script type="text/javascript">
var REP_TYPE=<%=EnumUtils.getEnums("REP.TYPE") %>;
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
		<th><label>统计方式:</label></th>
		<td><input id='type' class="easyui-combobox"
				   data-options="
				   		value:'B',
				   		data:REP_TYPE,
				   		valueField:'code',
				   		textField:'name'
				   "></input></td>		
		<td><a href="javascript:doSearch()"class="easyui-linkbutton" plain="true" iconCls="icon-search">查询</a></td>	
	</tr>
</table>
</div>
<div id='empCheckDiv' data-options=" region:'center',iconCls: 'icon-table'" title="员工检查情况表">
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
				<th data-options="field:'NAME',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">商品大类</th>
				<th data-options="field:'TOTAL',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">需检查总数</th>
				<th data-options="field:'FINISH',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">已检查总数</th>
				<th data-options="field:'RATE',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">检查完成率（%）</th>
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