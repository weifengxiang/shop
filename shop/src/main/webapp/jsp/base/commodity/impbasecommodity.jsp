<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/base/commodity/impbasecommodity.js'></script>
<script type="text/javascript">
$(function() {
	
});
</script> 
</head>
<body>
<div class="easyui-panel"  style="width:100%;height:100%;text-align: center;"
	 data-options="border:false,fit:true,footer:'#addPageButtonsFt'">
		<form id="impform" class="easyui-form" method="post" enctype="multipart/form-data"
			data-options="novalidate:true">
			<table style="width:100%">
				<tr>
					<td style="text-align: right;">是否全量更新:</td>
					<td colspan='3'>
						<input type='radio' name='full' value='1' >是</input>
						<input type='radio' name='full' value='0' checked>否</input>
					</td>
				</tr>
				<tr>
					<td colspan='4'>
						<input id='fileinput' name='fileinput' class="easyui-filebox" style="width:90%"></input>
					</td>
				</tr>
			</table>
		</form>
		<div style="padding-top:0px;float: left;text-align: left">
			<span style="color:red">
				<p>说明：</p>
				<ol>
					<li>是否全量更新为‘是’时,导入时会将该小类下的所有商品全部删除，然后导入最新的Excel数据；</li>
					<li>是否全量更新为‘否’时,导入时现有商品库不会进行删除，直接在数据库中增加最新的Excel数据；</li>
				</ol>
			</span>
		</div>
		<div style="padding-top:50px">
			<a class="easyui-linkbutton" style='width:102px' iconCls='icon-excel' href='${basepath}template/BaseCommodity.xls'>商品模板</a>
		</div>
</div>
<div id='addPageButtonsFt' style="text-align: center; padding: 2px;top:0px">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' id='saveImp'>导入</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-cancel' id='reset'>取消</a>
</div>
</body>
</html>