/**
 * 初始化添加盘点详情页面
 */
function initAddBaseCheckDetailPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
}
/**
 * 初始化编辑盘点详情页面
 */
function initEditBaseCheckDetailPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/BaseCheckDetail/getBaseCheckDetailById?id="+paramOpts.data.id;
	$('#addeditbasecheckdetailform').form('load',SKY.urlCSRF(url));
}
/**
 * 保存添加/编辑盘点详情
 */
function submitAddEditBaseCheckDetailForm() {
	var options = { 
	   data:{
    	   "data":function(){
    		   //return JSON.stringify();
    	   }
       },   
       beforeSubmit:function(data){
			return $('#addeditbasecheckdetailform').form('enableValidation').form('validate');
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
       url:SKY.urlCSRF(basepath+'base/BaseCheckDetail/saveAddEditBaseCheckDetail'), 
       type:'post',   
       dataType:'json',   
       timeout:-1    
	};  
	$('#addeditbasecheckdetailform').ajaxSubmit(options);
	
}