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
	var CHECK_STATE = <%=EnumUtils.getEnums("CHECK.STATE") %>
	</script>
</head>
<body>
	<div class="easyui-navpanel">
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
		<footer>
            <div class="m-buttongroup m-buttongroup-justified" style="width:100%">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top',plain:true">消息通知</a>
                <a id='chechPlan' class="easyui-linkbutton" data-options="iconCls:'icon-large-clipart',size:'large',iconAlign:'top',plain:true">盘查计划</a>
            </div>
		</footer>
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
</body>	
<script type="text/javascript">
var planCode='001_2018-02-24';
$(function(){
	var url=basepath+"base/SysNotice/getLasterSysNotice";
	$('#detailsysnoticeform').form('load',SKY.urlCSRF(url));
	$('#chechPlan').on('click',function(){
		var params = new HashMap();
		params.put('state','0');
		window.location.href=basepath+"dd/DDController/check?params="+params.getJSON();
	});
});
</script>
</html>