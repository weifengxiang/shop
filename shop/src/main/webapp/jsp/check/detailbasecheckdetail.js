/**
 * 初始化盘点详情详细页面
 */
function initDetailBaseCheckDetailPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/BaseCheckDetail/getBaseCheckDetailById?id="+paramOpts.data.id;
	$('#detailbasecheckdetailform').form('load',SKY.urlCSRF(url));
}
