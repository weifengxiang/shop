<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/base/commodity/detailbasecommodity.js'></script>
<script type="text/javascript">
var _callbacks = $.Callbacks();
$(function() {
	
});
</script> 
</head>
<body>
<div class="easyui-panel"  style="width:100%;height:100%;text-align: center;"
	 data-options="footer:'#detailPageButtonsFt'">
		<form id="detailbasecommodityform" class="easyui-form" method="post" 
			data-options="novalidate:true">
			<input type='hidden' name='id' id='id'/>
			<table style="width:100%">
				  <tr>
					<th><label>缂栧彿:</label></th>
					<td><input class="easyui-textbox" name="code"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>鍚嶇О:</label></th>
					<td><input class="easyui-textbox" name="name"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>瑙勬牸:</label></th>
					<td><input class="easyui-textbox" name="spec"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>鏉＄爜:</label></th>
					<td><input class="easyui-textbox" name="barCode"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>绫诲埆:</label></th>
					<td><input class="easyui-textbox" name="cateCode"
						data-options="required:true"></input></td>
				  </tr>
				  <tr>
					<th><label>澶囨敞:</label></th>
					<td><input class="easyui-textbox" name="note"
						data-options="required:true"></input></td>
				  </tr>
			</table>
		</form>
</div>
<div id='detailPageButtonsFt' style="text-align:center; padding:2px; top:0px">
	<a href="javascript:void(0)" id='cloBtn' class="easyui-linkbutton" iconCls='icon-cancel'">关闭</a>
</div>
</body>
</html>