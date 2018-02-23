/**
 * 初始化添加盘点计划页面
 */
function initAddBaseCheckPlanPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
}
/**
 * 初始化编辑盘点计划页面
 */
function initEditBaseCheckPlanPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/BaseCheckPlan/getBaseCheckPlanById?id="+paramOpts.data.id;
	$('#addeditbasecheckplanform').form('load',SKY.urlCSRF(url));
}
/**
 * 保存添加/编辑盘点计划
 */
function submitAddEditBaseCheckPlanForm() {
	var options = { 
	   data:{
    	   "data":function(){
    		   //return JSON.stringify();
    	   }
       },   
       beforeSubmit:function(data){
			return $('#addeditbasecheckplanform').form('enableValidation').form('validate');
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
       url:SKY.urlCSRF(basepath+'base/BaseCheckPlan/saveAddEditBaseCheckPlan'), 
       type:'post',   
       dataType:'json',   
       timeout:-1    
	};  
	$('#addeditbasecheckplanform').ajaxSubmit(options);
	
}