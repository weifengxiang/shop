/**
 * 初始化鍟嗗搧绠＄悊详细页面
 */
function initDetailBaseCommodityPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"sys/BaseCommodity/getBaseCommodityById?id="+paramOpts.data.id;
	$('#detailbasecommodityform').form('load',SKY.urlCSRF(url));
}
