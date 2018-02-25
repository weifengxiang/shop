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
	<script type="text/javascript" src="${basepath}skin/js/md5.js"></script>
	<security:csrfMetaTags />
</head>
<body>
	<div class="easyui-navpanel" style="position:relative;padding:20px">
		<header>
			<div class="m-toolbar">
				<div class="m-title">用户登录</div>
				<div class="m-right">
					<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" outline="true" onclick="$('#ddloginform').form('reset')" style="width:60px">重置</a>
				</div>
			</div>
		</header>
		<form id="ddloginform" action="${basepath}j_spring_security_check" method="post">
		<security:csrfInput/>
		<input type='hidden' name='client' value='dd'/>
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" label="用户名:" name="username" id="j_username" prompt="请输入用户名" style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-passwordbox" label="密码:" name="password" id="j_password" prompt="请输入密码" style="width:100%">
			</div>
			<div style="margin-bottom:10px">
				<font color=red>${SPRING_SECURITY_LAST_EXCEPTION.message}</font>
			</div>
		</form>
		<div style="padding:20px">
			<p><a href="#" class="easyui-linkbutton c1" style="width:100%" id="login_submit"  onclick='login()'>登录</a></p>
		</div>
	</div>
</body>
<script type="text/javascript">
function login() {
	var j_username = $("#j_username").textbox('getValue');
	if(!j_username){
		$.messager.alert('提示','请输入登录名','error');
		return ;
	} 
	var j_password = $("#j_password").textbox('getValue');
	if(!j_password){
		$.messager.alert('提示','请输入密码','error');
		return ;
	} 
	$("#j_password").textbox('setValue',toMD5Str($("#j_password").textbox('getValue')));
	$("#ddloginform").submit();
}
</script>
</html>