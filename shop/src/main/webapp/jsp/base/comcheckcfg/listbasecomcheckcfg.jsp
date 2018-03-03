<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/base/comcheckcfg/listbasecomcheckcfg.js'></script>
<script type="text/javascript">
var SYS_IS=<%=EnumUtils.getEnums("SYS.IS") %>
$(function() {
	init();
});
</script> 
</head>
<body class="easyui-layout" style="width: 100%; height: 100%; padding: 0; border: 0"
	  data-options='border:false,fit:true'>
<div data-options=" region:'west',iconCls: 'icon-table',split:true" title="商品门类" style="width:300px">
	<ul class="easyui-tree" id="comcatetree" data-options='animate:true'></ul>
</div>
<div data-options=" region:'center',iconCls: 'icon-table'" title="商品盘点设置管理">
<table  id="listbasecomcheckcfgdg" class="easyui-datagrid" style="width: 100%; height: 100%"
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
								//SKY_EASYUI.beginEdit('listbasecomcheckcfgdg',rowIndex);
						  },
			onLoadSuccess : function () {
        		$(this).datagrid('fixRownumber');
        		$(this).datagrid('doCellTip',{'max-width':'200px','delay':500});
    		}
		">
	<thead>
		<tr>
			<th data-options="field: 'checked', checkbox:true"></th>
				<th data-options="field:'comCateName',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">盘点类别</th>
				<th data-options="field:'empName',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">员工</th>
				<th data-options="field:'orgName',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">门店</th>
				<th data-options="field:'status',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}},
				formatter:function(value,row){
						  	 return SKY.formatterEnum(value,row,SYS_IS);
						 }">状态(是否有效)</th>
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
		data-options="iconCls:'icon-add',plain:true" onclick="addBaseComcheckCfg()">添加盘查人员</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit',plain:true" onclick="editBaseComcheckCfg()">修改盘查人员</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-20130408025545236_easyicon_net_30',plain:true" onclick="delBaseComcheckCfg()">删除盘查人员</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		data-options="iconCls:'icon-06',plain:true" onclick="detailBaseComcheckCfg()">查看明细</a>
</div>
</body>
</html>