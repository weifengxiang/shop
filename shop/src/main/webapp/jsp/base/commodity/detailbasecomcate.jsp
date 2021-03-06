<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/base/commodity/detailbasecomcate.js'></script>
<script type="text/javascript">
var _callbacks = $.Callbacks();
$(function() {
	
});
</script> 
</head>
<body>
<div class="easyui-panel"  style="width:100%;height:100%;text-align: center;"
	 data-options="footer:'#detailPageButtonsFt'">
		<form id="detailbasecomcateform" class="easyui-form" method="post" 
			data-options="novalidate:true">
			<input type='hidden' name='id' id='id'/>
			<input type='hidden' name='parCode' id='parCode'/>
			<table style="width:100%">
				  <tr>
					<th><label>编号:</label></th>
					<td><input class="easyui-textbox" name="code"
						data-options="required:true"></input></td>
				  
					<th><label>名称:</label></th>
					<td><input class="easyui-textbox" name="name"
						data-options="required:true"></input></td>
				  </tr>
				 <tr>
				  	<th><label>是否小类:</label></th>
					<td>
						<input type='radio' name="isLeaf" value='0' checked>否</input>
						<input type='radio' name="isLeaf" value='1'>是</input>
					</td>
					<th><label>毛利率/加价率:</label></th>
					<td><input class="easyui-numberbox" name="rate"
						data-options="required:true,precision:2"></input></td>
				  </tr>
				  <tr colspan='3'>
					<th><label>序号:</label></th>
					<td><input class="easyui-numberbox" name="seq"
						data-options="required:true"></input></td>
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
<div id='detailPageButtonsFt' style="text-align:center; padding:2px; top:0px">
	<a href="javascript:void(0)" id='cloBtn' class="easyui-linkbutton" iconCls='icon-cancel'">关闭</a>
</div>
</body>
</html>