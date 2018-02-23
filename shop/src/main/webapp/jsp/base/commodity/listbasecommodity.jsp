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
<div data-options=" region:'north',iconCls: 'icon-table'" class="easyui-panel" title="查询条件"
	 style="width:100%; height:63px; padding:0px;" cellpadding="0">
	<table class='noborder'>
		<tr style="height: 34px">
			<th><label>编号:</label></th>
			<td><input  class="easyui-textbox" data-options="prompt:'输入编号'" name="q_code"  id="q_code" ></input></td>				
			<th><label>名称:</label></th>
			<td><input  class="easyui-textbox" data-options="prompt:'输入名称'" name="q_name"  id="q_name" ></input></td>				
			<th><label>规格:</label></th>
			<td><input  class="easyui-textbox" data-options="prompt:'输入规格'" name="q_spec"  id="q_spec" ></input></td>				
			<th><label>条码:</label></th>
			<td><input  class="easyui-textbox" data-options="prompt:'输入条码'" name="q_barCode"  id="q_barCode" ></input></td>				
			<td><a href="javascript:searchButton()"class="easyui-linkbutton" plain="true" iconCls="icon-search">查询</a></td>
		</tr>
	</table>
</div>
<div data-options="region:'west',split:true,title:'商品类别管理'" style="width:300px;padding:0px;">
		<ul class="easyui-tree" id="comcatetree" data-options='animate:true'></ul>
</div>
<div data-options=" region:'center',iconCls: 'icon-table'" title="商品管理">
<table  id="listbasecommoditydg" class="easyui-datagrid"  style="width:100%; height:63px; padding:0px;" cellpadding="0"
	data-options="
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
				<th data-options="field:'code',width:120,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">编号</th>
				<th data-options="field:'name',width:150,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">名称</th>
				<th data-options="field:'unit',width:80,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">单位</th>
				<th data-options="field:'spec',width:80,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">规格</th>
				<th data-options="field:'priceMethod',width:80,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">计价方式</th>
				<th data-options="field:'barCode',width:150,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">条码</th>
				<th data-options="field:'cateName',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">类别</th>
				<th data-options="field:'note',width:80,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">备注</th>
		</tr>
	</thead>
</table>
</div>
</div>
<div id="tb" style="height: auto">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-excel',plain:true" onclick="impBaseCommodity()">导入商品Excel</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-add',plain:true" onclick="addBaseCommodity()">增加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit',plain:true" onclick="editBaseCommodity()">修改</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-20130408025545236_easyicon_net_30',plain:true" onclick="delBaseCommodity()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-06',plain:true" onclick="detailBaseCommodity()">查看明细</a>
</div>
<div id="mmTree" class="easyui-menu"">
	<div onclick="impComCate()" data-options="iconCls:'icon-excel'">导入商品类别</div>
	<div class="menu-sep"></div>
	<div onclick="addComCate()" data-options="iconCls:'icon-add'">添加下级商品类别</div>
	<div class="menu-sep"></div>
	<div onclick="editComCate()" data-options="iconCls:'icon-edit'">修改商品类别</div>
	<div class="menu-sep"></div>
	<div onclick="delComCate()" data-options="iconCls:'icon-20130408025545236_easyicon_net_30'">删除商品类别</div>
	<div class="menu-sep"></div>
	<div onclick="detailComCate()" data-options="iconCls:'icon-detail'">查看商品类别</div>
</div>
</body>
</html>