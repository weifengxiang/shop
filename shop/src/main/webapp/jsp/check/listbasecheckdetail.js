//初始化
function init(){
	$('#listbasecheckdetaildg').datagrid('options').url=SKY.urlCSRF(basepath+'base/BaseCheckDetail/getBaseCheckDetailByPage');
	$('#listbasecheckdetaildg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			return ft.getJSON();
		}
	});
}
 /**
 *添加盘点详情
 **/
function addBaseCheckDetail(){
	var opts={
				id:'addBaseCheckDetail',
				title:'添加盘点详情',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCheckDetail/initAddBaseCheckDetailPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initAddBaseCheckDetailPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initAddBaseCheckDetailPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
 /**
 *删除盘点详情
 **/
function delBaseCheckDetail(){
	var checkeds=$('#listbasecheckdetaildg').datagrid('getChecked');
	if(null==checkeds||checkeds.length<1){
		$.messager.alert('提示','请选择要删除的记录','info');
		return;
	}else{
		var msg="确定要删除"+checkeds.length+"条数据?";
		$.messager.confirm("删除确认",msg,
		function (r){
			if(r){
				SKY_EASYUI.mask('正在进行删除，请稍等...');
				var url = SKY.urlCSRF(basepath+'base/BaseCheckDetail/delBaseCheckDetail');
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
		    				$('#listbasecheckdetaildg').datagrid('reload');
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
*修改盘点详情
**/
function editBaseCheckDetail(){
	var checkeds=$('#listbasecheckdetaildg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'editBaseCheckDetail',
				title:'修改盘点详情',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCheckDetail/initEditBaseCheckDetailPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initEditBaseCheckDetailPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initEditBaseCheckDetailPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
*查看明细
**/
function detailBaseCheckDetail(){
	var checkeds=$('#listbasecheckdetaildg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'detailBaseCheckDetail',
				title:'盘点详情明细',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCheckDetail/initDetailBaseCheckDetailPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initDetailBaseCheckDetailPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                };
		            	this.content.initDetailBaseCheckDetailPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 * 查询按钮
 */
function searchButton(){
	$('#listbasecheckdetaildg').datagrid('options').url=SKY.urlCSRF(basepath+'base/BaseCheckDetail/getBaseCheckDetailByPage');
	$('#listbasecheckdetaildg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			var planCode =$('#q_planCode').textbox("getValue");
			if(planCode){
				ft.put("planCode@=", planCode);
			}
			var comCode =$('#q_comCode').textbox("getValue");
			if(comCode){
				ft.put("comCode@=", comCode);
			}
			var result =$('#q_result').textbox("getValue");
			if(result){
				ft.put("result@=", result);
			}
			var state =$('#q_state').textbox("getValue");
			if(state){
				ft.put("state@=", state);
			}
			var note =$('#q_note').textbox("getValue");
			if(note){
				ft.put("note@=", note);
			}
			return ft.getJSON();
		}
	});
}