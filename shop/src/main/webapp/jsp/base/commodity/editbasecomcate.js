/**
 * 初始化添加商品门类管理页面
 */
function initAddBaseComCatePage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
}
/**
 * 初始化编辑商品门类管理页面
 */
function initEditBaseComCatePage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"sys/BaseComCate/getBaseComCateById?id="+paramOpts.data.id;
	$('#addeditbasecomcateform').form('load',SKY.urlCSRF(url));
}
/**
 * 保存添加/编辑商品门类管理
 */
function submitAddEditBaseComCateForm() {
	var options = { 
	   data:{
    	   "data":function(){
    		   //return JSON.stringify();
    	   }
       },   
       beforeSubmit:function(data){
			return $('#addeditbasecomcateform').form('enableValidation').form('validate');
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
       url:SKY.urlCSRF(basepath+'sys/BaseComCate/saveAddEditBaseComCate'), 
       type:'post',   
       dataType:'json',   
       timeout:-1    
	};  
	$('#addeditbasecomcateform').ajaxSubmit(options);
	
}