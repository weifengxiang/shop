<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/dingding/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>商品盘查系统</title>
	<security:csrfMetaTags />
	<script type="text/javascript">
	var CHECK_STATE = <%=EnumUtils.getEnums("CHECK.STATE") %>
	</script>
</head>
<body>
	<div class="easyui-navpanel">
		<div class="easyui-tabs" data-options="tabHeight:60,fit:true,tabPosition:'bottom',border:false,pill:true,narrow:true,justified:true">
			<div style="padding:10px">
				<div class="panel-header tt-inner">
					<img src='${bathpath}skin/images/scanner.png'/><br>消息通知
				</div>
				<form id="detailsysnoticeform">
					<security:csrfInput/>
					<input type='hidden' name='client' value='dd'/>
						<div style="margin-bottom:10px">
							<input class='easyui-textbox' label="标题:" name="title" style="width:100%" readonly></input>
						</div>
						<div style="margin-bottom:10px">
							<input class='easyui-textbox' label="内容:" name="content" 
								   style="width:100%;height:200px" readonly data-options="multiline:true"></input>
						</div>
				 </form>
			</div>
			<div style="padding:10px">
				<div class="panel-header tt-inner">
					<img src='${bathpath}skin/images/tablet.png'/><br>盘查计划
				</div>
				<div style='height: 100%;'>
					<ul class="easyui-tree" id="comcatetree" data-options='animate:true'></ul>
			    </div>
			</div>
		</div>
	</div>
	<style>
       .tt-inner{
            display:inline-block;
            line-height:12px;
            padding-top:5px;
        }
		p{
			line-height:150%;
		}
	</style>
	<div id="p3" class="easyui-navpanel" style='width:100%;height:100%' >
		<header>
			<div class="m-toolbar">
				<div class="m-left">
					<a href="#" class="easyui-linkbutton m-back" data-options="plain:true,outline:true,back:true">Back</a>
				</div>
				<div class="m-title">商品列表</div>
			</div>
		</header>
		<div style="padding:20px 10px">
			<table  id="listbasecheckdetaildg" class="easyui-datagrid" 
				data-options="
						singleSelect:true,fitColumns:true,scrollbarSize:0,
						fit:true,
						border:false,
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
var planCode='001_2018-02-24';
$(function(){
	var url=basepath+"base/SysNotice/getLasterSysNotice";
	$('#detailsysnoticeform').form('load',SKY.urlCSRF(url));
	initComCateTree();
});
function initComCateTree() {
	var params=new HashMap();
	params.put('planCode',planCode);
	var url = basepath + 'base/BaseCheckPlan/getBaseCheckPlanCateTree';
	url=SKY.urlCSRF(url);
	$.ajax({
		url:url,
		type: "POST",
		data:{"data":params.getJSON()},
		dataType:'json',
		success:function(data){
			$('#comcatetree').tree(
					{
						data : data,
						lines:true,
						method : 'POST',
						onClick : function(node) {
							var data=node.data;
							if(data){
								$.mobile.go('#p3')
								searchPlanDetail();
							}
						}
					});
		}
	});
}
/**
 * 盘查明细查询
 */
function searchPlanDetail(){
	var selectNode = $('#comcatetree').tree("getData",
	  		 $('#comcatetree').tree("getSelected").target
	  		);
	$('#listbasecheckdetaildg').datagrid('options').url=SKY.urlCSRF(basepath+'base/BaseCheckDetail/getBaseCheckDetailByPage')
	$('#listbasecheckdetaildg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			if(planCode){
				ft.put("planCode@=", planCode);
			}
			var cate_code=selectNode.data.cate_code;
			if(cate_code){
				ft.put("exists(select 1 from base_commodity bc where bc.cate_code='"+cate_code+"' and bc.code=com_code) and 1@=",1);
			}
			return ft.getJSON();
		}
	});
}
</script>
</html>