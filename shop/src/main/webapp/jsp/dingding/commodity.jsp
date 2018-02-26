<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/dingding/include.jsp"%> 
<!doctype html>
<html>
<head>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>商品盘查系统</title>
	<security:csrfMetaTags />
	</style>
	<script type="text/javascript">
	var planCode = '${planCode}';
	var cateCode = '${cateCode}';
	var CHECK_STATE = <%=EnumUtils.getEnums("CHECK.STATE") %>
	</script>
</head>
<body>
	<div class="easyui-navpanel" style='width:100%;height:100%' >
		<header>
			<div class="m-toolbar">
				<div class="m-left">
					<a href="#" class="easyui-linkbutton m-back" data-options="plain:true,outline:true,back:true">Back</a>
				</div>
				<div class="m-title">商品列表</div>
			</div>
		</header>
		<div style="padding:20px 10px">
			<table id="listbasecheckdetaildg" class="easyui-datagrid" style='width:100%;height:100%'
				data-options="
						singleSelect:true,border:false,fit:true,fitColumns:true,
						scrollbarSize:0,
						pagination:true
					">
				<thead>
					<tr>
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
									}}">盘点结果</th>
							<th data-options="field:'state',width:100,
							editor:{
									type:'textbox',
									options:{
										required:true
									}},
							formatter:function(value,row){
									  	 return SKY.formatterEnum(value,row,CHECK_STATE);
									 }">盘点状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>	
<script type="text/javascript">
$(function(){
	searchPlanDetail();
});
/**
 * 盘查明细查询
 */
function searchPlanDetail(){
	$('#listbasecheckdetaildg').datagrid('options').url=SKY.urlCSRF(basepath+'base/BaseCheckDetail/getBaseCheckDetailByPage')
	$('#listbasecheckdetaildg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			if(planCode){
				ft.put("planCode@=", planCode);
			}
			if(cateCode){
				ft.put("exists(select 1 from base_commodity bc where bc.cate_code='"+cateCode+"' and bc.code=com_code) and 1@=",1);
			}
			return ft.getJSON();
		}
	});
}
</script>
</html>