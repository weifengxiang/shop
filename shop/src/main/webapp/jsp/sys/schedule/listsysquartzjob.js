//初始化
function init(){
	$('#listsysquartzjobdg').datagrid('options').url=SKY.urlCSRF(basepath+'sys/SysQuartzJob/getSysQuartzJobByPage');
	$('#listsysquartzjobdg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			return ft.getJSON();
		}
	});
}
 //添加
function addSysQuartzJob(){
	SKY_EASYUI.appendRow('listsysquartzjobdg',{});
}
//删除
function delSysQuartzJob(){
	var checked=$('#listsysquartzjobdg').datagrid('getChecked');
	if(null==checked){
		return;
	}else{
		var msg=checked.length+'行记录被删除';
		$.messager.confirm("删除确认",msg,
		function (r){
			if(r){
				SKY_EASYUI.mask('正在进行删除，请稍等...');
				var url = SKY.urlCSRF(basepath+'sys/SysQuartzJob/delSysQuartzJob');
				var params = {
							"delRows":JSON.stringify(checked)
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
		    				$('#listsysquartzjobdg').datagrid('reload');
		    			}
		    		},
		    		error:function(data,status,e){
		    			SKY_EASYUI.unmask();
		    			$.messager.alert("提示","删除失败,请联系管理员","info");
		    		}
				});
			}else{
				return;
			}
		}
		);
	}
}
//修改
function editSysQuartzJob(){
	SKY_EASYUI.beginEditChecked('listsysquartzjobdg');
}
//保存
function saveSysQuartzJob(){
	var gridId="listsysquartzjobdg";
	if(!SKY_EASYUI.endEditing(gridId)){
		$.messager.alert("错误","请正确输入必填信息","info");    
		return;
	}
	// 获取新增的数据
	var insertRows = $('#'+gridId).datagrid('getChanges','inserted');
	// 获取修改的数据
	var updateRows = $('#'+gridId).datagrid('getChanges','updated');
		
	if(insertRows.length == 0 
			&& updateRows.length == 0 
				&&delRows.length == 0){							
		$.messager.alert("提示","无数据更新！","info");
		return;
	}
	var msg = "";
	if(null!=insertRows && insertRows.length > 0){
		msg = "新增："+insertRows.length+"行<br>";
	}
	if(null!=updateRows && updateRows.length > 0){
		msg =msg+ "更新："+updateRows.length+"行<br>";
	}
	
	msg = msg+"确认提交？";	
	var url = basepath+'sys/SysQuartzJob/saveSysQuartzJob';
	url=SKY.urlCSRF(url);
	// 提示保存信息
	$.messager.confirm("保存确认",msg,
	     function (r){
			if(r){
				SKY_EASYUI.mask('正在进行保存，请稍等...');
				var params = {
							"insertRows":JSON.stringify(insertRows),
							"updateRows":JSON.stringify(updateRows)
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
		    				$('#'+gridId).datagrid('reload');
		    			}
		    		},
		    		error:function(data,status,e){
		    			SKY_EASYUI.unmask();
		    			$.messager.alert("提示","保存失败,请联系管理员","info");
		    		}
				});
			}else{// 点击取消
				return;
			}
		}
	);
}
/**
 * 查询按钮
 */
function searchButton(){
	$('#listsysquartzjobdg').datagrid('options').url=SKY.urlCSRF(basepath+'sys/SysQuartzJob/getSysQuartzJobByPage');
	$('#listsysquartzjobdg').datagrid('load', {
		filter : function(){
			var ft = new HashMap();
			var id =$('#q_id').textbox("getValue");
			if(id){
				ft.put("id@=", id);
			}
			var jobName =$('#q_jobName').textbox("getValue");
			if(jobName){
				ft.put("jobName@=", jobName);
			}
			var jobGroupName =$('#q_jobGroupName').textbox("getValue");
			if(jobGroupName){
				ft.put("jobGroupName@=", jobGroupName);
			}
			var jobClass =$('#q_jobClass').textbox("getValue");
			if(jobClass){
				ft.put("jobClass@=", jobClass);
			}
			return ft.getJSON();
		}
	});
}
//启动
function startSysQuartzJob(){
	var checked=$('#listsysquartzjobdg').datagrid('getChecked');
	if(null==checked){
		return;
	}else{
		SKY_EASYUI.mask('正在进行启动，请稍等...');
		var url = SKY.urlCSRF(basepath+'sys/SysQuartzJob/runSysQuartzJob');
		var params = {
					"job":JSON.stringify(checked)
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
    				$('#listsysquartzjobdg').datagrid('reload');
    			}
    		},
    		error:function(data,status,e){
    			SKY_EASYUI.unmask();
    			$.messager.alert("提示","启动失败,请联系管理员","info");
    		}
		});
		
	}
}
//暂停
function pauseSysQuartzJob(){
	var checked=$('#listsysquartzjobdg').datagrid('getChecked');
	if(null==checked){
		return;
	}else{
		SKY_EASYUI.mask('正在暂停，请稍等...');
		var url = SKY.urlCSRF(basepath+'sys/SysQuartzJob/pauseSysQuartzJob');
		var params = {
					"job":JSON.stringify(checked)
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
    				$('#listsysquartzjobdg').datagrid('reload');
    			}
    		},
    		error:function(data,status,e){
    			SKY_EASYUI.unmask();
    			$.messager.alert("提示","暂停失败,请联系管理员","info");
    		}
		});
		
	}	
}
//立即执行
function runSysQuartzJob(){
	var checked=$('#listsysquartzjobdg').datagrid('getChecked');
	if(null==checked){
		return;
	}else{
		SKY_EASYUI.mask('正在执行，请稍等...');
		var url = SKY.urlCSRF(basepath+'sys/SysQuartzJob/triggerSysQuartzJob');
		var params = {
					"job":JSON.stringify(checked)
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
    				$('#listsysquartzjobdg').datagrid('reload');
    			}
    		},
    		error:function(data,status,e){
    			SKY_EASYUI.unmask();
    			$.messager.alert("提示","执行失败,请联系管理员","info");
    		}
		});
		
	}	
}
//停止
function stopSysQuartzJob(){
	var checked=$('#listsysquartzjobdg').datagrid('getChecked');
	if(null==checked){
		return;
	}else{
		SKY_EASYUI.mask('正在停止，请稍等...');
		var url = SKY.urlCSRF(basepath+'sys/SysQuartzJob/stopSysQuartzJob');
		var params = {
					"job":JSON.stringify(checked)
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
    				$('#listsysquartzjobdg').datagrid('reload');
    			}
    		},
    		error:function(data,status,e){
    			SKY_EASYUI.unmask();
    			$.messager.alert("提示","停止失败,请联系管理员","info");
    		}
		});
		
	}	
}
//恢复
function resumeSysQuartzJob(){
	var checked=$('#listsysquartzjobdg').datagrid('getChecked');
	if(null==checked){
		return;
	}else{
		SKY_EASYUI.mask('正在恢复，请稍等...');
		var url = SKY.urlCSRF(basepath+'sys/SysQuartzJob/resumeSysQuartzJob');
		var params = {
					"job":JSON.stringify(checked)
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
    				$('#listsysquartzjobdg').datagrid('reload');
    			}
    		},
    		error:function(data,status,e){
    			SKY_EASYUI.unmask();
    			$.messager.alert("提示","恢复失败,请联系管理员","info");
    		}
		});
		
	}	
}