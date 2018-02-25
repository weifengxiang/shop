/**
 * 初始化盘点计划详细页面
 */
function initDetailBaseCheckPlanPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	$('#planCode').textbox('setValue',paramOpts.data.code);
	$('#planName').textbox('setValue',paramOpts.data.name);
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
							if(data){
								searchPlanDetail();
							}
						}
					});
		}
	});
}
/**
 * 盘查明细查询
 */
function searchPlanDetail(){
	var selectNode = $('#comcatetree').tree("getData",
	  		 $('#comcatetree').tree("getSelected").target
	  		);
	$('#listbasecheckdetaildg').datagrid('options').url=SKY.urlCSRF(basepath+'base/BaseCheckDetail/getBaseCheckDetailByPage');
	$('#listbasecheckdetaildg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			var planCode =$('#planCode').textbox("getValue");
			if(planCode){
				ft.put("planCode@=", planCode);
			}
			var cate_code=selectNode.data.cate_code;
			if(cate_code){
				ft.put("exists(select 1 from base_commodity bc where bc.cate_code='"+cate_code+"' and bc.code=com_code) and 1@=",1);
			}
			return ft.getJSON();
		}
	});
}