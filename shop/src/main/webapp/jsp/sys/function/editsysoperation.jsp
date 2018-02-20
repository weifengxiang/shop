<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/sys/function/editsysoperation.js'></script>
<script type="text/javascript">
var _callbacks = $.Callbacks();
$(function() {
	
});
</script> 
</head>
<body>
<div class="easyui-panel"  style="width:100%;height:100%;text-align: center;"
	 data-options="footer:'#editPageButtonsFt',border:false">
		<form id="editsysoperationform" class="easyui-form" method="post" 
			data-options="novalidate:true">
			<input type='hidden' name='id' id='id'>
			<input type='hidden' name='menuCode' id='menuCode'>
			<table style="width:100%">
				<tr>
					<th><label>操作编号:</label></th>
					<td><input class="easyui-textbox" name="code" id="code"
						data-options="required:true"></input></td>
			
					<th><label>操作名称:</label></th>
					<td><input class="easyui-textbox" name="name"
						data-options="required:true"></input></td>
				</tr>

				<tr>
					<th><label>序号:</label></th>
					<td><input class="easyui-numberbox" name="seq"
						data-options="required:true"></input></td>
					
					<th><label>是否默认:</label></th>
					<td>
						<input type='checkbox' name="isDefault" value='1'></input>
					</td>
				</tr>
		
				<tr>
					<th><label>操作地址:</label></th>
					<td colspan='3'>
						<input class="easyui-textbox" name="url"  style="width:400px"
						data-options="required:true"></input></td>
				
				</tr>
				<tr>
					<th><label>备注:</label></th>
					<td colspan='3'><input class="easyui-textbox" name="note" style="width:400px;height: 100px"
						data-options="multiline:true"></input></td>
				</tr>
			</table>
		</form>
</div>
<div id='editPageButtonsFt' style="text-align:center; padding:0px; top:0px">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save'
		onclick="submitAddEditSysOperationForm()">保存</a> 
	<a href="javascript:void(0)" id='cloBtn' class="easyui-linkbutton" iconCls='icon-cancel'>取消</a>
</div>
</body>
</html>