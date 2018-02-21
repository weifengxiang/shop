//初始化
function init(){
	$('#listbasecomcatedg').datagrid('options').url=SKY.urlCSRF(basepath+'sys/BaseComCate/getBaseComCateByPage');
	$('#listbasecomcatedg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			return ft.getJSON();
		}
	});
}
 /**
 *添加鍟嗗搧闂ㄧ被绠＄悊
 **/
function addBaseComCate(){
	var opts={
				id:'addBaseComCate',
				title:'添加鍟嗗搧闂ㄧ被绠＄悊',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'sys/BaseComCate/initAddBaseComCatePage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initAddBaseComCatePage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initAddBaseComCatePage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
 /**
 *删除鍟嗗搧闂ㄧ被绠＄悊
 **/
function delBaseComCate(){
	var checkeds=$('#listbasecomcatedg').datagrid('getChecked');
	if(null==checkeds||checkeds.length<1){
		$.messager.alert('提示','请选择要删除的记录','info');
		return;
	}else{
		var msg="确定要删除"+checkeds.length+"条数据?";
		$.messager.confirm("删除确认",msg,
		function (r){
			if(r){
				SKY_EASYUI.mask('正在进行删除，请稍等...');
				var url = SKY.urlCSRF(basepath+'sys/BaseComCate/delBaseComCate');
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
		    				$('#listbasecomcatedg').datagrid('reload');
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
*修改鍟嗗搧闂ㄧ被绠＄悊
**/
function editBaseComCate(){
	var checkeds=$('#listbasecomcatedg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'editBaseComCate',
				title:'修改鍟嗗搧闂ㄧ被绠＄悊',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'sys/BaseComCate/initEditBaseComCatePage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initEditBaseComCatePage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initEditBaseComCatePage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
*查看明细
**/
function detailBaseComCate(){
	var checkeds=$('#listbasecomcatedg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'detailBaseComCate',
				title:'鍟嗗搧闂ㄧ被绠＄悊明细',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'sys/BaseComCate/initDetailBaseComCatePage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initDetailBaseComCatePage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                };
		            	this.content.initDetailBaseComCatePage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 * 查询按钮
 */
function searchButton(){
	$('#listbasecomcatedg').datagrid('options').url=SKY.urlCSRF(basepath+'sys/BaseComCate/getBaseComCateByPage');
	$('#listbasecomcatedg').datagrid('load', {
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
			var isLeaf =$('#q_isLeaf').textbox("getValue");
			if(isLeaf){
				ft.put("isLeaf@=", isLeaf);
			}
			var note =$('#q_note').textbox("getValue");
			if(note){
				ft.put("note@=", note);
			}
			return ft.getJSON();
		}
	});
}