<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/base/comcheckcfg/editbasecomcheckcfg.js'></script>
<script type="text/javascript">
var _callbacks = $.Callbacks();
$(function() {
	
});
</script> 
</head>
<body>
<div class="easyui-panel"  style="width:100%;height:100%;text-align: center;"
	 data-options="footer:'#editPageButtonsFt'">
		<form id="addeditbasecomcheckcfgform" class="easyui-form" method="post" 
			data-options="novalidate:true">
			<input type='hidden' name='id' id='id'/>
			<input type='hidden' name='comCate' id='comCate'/>
			<input type='hidden' name='empCode' id='empCode'/>
			<table style="width:100%">
				  <tr>
					<th><label>盘点类别:</label></th>
					<td><input class="easyui-textbox" name="comCateName" id="comCateName"
						data-options="required:true,readonly:true"></input></td>
				  </tr>
				  <tr>
					<th><label>员工:</label></th>
					<td><input class="easyui-textbox" name="empName" id="empName"
						data-options="required:true"></input>
						<a href="javascript:openUserHelp()"class="easyui-linkbutton" plain="true" iconCls="icon-search">选择</a>
					</td>
				  </tr>
				  <tr>
					<th><label>状态:</label></th>
					<td>
						<input type='radio' name="status" value='1' checked>有效</input>
						<input type='radio' name="status" value='0'>无效</input>
					</td>
				  </tr>
				  <tr>
					<th><label>备注:</label></th>
					<td><input class="easyui-textbox" name="note"
						style="width:400px;height: 100px"
						data-options="multiline:true"></input></td>
				  </tr>
			</table>
		</form>
</div>
<div id='editPageButtonsFt' style="text-align:center; padding:0px; top:0px">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save'
		onclick="submitAddEditBaseComcheckCfgForm()">保存</a> 
	<a href="javascript:void(0)" id='cloBtn' class="easyui-linkbutton" iconCls='icon-cancel'>关闭</a>
</div>
</body>
</html>