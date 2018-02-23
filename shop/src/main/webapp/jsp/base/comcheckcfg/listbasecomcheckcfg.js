//初始化
function init(){
	initComCateTree();
}
function initComCateTree() {
	var rootData=[
					{    
					    "id":"root",    
					    "text":"商品类别树[root]",    
					    "iconCls":"icon-2012080111634",
					    "state":"closed",
					    "data":{
					    	"code":"root",
					    	"name":"商品类别树",
					    }
					}
	              ];
	var url = basepath + 'base/BaseComcheckCfg/getBaseComcheckCfg';
	url=SKY.urlCSRF(url);
	$('#comcatetree').tree(
			{
				data : rootData,
				lines:true,
				method : 'POST',
				onBeforeExpand : function(node, param) {
					$('#comcatetree').tree('options').url = url + "&data="
							+ JSON.stringify(node.data);
				},
				onClick : function(node) {
					var data=node.data;
					if(data.id){
						loadcomcheckcfgdg();
					}
				},
				onContextMenu : function(e, node) {
					e.preventDefault();
				}
			});
}
 /**
 *添加商品盘点设置
 **/
function addBaseComcheckCfg(){
	var selectNode = $('#comcatetree').tree("getData",
	  		 $('#comcatetree').tree("getSelected").target
	  		);
	var comCate = selectNode.data;
	var opts={
				id:'addBaseComcheckCfg',
				title:'添加商品盘点设置',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseComcheckCfg/initAddBaseComcheckCfgPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initAddBaseComcheckCfgPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.comCate=comCate;
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	loadcomcheckcfgdg();
		                };
		            	this.content.initAddBaseComcheckCfgPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 *删除商品盘点设置
 **/
function delBaseComcheckCfg(){
	var checkeds=$('#listbasecomcheckcfgdg').datagrid('getChecked');
	if(null==checkeds||checkeds.length<1){
		$.messager.alert('提示','请选择要删除的记录','info');
		return;
	}else{
		var msg="确定要删除"+checkeds.length+"条数据?";
		$.messager.confirm("删除确认",msg,
		function (r){
			if(r){
				SKY_EASYUI.mask('正在进行删除，请稍等...');
				var url = SKY.urlCSRF(basepath+'base/BaseComcheckCfg/delBaseComcheckCfg');
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
		    				$('#listbasecomcheckcfgdg').datagrid('reload');
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
*修改商品盘点设置
**/
function editBaseComcheckCfg(){
	var checkeds=$('#listbasecomcheckcfgdg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'editBaseComcheckCfg',
				title:'修改商品盘点设置',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseComcheckCfg/initEditBaseComcheckCfgPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initEditBaseComcheckCfgPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	loadcomcheckcfgdg();
		                };
		            	this.content.initEditBaseComcheckCfgPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
*查看明细
**/
function detailBaseComcheckCfg(){
	var checkeds=$('#listbasecomcheckcfgdg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'detailBaseComcheckCfg',
				title:'商品盘点设置明细',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseComcheckCfg/initDetailBaseComcheckCfgPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initDetailBaseComcheckCfgPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=checkeds[0];
		                paramOpts.callBack=function(){
		                	dialog.close();
		                };
		            	this.content.initDetailBaseComcheckCfgPage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 * 查询
 */
function loadcomcheckcfgdg(){
	var selectNode = $('#comcatetree').tree("getData",
	  		 $('#comcatetree').tree("getSelected").target
	  		);
	var comCate = selectNode.data;
	$('#listbasecomcheckcfgdg').datagrid('options').url=SKY.urlCSRF(basepath+'base/BaseComcheckCfg/getBaseComcheckCfgByPage');
	$('#listbasecomcheckcfgdg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			var comCate_code =comCate.code;
			if(comCate){
				ft.put("comCate@=", comCate_code);
			}
			return ft.getJSON();
		}
	});
}