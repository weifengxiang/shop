<%@page import="org.sky.sys.utils.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/sys/notice/editsysnotice.js'></script>
<script type="text/javascript">
var _callbacks = $.Callbacks();
var userCode = '<%=org.sky.sys.utils.BspUtils.getLoginUser().getCode() %>';
var userName = '<%=org.sky.sys.utils.BspUtils.getLoginUser().getName() %>';
var dateTime = '<%=CommonUtils.getCurrentDate() %>';
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
			<input type='hidden' name='publisher' id='publisher'/>
			<table style="width:100%">
				  <tr>
					<th><label>标题:</label></th>
					<td colspan='3'><input class="easyui-textbox" name="title" 
						style="width:400px"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>内容:</label></th>
					<td colspan='3'><input class="easyui-textbox" name="content"
						style="width:400px;height: 100px"
						data-options="required:true,multiline:true"></input></td>
				  </tr>
				  <tr>
					<th><label>发布人:</label></th>
					<td><input class="easyui-textbox" name="publisherName" id="publisherName" readonly
						data-options="required:true"></input></td>
					<th><label>发布日期:</label></th>
					<td><input class="easyui-datetimebox" name="pubtime" id="pubtime" readonly
						data-options="required:true"></input></td> 
				  </tr>
				  <tr>
					<th><label>状态:</label></th>
					<td colspan='3'>
						<input type='radio' name="state" value='1' checked>发布</input>
						<input type='radio' name="state" value='0'>暂存</input>
					</td>
				  </tr>
				  <tr>
					<th><label>备注:</label></th>
					<td colspan='3'><input class="easyui-textbox" name="note"
						style="width:400px;height: 100px"
						data-options="multiline:true"></input></td>
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