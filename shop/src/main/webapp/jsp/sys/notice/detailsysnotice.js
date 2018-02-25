/**
 * 初始化通知详细页面
 */
function initDetailSysNoticePage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/SysNotice/getSysNoticeById?id="+paramOpts.data.id;
	$('#detailsysnoticeform').form('load',SKY.urlCSRF(url));
}
