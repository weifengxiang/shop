<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/check/listbasecheckdetail.js'></script>
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
		<th><label>计划编号:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入计划编号'" name="q_planCode"  id="q_planCode" ></input></td>				
		<th><label>商品编号:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入商品编号'" name="q_comCode"  id="q_comCode" ></input></td>				
		<th><label>盘点结果:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入盘点结果'" name="q_result"  id="q_result" ></input></td>				
		<th><label>盘点状态:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入盘点状态'" name="q_state"  id="q_state" ></input></td>				
		<th><label>备注:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入备注'" name="q_note"  id="q_note" ></input></td>				
		<td><a href="javascript:searchButton()"class="easyui-linkbutton" plain="true" iconCls="icon-search">查询</a></td>
	</tr>
</table>
</div>
<div data-options=" region:'center',iconCls: 'icon-table'" title="盘点详情管理">
<table  id="listbasecheckdetaildg" class="easyui-datagrid" style="width: 100%; height: 100%"
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
								//SKY_EASYUI.beginEdit('listbasecheckdetaildg',rowIndex);
						  },
			onLoadSuccess : function () {
        		$(this).datagrid('fixRownumber');
        		$(this).datagrid('doCellTip',{'max-width':'200px','delay':500});
    		}
		">
	<thead>
		<tr>
			<th data-options="field: 'checked', checkbox:true"></th>
				<th data-options="field:'planCode',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">计划编号</th>
				<th data-options="field:'comCode',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">商品编号</th>
				<th data-options="field:'result',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">盘点结果</th>
				<th data-options="field:'state',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">盘点状态</th>
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
		data-options="iconCls:'icon-add',plain:true" onclick="addBaseCheckDetail()">增加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit',plain:true" onclick="editBaseCheckDetail()">修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-20130408025545236_easyicon_net_30',plain:true" onclick="delBaseCheckDetail()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-06',plain:true" onclick="detailBaseCheckDetail()">查看明细</a>
</div>
</body>
</html>