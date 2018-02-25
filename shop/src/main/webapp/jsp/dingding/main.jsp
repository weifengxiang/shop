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
</head>
<body>
	<div class="easyui-navpanel">
		<div class="easyui-tabs" data-options="tabHeight:60,fit:true,tabPosition:'bottom',border:false,pill:true,narrow:true,justified:true">
			<div style="padding:10px">
				<div class="panel-header tt-inner">
					<img src='${bathpath}skin/images/scanner.png'/><br>消息通知
				</div>
				<p>A modem (modulator-demodulator) is a device that modulates an analog carrier signal to encode digital information, and also demodulates such a carrier signal to decode the transmitted information.</p>
			</div>
			<div style="padding:10px">
				<div class="panel-header tt-inner">
					<img src='${bathpath}skin/images/tablet.png'/><br>盘查计划
				</div>
				<p>In computing, an image scanner—often abbreviated to just scanner—is a device that optically scans images, printed text, handwriting, or an object, and converts it to a digital image.</p>
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
</body>	
</html>