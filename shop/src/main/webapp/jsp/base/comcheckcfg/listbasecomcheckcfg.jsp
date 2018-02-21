<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/inc/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<security:csrfMetaTags/>
<script type="text/javascript" src='${basepath}jsp/base/comcheckcfg/listbasecomcheckcfg.js'></script>
<script type="text/javascript">
$(function() {
	init();
});
</script> 
</head>
<body class="easyui-layout" style="width: 100%; height: 100%; padding: 0; border: 0"
	  data-options='border:false,fit:true'>
<div data-options=" region:'center',iconCls: 'icon-table'" title="商品盘点设置">
	<ul class="easyui-tree" id="comcatetree" data-options='animate:true'></ul>
</div>
<div id="mmTree" class="easyui-menu"">
	<div onclick="addBaseComcheckCfg()" data-options="iconCls:'icon-add'">添加盘查人员</div>
	<div class="menu-sep"></div>
	<div onclick="editBaseComcheckCfg()" data-options="iconCls:'icon-edit'">修改盘查人员</div>
	<div class="menu-sep"></div>
	<div onclick="delBaseComcheckCfg()" data-options="iconCls:'icon-20130408025545236_easyicon_net_30'">删除盘查人员</div>
	<div class="menu-sep"></div>
	<div onclick="detailBaseComcheckCfg()" data-options="iconCls:'icon-detail'">查看盘查人员</div>
</div>
</body>
</html>