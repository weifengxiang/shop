/**
 * 初始化商品盘点设置详细页面
 */
function initDetailBaseComcheckCfgPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/BaseComcheckCfg/getBaseComcheckCfgById?id="+paramOpts.data.id;
	$('#detailbasecomcheckcfgform').form('load',SKY.urlCSRF(url));
}
