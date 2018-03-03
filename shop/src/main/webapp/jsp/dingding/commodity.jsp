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
	var state = '${state}';
	var CHECK_STATE = <%=EnumUtils.getEnums("CHECK.STATE") %>;
	var CHECK_RESULT = <%=EnumUtils.getEnums("CHECK.RESULT") %>;
	</script>
</head>
<body>
	<div style='width:100%;height:10%'>
		<p><a href="#" class="easyui-linkbutton c1" id='backBtn' style="width:100px;padding-right: 10px">返回</a></p>
	</div>
	<div style='width:100%;height:90%'>
	<table id="listbasecheckdetaildg" class="easyui-datagrid" style='width:100%;height:90%'
		data-options="
				singleSelect:true,border:false,fit:true,fitColumns:true,
				scrollbarSize:0,
				pagination:true,
				nowrap:false
			">
		<thead>
			<tr>
					<th data-options="field:'comName',width:90,
					editor:{
							type:'textbox',
							options:{
								required:true
							}}">商品名称</th>
					<th data-options="field:'result',width:30,
					editor:{
							type:'textbox',
							options:{
								required:true
							}},
					formatter:function(value,row){
							  	 return SKY.formatterEnum(value,row,CHECK_RESULT);
							 }">结果</th>
					<th data-options="field:'state',width:30,
					editor:{
							type:'textbox',
							options:{
								required:true
							}},
					formatter:function(value,row){
							  	 return SKY.formatterEnum(value,row,CHECK_STATE);
							 }">状态</th>
					<th data-options="field:'id',align:'center',width:70,
					editor:{
							type:'textbox',
							options:{
								required:true
							}},
					formatter:function(value,row){
							  	 return foramtButton(row);
							 }">操作</th>
			</tr>
		</thead>
	</table>
	</div>
</body>	
<script type="text/javascript">
$(function(){
	searchPlanDetail();
	$('#backBtn').on('click',function(){
		var params = new HashMap();
		params.put('state',state);
		params.put('cateCode',cateCode);
		window.location.href=basepath+"dd/DDController/check?params="+params.getJSON();
	});
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
			if('-1'!=state){
				ft.put("state@=", state);
			}
			if(cateCode){
				ft.put("exists(select 1 from base_commodity bc where bc.cate_code='"+cateCode+"' and bc.code=com_code) and 1@=",1);
			}
			return ft.getJSON();
		}
	});
}
function foramtButton(row){
	return "<nobr><input type='button' value='有货' onclick=\"check('"+row.id+"','1')\"></input>"+
		   "&nbsp&nbsp&nbsp&nbsp"+
		   "<input type='button' value='缺货' onclick=\"check('"+row.id+"','0')\"></input></nobr>";
}
/**
 * 0:缺货,1:有货
 */
function check(id,result){
	var url=basepath+'/dd/DDController/'+id+'/'+result;
	$.post(SKY.urlCSRF(url), function(data){
		   if(data.code=='1'){
			   $('#listbasecheckdetaildg').datagrid('reload');
		   }else{
			   $.messager.alert('提示',data.name,'error')
		   }
		 });
}
</script>
</html>