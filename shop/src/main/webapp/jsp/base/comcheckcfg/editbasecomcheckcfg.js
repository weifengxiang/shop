/**
 * 初始化添加商品盘点设置页面
 */
function initAddBaseComcheckCfgPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var comCate = paramOpts.comCate;
	$('#comCate').val(comCate.code);
	$('#comCateName').textbox('setValue',comCate.name);
}
/**
 * 初始化编辑商品盘点设置页面
 */
function initEditBaseComcheckCfgPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/BaseComcheckCfg/getBaseComcheckCfgById?id="+paramOpts.data.id;
	$('#addeditbasecomcheckcfgform').form('load',SKY.urlCSRF(url));
}
/**
 * 保存添加/编辑商品盘点设置
 */
function submitAddEditBaseComcheckCfgForm() {
	var options = { 
	   data:{
    	   "data":function(){
    		   //return JSON.stringify();
    	   }
       },   
       beforeSubmit:function(data){
			return $('#addeditbasecomcheckcfgform').form('enableValidation').form('validate');
       },   
       success:function(data){
    	    $.messager.alert('提示',data.name,'info',function(){
    		   if(data.code=='1'){
    			   _callbacks.fire();  
    		   }  	
    	   	});     	   
       },
       error:function(e){
    	   $.messager.alert('提示',JSON.stringify(e),'info');
       },
       url:SKY.urlCSRF(basepath+'base/BaseComcheckCfg/saveAddEditBaseComcheckCfg'), 
       type:'post',   
       dataType:'json',   
       timeout:-1    
	};  
	$('#addeditbasecomcheckcfgform').ajaxSubmit(options);
}
/**
 * 用户选择帮助
 * @returns
 */
function openUserHelp(){
	var opts={
			id:'editBaseComcheckCfg',
			title:'修改商品盘点设置',
			width:600,
			height:450,
			modal:true,
			content:'url:'+SKY.urlCSRF(basepath+'sys/common/help/userchoose'),
			onLoad: function(dialog){ 
	            if(this.content && this.content.initUserHelp){//判断弹出窗体iframe中的driveInit方法是否存在 
	                var paramOpts=new Object();
	                paramOpts.dialog=dialog;
	                paramOpts.close=function(){
	                	dialog.close();
	                };
	                paramOpts.ok=function(list){
	                	if(list.length==1){
	                		$('#empCode').val(list[0].code);
	                		$('#empName').textbox('setValue',list[0].name);
	                		dialog.close();
	                	}else{
	                		//$.messager.alert('提示','请选择一个员工','info');
	                	}
	                	
	                };
	            	this.content.initUserHelp(paramOpts);//调用并将参数传入，此处当然也可以传入其他内容 
	            } 
	        }
		  };
	SKY_EASYUI.open(opts);
}