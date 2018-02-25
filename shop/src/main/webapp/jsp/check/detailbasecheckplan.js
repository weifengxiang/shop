/**
 * 初始化盘点计划详细页面
 */
function initDetailBaseCheckPlanPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	initComCateTree(paramOpts.data);
}
/**
 * 初始化商品类别
 * @returns
 */
function initComCateTree(data) {
	var params=new HashMap();
	params.put('planCode',data.code);
	var url = basepath + 'base/BaseCheckPlan/getBaseCheckPlanCateTree';
	url=SKY.urlCSRF(url);
	$.ajax({
		url:url,
		type: "POST",
		data:{"data":params.getJSON()},
		dataType:'json',
		success:function(data){
			$('#comcatetree').tree(
					{
						data : data,
						lines:true,
						method : 'POST',
						onClick : function(node) {
							var data=node.data;
							if(data.id){
							}
						}
					});
		}
	});
	
}