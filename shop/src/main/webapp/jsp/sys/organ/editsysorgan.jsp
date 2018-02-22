<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/sys/organ/editsysorgan.js'></script>
<script type="text/javascript">
var _callbacks = $.Callbacks();
var D_PRICE=<%=EnumUtils.getEnums("ORGAN.D_PRICE") %>;
var ATTR=<%=EnumUtils.getEnums("ORGAN.ATTR") %>;
$(function() {
	init()
});
</script> 
</head>
<body>
<div class="easyui-panel"  style="width:100%;height:100%;text-align: center;"
	 data-options="footer:'#editPageButtonsFt'">
		<form id="addeditsysorganform" class="easyui-form" method="post" 
			data-options="novalidate:true">
			<input type='hidden' name='id' id='id'/>
			<input type='hidden' name='parCode' id='parCode'/>
			<table style="width:100%">
				  <tr>
					<th><label>机构编号:</label></th>
					<td><input class="easyui-textbox" name="code" id="code"
						data-options="required:true"></input></td>

					<th><label>机构名称:</label></th>
					<td><input class="easyui-textbox" name="name"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>机构类型:</label></th>
					<td><input class="easyui-combobox" id='type' name="type"
						data-options="  required:true,  
										editable:false,
								        valueField: 'code',    
								        textField: 'name'   
									"></input></td>

					<th><label>序号:</label></th>
					<td><input class="easyui-numberbox" name="seq"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>配送价格:</label></th>
					<td><input class="easyui-combobox" id='dPrice' name="dPrice"
						data-options="data:D_PRICE,
									  valueField: 'code',    
								      textField: 'name'"></input></td>

					<th><label>属性:</label></th>
					<td><input class="easyui-combobox" name="attr"
						data-options="data:ATTR,
									  valueField: 'code',    
								      textField: 'name'"></input></td>
				  </tr>
				  <tr>
					<th><label>门店面积:</label></th>
					<td><input class="easyui-numberbox" id='area' name='area'
						data-options=""></input></td>

					<th><label>电话:</label></th>
					<td><input class="easyui-textbox" name="phone"
						data-options=""></input></td>
				  </tr>
				  <tr>
					<th><label>地址:</label></th>
					<td colspan='3'><input class="easyui-textbox" name="address"  style="width:400px;height: 100px"
						data-options="multiline:true"></input></td>
				  </tr>
				  <tr>
					<th><label>备注:</label></th>
					<td colspan='3'><input class="easyui-textbox" name="note"  style="width:400px;height: 100px"
						data-options="multiline:true"></input></td>
				  </tr>
			</table>
		</form>
</div>
<div id='editPageButtonsFt' style="text-align:center; padding:0px; top:0px">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save'
		onclick="submitAddEditSysOrganForm()">保存</a> 
	<a href="javascript:void(0)" id='cloBtn' class="easyui-linkbutton" iconCls='icon-cancel'>关闭</a>
</div>
</body>
</html>