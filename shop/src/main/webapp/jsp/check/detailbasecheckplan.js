/**
 * 初始化盘点计划详细页面
 */
function initDetailBaseCheckPlanPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/BaseCheckPlan/getBaseCheckPlanById?id="+paramOpts.data.id;
	$('#detailbasecheckplanform').form('load',SKY.urlCSRF(url));
}
