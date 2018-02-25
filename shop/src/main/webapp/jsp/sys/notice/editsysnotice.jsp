<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/sys/notice/editsysnotice.js'></script>
<script type="text/javascript">
var _callbacks = $.Callbacks();
$(function() {
	
});
</script> 
</head>
<body>
<div class="easyui-panel"  style="width:100%;height:100%;text-align: center;"
	 data-options="footer:'#editPageButtonsFt'">
		<form id="addeditsysnoticeform" class="easyui-form" method="post" 
			data-options="novalidate:true">
			<input type='hidden' name='id' id='id'/>
			<table style="width:100%">
				  <tr>
					<th><label>标题:</label></th>
					<td><input class="easyui-textbox" name="title"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>内容:</label></th>
					<td><input class="easyui-textbox" name="content"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>发布人:</label></th>
					<td><input class="easyui-textbox" name="publisher"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>发布日期:</label></th>
					<td><input class="easyui-textbox" name="pubtime"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>状态:</label></th>
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
		onclick="submitAddEditSysNoticeForm()">保存</a> 
	<a href="javascript:void(0)" id='cloBtn' class="easyui-linkbutton" iconCls='icon-cancel'>关闭</a>
</div>
</body>
</html>