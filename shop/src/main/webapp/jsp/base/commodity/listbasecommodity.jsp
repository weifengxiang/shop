<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/base/commodity/listbasecommodity.js'></script>
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
		<th><label>缂栧彿:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入缂栧彿'" name="q_code"  id="q_code" ></input></td>				
		<th><label>鍚嶇О:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入鍚嶇О'" name="q_name"  id="q_name" ></input></td>				
		<th><label>瑙勬牸:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入瑙勬牸'" name="q_spec"  id="q_spec" ></input></td>				
		<th><label>鏉＄爜:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入鏉＄爜'" name="q_barCode"  id="q_barCode" ></input></td>				
		<th><label>绫诲埆:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入绫诲埆'" name="q_cateCode"  id="q_cateCode" ></input></td>				
		<th><label>澶囨敞:</label></th>
		<td><input  class="easyui-textbox" data-options="prompt:'输入澶囨敞'" name="q_note"  id="q_note" ></input></td>				
		<td><a href="javascript:searchButton()"class="easyui-linkbutton" plain="true" iconCls="icon-search">查询</a></td>
	</tr>
</table>
</div>
<div data-options=" region:'center',iconCls: 'icon-table'" title="鍟嗗搧绠＄悊管理">
<table  id="listbasecommoditydg" class="easyui-datagrid" style="width: 100%; height: 100%"
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
								//SKY_EASYUI.beginEdit('listbasecommoditydg',rowIndex);
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
						}}">缂栧彿</th>
				<th data-options="field:'name',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">鍚嶇О</th>
				<th data-options="field:'spec',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">瑙勬牸</th>
				<th data-options="field:'barCode',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">鏉＄爜</th>
				<th data-options="field:'cateCode',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">绫诲埆</th>
				<th data-options="field:'note',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">澶囨敞</th>
		</tr>
	</thead>
</table>
</div>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true" onclick="addBaseCommodity()">增加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit',plain:true" onclick="editBaseCommodity()">修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-20130408025545236_easyicon_net_30',plain:true" onclick="delBaseCommodity()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-06',plain:true" onclick="detailBaseCommodity()">查看明细</a>
</div>
</body>
</html>