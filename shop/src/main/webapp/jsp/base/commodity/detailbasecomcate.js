/**
 * 初始化商品门类管理详细页面
 */
function initDetailBaseComCatePage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/BaseCommodity/getBaseComCateById?id="+paramOpts.data.id;
	$('#detailbasecomcateform').form('load',SKY.urlCSRF(url));
}
