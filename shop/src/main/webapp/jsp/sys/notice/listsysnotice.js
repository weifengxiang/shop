//初始化
function init(){
	$('#listsysnoticedg').datagrid('options').url=SKY.urlCSRF(basepath+'base/SysNotice/getSysNoticeByPage');
	$('#listsysnoticedg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			return ft.getJSON();
		}
	});
}
 /**
 *添加通知
 **/
function addSysNotice(){
	var opts={
				id:'addSysNotice',
				title:'添加通知',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/SysNotice/initAddSysNoticePage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initAddSysNoticePage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initAddSysNoticePage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
 /**
 *删除通知
 **/
function delSysNotice(){
	var checkeds=$('#listsysnoticedg').datagrid('getChecked');
	if(null==checkeds||checkeds.length<1){
		$.messager.alert('提示','请选择要删除的记录','info');
		return;
	}else{
		var msg="确定要删除"+checkeds.length+"条数据?";
		$.messager.confirm("删除确认",msg,
		function (r){
			if(r){
				SKY_EASYUI.mask('正在进行删除，请稍等...');
				var url = SKY.urlCSRF(basepath+'base/SysNotice/delSysNotice');
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
		    				$('#listsysnoticedg').datagrid('reload');
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
*修改通知
**/
function editSysNotice(){
	var checkeds=$('#listsysnoticedg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'editSysNotice',
				title:'修改通知',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/SysNotice/initEditSysNoticePage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initEditSysNoticePage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	searchButton();
		                };
		            	this.content.initEditSysNoticePage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
*查看明细
**/
function detailSysNotice(){
	var checkeds=$('#listsysnoticedg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'detailSysNotice',
				title:'通知明细',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/SysNotice/initDetailSysNoticePage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initDetailSysNoticePage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                };
		            	this.content.initDetailSysNoticePage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 * 查询按钮
 */
function searchButton(){
	$('#listsysnoticedg').datagrid('options').url=SKY.urlCSRF(basepath+'base/SysNotice/getSysNoticeByPage');
	$('#listsysnoticedg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			var title =$('#q_title').textbox("getValue");
			if(title){
				ft.put("title@=", title);
			}
			var content =$('#q_content').textbox("getValue");
			if(content){
				ft.put("content@=", content);
			}
			var publisher =$('#q_publisher').textbox("getValue");
			if(publisher){
				ft.put("publisher@=", publisher);
			}
			var pubtime =$('#q_pubtime').textbox("getValue");
			if(pubtime){
				ft.put("pubtime@=", pubtime);
			}
			var state =$('#q_state').textbox("getValue");
			if(state){
				ft.put("state@=", state);
			}
			return ft.getJSON();
		}
	});
}