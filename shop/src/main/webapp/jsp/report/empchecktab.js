/**
 * 初始化页面
 */
function init(){
	var rootData=[
		{    
		    "id":"root",    
		    "text":"组织机构树[root]",    
		    "iconCls":"icon-2012080111634",
		    "state":"closed",
		    "data":{
		    	"code":"root",
		    	"name":"组织机构树",
		    }
		}
      ];
	var url = basepath + 'sys/SysOrgan/getSysOrganTree';
	url=SKY.urlCSRF(url);
	$('#organTree').combotree({
						          data:rootData,//初始combotree时请求的后端接口，即顶层节点的数据的接口。此处用静态json代替
						          method : 'POST',
						          onBeforeExpand: function (node) {
						              var $tree = $('#organTree').combotree("tree");
						              $tree.tree("options").url = url +"&data=" + JSON.stringify(node.data);
						          },
						          onSelect:function(record){
						        	  var orgCode = record.data.code;
						        	  var params = new HashMap();
						        	  params.put("shopCode@=",orgCode);
						        	  $.ajax({
								    		url:SKY.urlCSRF(basepath+"report/checkplan/getCheckPlanByOrganCode"),
								    		type: "POST",
								    		data:{"filter":params.getJSON()},
								    		dataType:'json',
								    		success:function(data){
								    			$('#checkPlan').combobox({
								    				data:data,
								    				valueField: 'code',
								    				textField: 'name'
								    			});
								    		}
										});
						          }
						      });
	//初始化type
	$('#type').combobox({
		onSelect:function(record){
			changeTitle(record);
		}
	});
}
/**
 * 执行查询
 * @returns
 */
function doSearch(){
	if(!$('#organTree').combotree('tree').tree('getSelected')){
		$.messager.alert('提示','请选择门店','error');
		return;
	}
	var shopCode = $('#organTree').combotree('tree').tree('getSelected').data.code;
	if(!shopCode){
		$.messager.alert('提示','请选择门店','error');
		return;
	}
	var planCode = $('#checkPlan').combobox('getValue');
	if(!planCode){
		$.messager.alert('提示','请选择计划','error');
		return;
	}
	var type = $('#type').combobox('getValue');
	if(!type){
		$.messager.alert('提示','请选择统计方式','error');
		return;
	}
	var url=basepath+'report/selectEmpCheckDetail/'+shopCode+'/'+planCode+'/'+type;
	$('#listempCheckDetaildg').datagrid('options').url=SKY.urlCSRF(url);
	$('#listempCheckDetaildg').datagrid('load',{});
}
function expExcel(){
	if(!$('#organTree').combotree('tree').tree('getSelected')){
		$.messager.alert('提示','请选择门店','error');
		return;
	}
	var shopCode = $('#organTree').combotree('tree').tree('getSelected').data.code;
	if(!shopCode){
		$.messager.alert('提示','请选择门店','error');
		return;
	}
	var planCode = $('#checkPlan').combobox('getValue');
	if(!planCode){
		$.messager.alert('提示','请选择计划','error');
		return;
	}
	var type = $('#type').combobox('getValue');
	if(!type){
		$.messager.alert('提示','请选择统计方式','error');
		return;
	}
	if(null==shopCode||null==planCode){
		$.messager.alert('提示','请选择导出条件','info');
		return;
	}else{
		var msg="确定要导出数据?";
		$.messager.confirm("导出确认",msg,
		function (r){
			if(r){
				SKY_EASYUI.mask('正在进行导出，请稍等...');
				var url = SKY.urlCSRF(basepath+'report/createEmpCheckDetailExcel/'+shopCode+'/'+planCode+'/'+type);
				$.ajax({
		    		url:url,
		    		type: "POST",
		    		dataType:'json',
		    		success:function(data){
		    			SKY_EASYUI.unmask();
		    			$.messager.alert("提示",data.name,"info");
		    			var filepath=data.data;
		    			SKY_EASYUI.downLoad(filepath);
		    		}
				});
			}else{
				return;
			}
		}
		);
	}
}
function changeTitle(record){
	var code = record.code;
	var column = $('#listempCheckDetaildg').datagrid('getColumnOption',"NAME");
	if("B"==code){
		column.title='商品大类';
	}else if("M"==code){
		column.title='商品中类';
	}else if("S"==code){
		column.title='商品小类';
	}else if("U"==code){
		column.title='员工';
	}
	//删除所有行
	$("#listempCheckDetaildg").datagrid('loadData', { total: 0, rows: []}); 
	//更新title，设置url为是为了防止加载数据
	$('#listempCheckDetaildg').datagrid({url:''});
}