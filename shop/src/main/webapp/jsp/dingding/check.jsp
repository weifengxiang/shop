<%@page import="org.sky.sys.utils.EnumUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/dingding/include.jsp"%> 
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>商品盘查系统</title>
	<security:csrfMetaTags />
	<script type="text/javascript">

	var planCode='${checkPlan.code}';
	var CHECK_STATE = <%=EnumUtils.getEnums("CHECK.STATE") %>
	</script>
</head>
<body>
	<div class="easyui-navpanel">
		${checkPlan.name}
		<div style='height: 100%;'>
			<ul class="easyui-tree" id="comcatetree" data-options='animate:true'></ul>
	    </div>
		<footer>
            <div class="m-buttongroup m-buttongroup-justified" style="width:100%">
                <a id='notice' class="easyui-linkbutton" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top',plain:true">消息通知</a>
                <a id='chechPlan' class="easyui-linkbutton" data-options="iconCls:'icon-large-clipart',size:'large',iconAlign:'top',plain:true">盘查计划</a>
            </div>
		</footer>
	</div>
</body>	
<script type="text/javascript">
$(function(){
	$('#notice').on('click',function(){
		window.location.href=basepath+"dd/DDController/initPage/main";
	});
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
								window.location.href=basepath+"dd/DDController/initBaseCheckDetailListPage/"+planCode+"/"+data.cate_code;
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