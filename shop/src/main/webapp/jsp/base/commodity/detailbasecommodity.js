/**
 * 初始化商品管理详细页面
 */
function initDetailBaseCommodityPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/BaseCommodity/getBaseCommodityById?id="+paramOpts.data.id;
	$('#detailbasecommodityform').form('load',SKY.urlCSRF(url));
}
