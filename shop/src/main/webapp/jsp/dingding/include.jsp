<!-- 必须要在 <head> 的第一行、放在第一行 兼容低版本IE-->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> 
<%@ page import="org.sky.sys.utils.BspUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://sky.org.cn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:url value="/" var="basepath"></c:url>
<link rel="stylesheet" type="text/css" href="${basepath}skin/plugins/jquery-easyui-1.5.4/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="${basepath}skin/plugins/jquery-easyui-1.5.4/themes/mobile.css">
<link rel="stylesheet" type="text/css" href="${basepath}skin/plugins/jquery-easyui-1.5.4/themes/color.css">
<link rel="stylesheet" type="text/css" href="${basepath}skin/plugins/jquery-easyui-1.5.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${basepath}skin/css/dd.css">
     
<script type="text/javascript" src="${basepath}skin/plugins/jquery-easyui-1.5.4/jquery.min.js"></script>
<script type="text/javascript" src="${basepath}skin/plugins/jquery-easyui-1.5.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basepath}skin/plugins/jquery-easyui-1.5.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${basepath}skin/plugins/jquery-easyui-1.5.4/jquery.easyui.mobile.js"></script>
<script type="text/javascript" src="${basepath}skin/js/map.js"></script>
<script type="text/javascript" src="${basepath}skin/js/sky.js"></script>

<script type="text/javascript">
var basepath='${basepath}';
$(function(){  
    // 设置jQuery Ajax全局的参数  
    $.ajaxSetup({ 
    	statusCode: {
    		401:function(){
    			$.messager.alert('错误','操作失败,未登录或登录超时','error');
    		  	},
   		  	403:function(){
   		  		$.messager.alert('错误','<font color="red">操作失败,您无权限执行此操作</font>','error');
       		  	},  
  		  	404:function(){
  		  		$.messager.alert('错误','操作失败,请求未找到','error');
      		  	}, 
    		408:function(){
    			$.messager.alert('错误','操作失败,请求超时','error');
      		  	}, 
      		500:function(){
      			$.messager.alert('错误','操作失败,服务器系统内部错误','error');
        		}
    	}
    });  
   
});
</script>