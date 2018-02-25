/**
 * 初始化添加通知页面
 */
function initAddSysNoticePage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	$('#publisher').val(userCode)
	$('#publisherName').textbox('setValue',userName)
	$('#pubtime').textbox('setValue',dateTime)
}
/**
 * 初始化编辑通知页面
 */
function initEditSysNoticePage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"base/SysNotice/getSysNoticeById?id="+paramOpts.data.id;
	$('#addeditsysnoticeform').form('options').onLoadSuccess=function(){
																	$('#pubtime').textbox('setValue',dateTime)
																};
	$('#addeditsysnoticeform').form('load',SKY.urlCSRF(url));
	
}
/**
 * 保存添加/编辑通知
 */
function submitAddEditSysNoticeForm() {
	var options = { 
	   data:{
    	   "data":function(){
    		   //return JSON.stringify();
    	   }
       },   
       beforeSubmit:function(data){
			return $('#addeditsysnoticeform').form('enableValidation').form('validate');
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
       url:SKY.urlCSRF(basepath+'base/SysNotice/saveAddEditSysNotice'), 
       type:'post',   
       dataType:'json',   
       timeout:-1    
	};  
	$('#addeditsysnoticeform').ajaxSubmit(options);
	
}