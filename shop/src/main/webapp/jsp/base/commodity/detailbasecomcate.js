/**
 * 初始化鍟嗗搧闂ㄧ被绠＄悊详细页面
 */
function initDetailBaseComCatePage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"sys/BaseComCate/getBaseComCateById?id="+paramOpts.data.id;
	$('#detailbasecomcateform').form('load',SKY.urlCSRF(url));
}
