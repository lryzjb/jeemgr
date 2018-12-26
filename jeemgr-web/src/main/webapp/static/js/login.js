function btnLogin_Click() {
	var username = $('#userId').val();
	var password = $('#password').val();

	var inputCode = $('#inputCode').val();
	var adcode = $('#adcode').val();
	if (check()) {
		$("#btn").hide();
		$.ajax({
			url : '/jeemgr-web/tJeemgrUser/userLogin.do',
			data : {
				userName: username,
				password : password,
				adcode:adcode,
				inputCode:inputCode
			},
			type : 'post',
			dataType : 'json',
			success : function(r) {
				if (r.flag){ 
					$('#loginInputForm').submit();
				} else {
					showLoginMessage(r.message);
					//document.getElementById("adcode").value=r.adcode;
					refresh();
				}
			}
		});
	}
}
//校验用户名和密码是不是都为空
function check() {
	if ($.trim($("#userId").val()) == "") {
		showLoginMessage("请输入用户名");
		$("#userId").focus().select();
		return false;
	}
	if ($("#password").val() == "") {
		showLoginMessage("请输入密码");
		$("#password").focus().select();
		return false;
	}
	if ($("#inputCode").val() == "") {
		showLoginMessage("请输入验证码");
		$("#inputCode").focus().select();
		return false;
	}
	return true;
}
//提示信息
function showLoginMessage(msg) {
	$("#loginMessage").html(msg);
	$("#loginMessage").show();
}
//密码大小写锁定提示
function checkuplow(evt) {
	var event = evt ? evt : window.event;
	var code = event.keyCode || event.which;
	var isshift = event.shiftKey;
	// 没按shift，大写或按shift，小写
	if (((code >= 65 && code <= 90) && !isshift)
			|| ((code >= 97 && code <= 122) && isshift)) {
		showMessage("Please Note，You Open The Caps Lock！");
	} else {
		showMessage("");
	}
}
//在登录界面给enter键绑定登录事件
document.onkeydown = function(evt) {
	var event = evt ? evt : window.event;
	if (!$("#btn").is(":hidden")) {
		if (event.keyCode == '13') {
			btnLogin_Click();
		}
	}
};