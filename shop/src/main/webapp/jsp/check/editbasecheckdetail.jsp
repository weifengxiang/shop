<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/check/editbasecheckdetail.js'></script>
<script type="text/javascript">
var _callbacks = $.Callbacks();
$(function() {
	
});
</script> 
</head>
<body>
<div class="easyui-panel"  style="width:100%;height:100%;text-align: center;"
	 data-options="footer:'#editPageButtonsFt'">
		<form id="addeditbasecheckdetailform" class="easyui-form" method="post" 
			data-options="novalidate:true">
			<input type='hidden' name='id' id='id'/>
			<table style="width:100%">
				  <tr>
					<th><label>计划编号:</label></th>
					<td><input class="easyui-textbox" name="planCode"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>商品编号:</label></th>
					<td><input class="easyui-textbox" name="comCode"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>盘点结果:</label></th>
					<td><input class="easyui-textbox" name="result"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>盘点状态:</label></th>
					<td><input class="easyui-textbox" name="state"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>备注:</label></th>
					<td><input class="easyui-textbox" name="note"
						data-options="required:true"></input></td>
				  </tr>
			</table>
		</form>
</div>
<div id='editPageButtonsFt' style="text-align:center; padding:0px; top:0px">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save'
		onclick="submitAddEditBaseCheckDetailForm()">保存</a> 
	<a href="javascript:void(0)" id='cloBtn' class="easyui-linkbutton" iconCls='icon-cancel'>关闭</a>
</div>
</body>
</html>