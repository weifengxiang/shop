<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/check/detailbasecheckplan.js'></script>
<script type="text/javascript">
var CHECK_STATE = <%=EnumUtils.getEnums("CHECK.STATE") %>;
var CHECK_RESULT = <%=EnumUtils.getEnums("CHECK.RESULT") %>;
var _callbacks = $.Callbacks();
$(function() {
	
});
</script> 
</head>
<body>
<div class="easyui-layout" style="width: 100%; height: 100%; padding: 0; border: 0"
	  data-options='border:false,fit:true'>
<div data-options=" region:'north',iconCls: 'icon-table'" class="easyui-panel" title=""
	 style="width:100%; height:43px; padding:0px;" cellpadding="0">
	 <form class="easyui-form" method="post"  data-options="novalidate:true">
		<table style="width:100%" class='noborder'>
			  <tr>
				<th><label>计划编号:</label></th>
				<td><input class="easyui-textbox" name="planCode" id='planCode'
					data-options="required:true"></input></td>
			  
				<th><label>计划名称:</label></th>
				<td><input class="easyui-textbox" name="planName"  id="planName"
					data-options="required:true"></input></td>
			  </tr>
		</table>
	</form>
</div>
<div data-options="region:'west',split:true,title:'商品类别'" style="width:300px;padding:0px;">
		<ul class="easyui-tree" id="comcatetree" data-options='animate:true'></ul>
</div>
<div data-options=" region:'center',iconCls: 'icon-table'" title="盘点详情管理">
<table  id="listbasecheckdetaildg" class="easyui-datagrid" style="width: 100%; height: 100%"
	data-options="
			region:'center',
			fit:true,
			border:false,
			remoteSort : false,
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
				<th data-options="field:'comName',width:180,
				editor:{
						type:'textbox',
						options:{
							required:true
						}}">商品名称</th>
				<th data-options="field:'result',width:100,
				editor:{
						type:'textbox',
						options:{
							required:true
						}},
				formatter:function(value,row){
						  	 return SKY.formatterEnum(value,row,CHECK_RESULT);
						 }">盘点结果</th>
				<th data-options="field:'state',width:100,
				editor:{
						type:'textbox',
						options:{
							required:true
						}},
				formatter:function(value,row){
						  	 return SKY.formatterEnum(value,row,CHECK_STATE);
						 }">盘点状态</th>
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
</div>
<div id='detailPageButtonsFt' style="text-align:center; padding:2px; top:0px">
	<a href="javascript:void(0)" id='cloBtn' class="easyui-linkbutton" iconCls='icon-cancel'">关闭</a>
</div>
</body>
</html>