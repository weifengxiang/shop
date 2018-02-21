/**
 * 初始化添加商品盘点设置页面
 */
function initAddBaseComcheckCfgPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
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