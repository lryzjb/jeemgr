function checkForm(){
	var userPassword = $("#userPassword").val();
	var password1 = $("#userPassword1").val();
	var password2 = $("#userPassword2").val();
	if($.trim(userPassword)==''){
		 showAlert('用户密码不能为空');
		 return false;
	}
	if($.trim(password1)==''){
		 showAlert('新密码不能为空');
		 return false;
		}
	if($.trim(password2)==''){
		 showAlert('确认密码不能为空');
		 return false;
	}
	return true;
}
/**
 * 添加保存操作
 */
function btnAddSave_Click(){
	if(!checkForm()){
		return;
	}else{
		if($('#form-password').form('validate')){//页面输入框的值符合规范
			//页面输入框的逻辑关系判断
//			if( judgepassword()){//1原始密码输入是否正确
				var userPassword = $("#userPassword").val();
				var password1 = $("#userPassword1").val();
				var password2 = $("#userPassword2").val();
				if(password1===password2){//新密码输入框的值比价是否一样
					$.ajax({
						url : '/jeemgr-web/tJeemgrUser/editPassword.do',
						data : {
							userPassword : userPassword,
							userPassword1 : password1,
							userPassword2 : password2
						},
						type:'post',
						dataType : 'json',
						success : function(r) {
							if(r=="0"){
								showAlert('修改成功!');
							}else if(r=="1"){
								showAlert('原密码输入不正确!');
							}else{
								showAlert('系统错误!');
							}
						}
					});
				}else{
					showAlert("两次输入的新密码不相同!");
				}
//			}else{
//				//showAlert("原始密码错误!");
//			}
		}else{
			showAlert('数据验证失败!');
		}
	}
}

//function judgepassword(){
//	var flag = false;
//	var password = $("#userPassword").val();
//	if($.trim(password)!=''){
//		//得到 值 启动ajax
//		$.ajax({
//			url : 'system/userLoginPass_validatePassword.action',
//			data : {
//				userPassword : password
//			},
//			type:'post',
//			dataType : 'json',
//			success : function(r) {
//				if(r){
//					if(r.flag){
//						  //showMessage(r.message);
//						  $("#ajaxresult").val("0") ;
//			          }else{
//			        	  showAlert(r.message);
//			        	  $("#ajaxresult").val("1") ;
//			          }
//				}else{
//					showAlert('系统错误');
//				}
//			}
//		});
//		if( $("#ajaxresult").val()==0 ){
//			flag = true;
//		}else{
//			flag = false;
//		}
//		return flag;
//	}
//	return false;
//}


//（公共方法）点击取消/关闭：
function btnCancel_Click(window){
	closeWindow(window);
}
//点击取消/关闭：
function cancel_ClickBtn(window){
	$("#userPassword").val("") ;
	$("#userPassword1").val("") ;
	$("#userPassword2").val("") ;
}
$(document).ready(function(){
	$("#userPassword").val("1");
	$("#userPassword").val("");
	$("#userPassword").val("");
	$("#userPassword").val("");
});