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
								    				textField: 'name',
								    				onSelect:loadData
								    			});
								    		}
										});
						          }
						      });
}
function loadData(){
	var shopCode= $('#organTree').combotree('tree').tree('getSelected').data.code;
	var planCode= $('#checkPlan').combobox('getValue');
	var url='report/selectEmpCheckDetail/'+shopCode+'/'+planCode;
	$('#listempCheckDetaildg').datagrid('options').url=SKY.urlCSRF(basepath+url);
	$('#listempCheckDetaildg').datagrid('load',{});
}