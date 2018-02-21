//初始化
function init(){
	$('#listbasecommoditydg').datagrid('options').url=SKY.urlCSRF(basepath+'sys/BaseCommodity/getBaseCommodityByPage');
	$('#listbasecommoditydg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			return ft.getJSON();
		}
	});
}
 /**
 *添加鍟嗗搧绠＄悊
 **/
function addBaseCommodity(){
	var opts={
				id:'addBaseCommodity',
				title:'添加鍟嗗搧绠＄悊',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'sys/BaseCommodity/initAddBaseCommodityPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initAddBaseCommodityPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initAddBaseCommodityPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
 /**
 *删除鍟嗗搧绠＄悊
 **/
function delBaseCommodity(){
	var checkeds=$('#listbasecommoditydg').datagrid('getChecked');
	if(null==checkeds||checkeds.length<1){
		$.messager.alert('提示','请选择要删除的记录','info');
		return;
	}else{
		var msg="确定要删除"+checkeds.length+"条数据?";
		$.messager.confirm("删除确认",msg,
		function (r){
			if(r){
				SKY_EASYUI.mask('正在进行删除，请稍等...');
				var url = SKY.urlCSRF(basepath+'sys/BaseCommodity/delBaseCommodity');
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
		    				$('#listbasecommoditydg').datagrid('reload');
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
*修改鍟嗗搧绠＄悊
**/
function editBaseCommodity(){
	var checkeds=$('#listbasecommoditydg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'editBaseCommodity',
				title:'修改鍟嗗搧绠＄悊',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'sys/BaseCommodity/initEditBaseCommodityPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initEditBaseCommodityPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initEditBaseCommodityPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
*查看明细
**/
function detailBaseCommodity(){
	var checkeds=$('#listbasecommoditydg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'detailBaseCommodity',
				title:'鍟嗗搧绠＄悊明细',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'sys/BaseCommodity/initDetailBaseCommodityPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initDetailBaseCommodityPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                };
		            	this.content.initDetailBaseCommodityPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 * 查询按钮
 */
function searchButton(){
	$('#listbasecommoditydg').datagrid('options').url=SKY.urlCSRF(basepath+'sys/BaseCommodity/getBaseCommodityByPage');
	$('#listbasecommoditydg').datagrid('load', {
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
			var spec =$('#q_spec').textbox("getValue");
			if(spec){
				ft.put("spec@=", spec);
			}
			var barCode =$('#q_barCode').textbox("getValue");
			if(barCode){
				ft.put("barCode@=", barCode);
			}
			var cateCode =$('#q_cateCode').textbox("getValue");
			if(cateCode){
				ft.put("cateCode@=", cateCode);
			}
			var note =$('#q_note').textbox("getValue");
			if(note){
				ft.put("note@=", note);
			}
			return ft.getJSON();
		}
	});
}