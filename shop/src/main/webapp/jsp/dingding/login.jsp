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
	<script type="text/javascript" src="${basepath}skin/jquery/jquery.cookie.js"></script>
	<script type="text/javascript" src="${basepath}skin/js/md5.js"></script>
	<security:csrfMetaTags />
	<style type="text/css">
	*{
		font-size:14px !important;
	}
	</style>
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
				<input class="easyui-textbox" data-options="prompt:'请输入用户名',iconCls:'icon-man'"  name="username" id="j_username" style="width:100%;height:40px">
			</div>
			<div style="margin-bottom:10px">
				<input class="easyui-passwordbox" data-options="prompt:'请输入密码'" name="password" id="j_password" style="width:100%;height:40px">
			</div>
			<div style="margin-bottom:10px">
				<input id="rememberme" type="checkbox"/>记住我
			</div>
			<div style="margin-bottom:10px">
				<font color=red>${SPRING_SECURITY_LAST_EXCEPTION.message}</font>
			</div>
		</form>
		<div style="padding:20px">
			<p><a href="#" class="easyui-linkbutton c1" style="width:100%;height:40px" id="login_submit"  onclick='login()'>登录</a></p>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	initRememberMe();
})
function login() {
	//记录cookie
	$.cookie('client','dd',{ path: '/', expires:100});
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
	$("#j_password").passwordbox('setValue',toMD5Str($("#j_password").textbox('getValue')));
	$("#ddloginform").submit();
}
//记住密码操作
function initRememberMe(){
	if($.cookie('absms_crm2_username')!=undefined){  
        $("#rememberme").attr("checked", true);  
    }else{  
        $("#rememberme").attr("checked", false);  
    }  
      
    //读取cookie  
    if($('#rememberme:checked').length>0){  
        $('#j_username').textbox('setValue',$.cookie('absms_crm2_username'));  
        $('#j_password').passwordbox('setValue',$.cookie('absms_crm2_password'));  
        $('#j_password').passwordbox('hidePassword');
    }  
      
    //监听【记住我】事件  
    $("#rememberme").click(function(){  
        if($('#rememberme:checked').length>0){//设置cookie  
            $.cookie('absms_crm2_username', $('#j_username').textbox('getValue'),{ path: '/', expires: 10 });  
            $.cookie('absms_crm2_password', $('#j_password').passwordbox('getValue'),{ path: '/', expires: 10 });  
        }else{//清除cookie  
        	$.removeCookie('absms_crm2_username',{ path: '/', expires: 10 });  
            $.removeCookie('absms_crm2_password',{ path: '/', expires: 10 });   
        }  
    });  
}
</script>
</html>