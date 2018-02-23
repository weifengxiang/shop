//初始化
function init(){
	initComCateTree();
}
/**
 * 初始化商品类别
 * @returns
 */
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
	var url = basepath + 'base/BaseCommodity/getComCateTree';
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
						searchButton();
					}
				},
				onContextMenu : function(e, node) {
					e.preventDefault();
					$(this).tree('select', node.target);
					$('#mmTree').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
				}
			});
}
 /**
 *添加商品管理
 **/
function addBaseCommodity(){
	if(!$('#comcatetree').tree("getSelected")){
		$.messager.alert('提示', '请选择商品门类', 'info');
		return;
	}
	var selectNode = $('#comcatetree').tree("getData",
	  		 $('#comcatetree').tree("getSelected").target
	  		);
	if (null == selectNode.data) {
		$.messager.alert('提示', '请选择商品门类', 'info');
		return;
	}
	var opts={
				id:'addBaseCommodity',
				title:'添加商品',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCommodity/initAddBaseCommodityPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initAddBaseCommodityPage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.comCate=selectNode.data;
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
 *删除商品管理
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
				var url = SKY.urlCSRF(basepath+'base/BaseCommodity/delBaseCommodity');
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
*修改商品管理
**/
function editBaseCommodity(){
	var checkeds=$('#listbasecommoditydg').datagrid('getChecked');
	if(null==checkeds||checkeds.length!=1){
		$.messager.alert('提示','请选择一条记录','info');
		return;
	}
	var opts={
				id:'editBaseCommodity',
				title:'修改商品管理',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCommodity/initEditBaseCommodityPage'),
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
				title:'商品管理明细',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCommodity/initDetailBaseCommodityPage'),
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
 * 导入商品Excel
 * @returns
 */
function impBaseCommodity(){
	var opts={
				id:'impBaseCommodity',
				title:'导入商品',
				width:400,
				height:300,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCommodity/initImpBaseCommodityPage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initImpBaseCommodity){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.callback=searchButton;
		            	this.content.initImpBaseCommodity(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 * 查询按钮
 */
function searchButton(){
	if (!$('#comcatetree').tree("getSelected")) {
		$.messager.alert('提示', '请选择商品门类', 'info');
		return;
	}
	var selectNode = $('#comcatetree').tree("getData",
	  		 $('#comcatetree').tree("getSelected").target
	  		);
	if (null == selectNode.data) {
		$.messager.alert('提示', '请选择商品门类', 'info');
		return;
	}
	$('#listbasecommoditydg').datagrid('options').url=SKY.urlCSRF(basepath+'base/BaseCommodity/getBaseCommodityByPage');
	$('#listbasecommoditydg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			ft.put("cateCode@=",selectNode.data.code);
			var code =$('#q_code').textbox("getValue");
			if(code){
				ft.put("code@like", code+"%");
			}
			var name =$('#q_name').textbox("getValue");
			if(name){
				ft.put("name@like", name+"%");
			}
			var spec =$('#q_spec').textbox("getValue");
			if(spec){
				ft.put("spec@like", spec+"%");
			}
			var barCode =$('#q_barCode').textbox("getValue");
			if(barCode){
				ft.put("barCode@like", barCode+"%");
			}
			return ft.getJSON();
		}
	});
}
/**
 * 新增商品类别
 * @returns
 */
function addComCate(){
	var selectNode = $('#comcatetree').tree("getData",
	  		 $('#comcatetree').tree("getSelected").target
	  		);
	if (null == selectNode.data) {
		$.messager.alert('提示', '请选择上级门类', 'info');
		return;
	}
	var opts={
			id:'addBaseComCate',
			title:'添加商品门类',
			width:600,
			height:450,
			modal:true,
			content:'url:'+SKY.urlCSRF(basepath+'base/BaseCommodity/initAddBaseComCatePage'),
			onLoad: function(dialog){ 
	            if(this.content && this.content.initAddBaseComCatePage){//判断弹出窗体iframe中的driveInit方法是否存在 
	                var paramOpts=new Object();
	                paramOpts.dialog=dialog;
	                paramOpts.data=selectNode.data;
	                paramOpts.callBack=function(){
	                	dialog.close();
	                	SKY_EASYUI.refreshSelectTreeNode('comcatetree');
	                };
	            	this.content.initAddBaseComCatePage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
	            } 
	        }
		  };
	SKY_EASYUI.open(opts);
}
/**
 * 修改商品类别
 * @returns
 */
function editComCate(){
	var selectNode = $('#comcatetree').tree("getData",
	  		 $('#comcatetree').tree("getSelected").target
	  		);
	if (null == selectNode.data) {
		$.messager.alert('提示', '请选择上级门类', 'info');
		return;
	}
	var opts={
				title:'修改商品类别',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCommodity/initEditBaseComCatePage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initEditBaseComCatePage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=selectNode.data;
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	SKY_EASYUI.refreshSelectTreeParentNode('comcatetree');
		                };
		            	this.content.initEditBaseComCatePage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 * 删除商品类别
 * @returns
 */
function delComCate(){
	$.messager.confirm('确认对话框', '您确定要删除该商品类别吗？', function(r) {
		if (r) {
			var selectNode = $('#comcatetree').tree("getData",
			  		 $('#comcatetree').tree("getSelected").target
			  		);
			if (null == selectNode.data) {
				$.messager.alert('提示', '请选择要删除的组织机构', 'info');
				return;
			}
			var url = SKY.urlCSRF(basepath+'base/BaseCommodity/delBaseComCate');
			$.ajax({
				type : "POST",
				url : url,
				data : {
					data : JSON.stringify(selectNode.data)
				},
				dataType : "json",
				success : function(data) {
					$.messager.alert('提示', data.name, 'info');
					if (data.code == '1') {
						SKY_EASYUI.refreshSelectTreeParentNode('comcatetree');
					}
				}
			});
		}
	});
}
/**
 * 商品类别详情
 * @returns
 */
function detailComCate(){
	var selectNode = $('#comcatetree').tree("getData",
	  		 $('#comcatetree').tree("getSelected").target
	  		);
	if (null == selectNode.data) {
		$.messager.alert('提示', '请选择商品门类', 'info');
		return;
	}
	var opts={
				title:'商品类别详情',
				width:600,
				height:450,
				modal:true,
				content:'url:'+SKY.urlCSRF(basepath+'base/BaseCommodity/initDetailBaseComCatePage'),
				onLoad: function(dialog){ 
		            if(this.content && this.content.initDetailBaseComCatePage){//判断弹出窗体iframe中的driveInit方法是否存在 
		                var paramOpts=new Object();
		                paramOpts.dialog=dialog;
		                paramOpts.data=selectNode.data;
		                paramOpts.callBack=function(){
		                	dialog.close();
		                	SKY_EASYUI.refreshSelectTreeParentNode('comcatetree');
		                };
		            	this.content.initDetailBaseComCatePage(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
		            } 
		        }
			  };
	SKY_EASYUI.open(opts);
}
/**
 * 导入商品门类
 * @returns
 */
function impComCate(){
	var opts={
			id:'impComCate',
			title:'导入商品类别',
			width:400,
			height:300,
			modal:true,
			content:'url:'+SKY.urlCSRF(basepath+'base/BaseCommodity/initImpComCatePage'),
			onLoad: function(dialog){ 
	            if(this.content && this.content.initImpComCate){//判断弹出窗体iframe中的driveInit方法是否存在 
	                var paramOpts=new Object();
	                paramOpts.dialog=dialog;
	                paramOpts.callback=initComCateTree;
	            	this.content.initImpComCate(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
	            } 
	        }
		  };
	SKY_EASYUI.open(opts);	
}