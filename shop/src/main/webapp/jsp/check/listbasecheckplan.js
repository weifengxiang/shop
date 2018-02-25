//初始化
function init(){
	$('#listbasecheckplandg').datagrid('options').url=SKY.urlCSRF(basepath+'base/BaseCheckPlan/getBaseCheckPlanByPage');
	$('#listbasecheckplandg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			return ft.getJSON();
		}
	});
}
 /**
 *添加盘点计划
 **/
function addBaseCheckPlan(){
	var opts={
				id:'addBaseCheckPlan',
				title:'添加盘点计划',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCheckPlan/initAddBaseCheckPlanPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initAddBaseCheckPlanPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initAddBaseCheckPlanPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
 /**
 *删除盘点计划
 **/
function delBaseCheckPlan(){
	var checkeds=$('#listbasecheckplandg').datagrid('getChecked');
	if(null==checkeds||checkeds.length<1){
		$.messager.alert('提示','请选择要删除的记录','info');
		return;
	}else{
		var msg="确定要删除"+checkeds.length+"条数据?";
		$.messager.confirm("删除确认",msg,
		function (r){
			if(r){
				SKY_EASYUI.mask('正在进行删除，请稍等...');
				var url = SKY.urlCSRF(basepath+'base/BaseCheckPlan/delBaseCheckPlan');
				var params = {
							"delRows":JSON.stringify(checkeds)
						};
				$.ajax({
		    		url:url,
		    		type: "POST",
		    		data:params,
		    		dataType:'json',
		    		success:function(data){
		    			SKY_EASYUI.unmask();
		    			$.messager.alert("提示",data.name,"info");
		    			if(data.code != '0'){
		    				$('#listbasecheckplandg').datagrid('reload');
		    			}
		    		}
				});
			}else{
				return;
			}
		}
		);
	}
}
/**
*修改盘点计划
**/
function editBaseCheckPlan(){
	var checkeds=$('#listbasecheckplandg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'editBaseCheckPlan',
				title:'修改盘点计划',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCheckPlan/initEditBaseCheckPlanPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initEditBaseCheckPlanPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initEditBaseCheckPlanPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
*查看明细
**/
function detailBaseCheckPlan(){
	var checkeds=$('#listbasecheckplandg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'detailBaseCheckPlan',
				title:'盘点计划明细',
				width:800,
				height:600,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCheckPlan/initDetailBaseCheckPlanPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initDetailBaseCheckPlanPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                };
		            	this.content.initDetailBaseCheckPlanPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 * 查询按钮
 */
function searchButton(){
	$('#listbasecheckplandg').datagrid('options').url=SKY.urlCSRF(basepath+'base/BaseCheckPlan/getBaseCheckPlanByPage');
	$('#listbasecheckplandg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			var code =$('#q_code').textbox("getValue");
			if(code){
				ft.put("code@=", code);
			}
			var name =$('#q_name').textbox("getValue");
			if(name){
				ft.put("name@=", name);
			}
			return ft.getJSON();
		}
	});
}