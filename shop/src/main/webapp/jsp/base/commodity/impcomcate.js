function initImpComCate(param){
	init();
	var dialog = param.dialog;
	$('#reset').on('click',function(){
								dialog.close();
						   }
				   );
	$('#saveImp').on('click',function(){
						    	var options = { 
								    data:{
							    	   "data":function(){
							    		   return JSON.stringify({
							    			   					  "full":"1"==$("input[name='full']:checked").val()
							    			   					  });
							    	   }
							        },   
						            beforeSubmit:function(data){
										if($('#impform').form('enableValidation').form('validate')){
											SKY_EASYUI.mask('正在上传，请稍等...');
										}else{
											return false;
										}
							        },   
							        success:function(data){
							        	SKY_EASYUI.unmask();
							           $.messager.alert('提示',data.name,'info',function(){		
																	        	   dialog.close();
																	        	   param.callback();
																	    	   });
							        },
							        error:function(e){
							           SKY_EASYUI.unmask();
							    	   $.messager.alert('提示',e.responseText,'info');
							        },
							        url:SKY.urlCSRF(basepath+'base/BaseCommodity/saveComCateImpExcel'), 
							        type:'post',   
							        dataType:'json',   
							        timeout:-1    
						    	};  
						    	$('#impform').ajaxSubmit(options); 
							 }
							);
}
function init(){
	$('#fileinput').filebox({    
	    buttonText: '选择Excel', 
	    buttonAlign: 'right',
	    prompt:'选择Excel',
	    required:true,
	    accept:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel'
	});
}