/**
 * 初始化添加鍟嗗搧绠＄悊页面
 */
function initAddBaseCommodityPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
}
/**
 * 初始化编辑鍟嗗搧绠＄悊页面
 */
function initEditBaseCommodityPage(paramOpts){
	_callbacks.add(paramOpts.callBack);
	$('#cloBtn').on('click',function(){
		paramOpts.dialog.close();
	});
	var url=basepath+"sys/BaseCommodity/getBaseCommodityById?id="+paramOpts.data.id;
	$('#addeditbasecommodityform').form('load',SKY.urlCSRF(url));
}
/**
 * 保存添加/编辑鍟嗗搧绠＄悊
 */
function submitAddEditBaseCommodityForm() {
	var options = { 
	   data:{
    	   "data":function(){
    		   //return JSON.stringify();
    	   }
       },   
       beforeSubmit:function(data){
			return $('#addeditbasecommodityform').form('enableValidation').form('validate');
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
       url:SKY.urlCSRF(basepath+'sys/BaseCommodity/saveAddEditBaseCommodity'), 
       type:'post',   
       dataType:'json',   
       timeout:-1    
	};  
	$('#addeditbasecommodityform').ajaxSubmit(options);
	
}