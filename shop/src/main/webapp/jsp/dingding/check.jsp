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
	var state = '${params.state}';
	var cateCode = '${params.cateCode}';
	</script>
</head>
<body>
	<div class="easyui-navpanel">
		${checkPlan.name}
		<input type='radio' name='state' value='-1'>全部</input>
		<input type='radio' name='state' value='0'>未盘点</input>
		<input type='radio' name='state' value='1'>已盘点</input>
		<div style='height: 100%;padding-top:10px; padding-left:10px'>
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
		window.location.href=basepath+"dd/DDController/main";
	});
	$("input[name='state'][value='"+state+"']").attr('checked',true)
	initComCateTree();
	$("input[name='state']").on('click',function(){
		cateCode='';
		initComCateTree();
	});
});
/**
 * 盘点计划树
 */
function initComCateTree() {
	var params=new HashMap();
	var state=$("input[name='state']:checked").val();
	params.put('planCode',planCode);
	if(state!='-1'){
		params.put('state',state);
	}
	var url = basepath + 'dd/DDController/getBaseCheckPlanCateTree';
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
								window.location.href=basepath+"dd/DDController/initBaseCheckDetailListPage/"+planCode+"/"+data.cate_code+"/"+state;
							}
						},
						onLoadSuccess:function(node, data){
							if(cateCode){
								var node = $('#comcatetree').tree('find',cateCode);
								if(null==node){
									node = $('#comcatetree').tree('find',cateCode.substring(0,cateCode.length-2));
								}
								if(null==node){
									node = $('#comcatetree').tree('find',cateCode.substring(0,cateCode.length-4));
								}
								$('#comcatetree').tree('expandTo', node.target);
							}
						}
					});
		}
	});
}
</script>
</html>