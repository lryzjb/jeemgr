/**
 * 
 */
function showConfirm(titleText, infoText, callback){
	$.messager.confirm(titleText, infoText, callback);
}

//提示信息 
function showMessage(msg){
	$.messager.show({title:'提示',msg:msg}); 
	//$.messager.show({title:msg_ajaf_message,msg:msg}); 
	//$('#message1,#message2,.message1').html(msg);
	//$('#message1,#message2,.message1').show();
}

//错误信息 
function showAlert(msg){ 
	//$.messager.alert('警告',msg,'error'); 
	$.messager.alert(msg_ajaf_warn,msg,'warning'); 
}

//信息 
function showInfo(msg){ 
	//$.messager.alert('警告',msg,'error'); 
	$.messager.alert(msg_ajaf_message,msg,'info'); 
}