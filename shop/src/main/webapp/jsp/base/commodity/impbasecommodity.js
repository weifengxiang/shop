function initImpBaseCommodity(param){
	init();
	var comcate = param.comcate;//商品类别
	var dialog = param.dialog;
	$('#reset').on('click',function(){
								dialog.close();
						   }
				   );
	$('#saveImp').on('click',function(){
								var doImp=function(){
									var options = { 
										    data:{
									    	   "data":function(){
									    		   return JSON.stringify({
									    			   					  "full":"1"==$("input[name='full']:checked").val(),
									    			   					  "catecode":comcate?comcate.data.code:""
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
									        url:SKY.urlCSRF(basepath+'base/BaseCommodity/saveBaseCommodityImpExcel'), 
									        type:'post',   
									        dataType:'json',   
									        timeout:-1    
								    	};  
								    $('#impform').ajaxSubmit(options); 
								}
								if('1'==$("input[name='full']:checked").val()){//全量更新必须钻则小类
									if(!comcate){
										$.messager.alert('提示', '请选择商品小类', 'error');
										return;
									}
									if(comcate.data.code.length!=6){
										$.messager.alert('提示', '请选择商品小类', 'error');
										return;
									}
									$.messager.confirm('确认','您选择的是全量更新,现有小类【'+comcate.data.name+'】的商品库将会被清空,确定这样做吗？',function(r){    
									    if (r){    
									    	doImp();    
									    }    
									});
								}else{
									doImp();
								}
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