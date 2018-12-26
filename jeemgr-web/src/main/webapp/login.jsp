<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include.jsp"%>
<!DOCTYPE html>
<html lang="cn">

<head>


<title>后台管理系统</title>

<link rel="icon" href="./static/img/logo/travelsky.ico" type="image/x-icon" />
<link rel="shortcut icon" href="./static/img/logo/travelsky.ico"
	type="image/x-icon" />

<link rel="stylesheet" type="text/css" href="./static/css/login.css">
<script type="text/javascript" src="./static/jquery/base/loginAnimate.js"></script>
<script type="text/javascript" src="./static/jquery/base/pngFix.js"></script>
<script type="text/javascript" src="./static/js/login.js"></script>

<script type="text/javascript">
	DD_belatedPNG.fix('.png-fix');
	
	$(function() {
		if (window.self != window.top) {
			window.top.location = "login.jsp";
		}
		
		//设置默认焦点
		$("#userId").focus();

		/* if ($("#mainFrame", parent.document.body).attr("name") != undefined) {
			window.parent.location.href = "system/login_loginUser.action";
		} */
	});
	/*以下的代码是实现如果用户登录了，就直接跳转到主页*/
	<%
	Object userName = session.getAttribute("userName");
	if(userName!=null){
		String userNameStr = userName.toString();
		Object roleName = session.getAttribute("roleName");
		if(roleName==null){
			%>
			window.location.href='login.jsp';
			<%
		}else{
			%>
			window.location.href='index.jsp';
			<%
		}
	}
	%>
	
</script>
 
<script type="text/javascript">  
    function refresh() {  
        //IE存在缓存,需要new Date()实现更换路径的作用  
        document.getElementById("image").src="image.jsp?"+new Date();  
    }  
</script> 

</head>


<body>

<DIV class="nescs-title" style="height:30px;padding-top:80px;width:650px;margin:0 auto;text-align:left;padding-left:85px;"><I></I>		 
  <img src="static/img/backgroundEPM/u28.jpg" width="100" height="50" style="display:block;float:left;">
  <div style="width:200px;float:left;padding-left:10px;">
  <H4 style="font-size:26px; font-family:'微软雅黑';color:#fff;padding-bottom:5px;text-align:left;">JEEMGR</H4>
<P style="font-size:11px; font-family:'微软雅黑';color:#fff;">后台管理系统</P></DIV>
</div>
	<div class="login-container" style="position:relative;height:200px;width:315px;padding-top:60px;">
	<div style="height:40px;line-height:40px;font-size:13px;font-weight:bold;color:#fff;background:url(static/img/backgroundEPM/userTItle.jpg) no-repeat;position:absolute;top:0;left:0;width:360px;padding-left:15px;">
    	<!-- Login To Your Account -->
    	Virtual Account System of Asia technology complany
    </div>
	
		<form id="loginInputForm" method="post"
			action="/jeemgr-web/index.jsp">
			<input  type="hidden"  id="adcode"  name = "adcode"  value=" ">
			<div class="input-prepend" style="height:50px;">
				<span class="add-on"><i class="icon-user"></i></span> <input
					class="span3" id="userId" name="userId" type="text"
					tabindex="1" placeholder="请输入用户名" value="" style="height:38px;">
			</div>
			<div class="input-prepend" style="height:50px;">
				<span class="add-on"><i class="icon-password"></i></span> <input
					class="span3" onkeypress="" id="password" name="password"
					type="password" tabindex="2" placeholder="请输入密码" value="" style="height:38px;">
			</div>
			<div class="input-prepend">
			 <span class="add-on" ><i class="icon-code"></i> </span>
			   <input  class="span3"  type="text"  tabindex="3" name="inputCode" id ="inputCode" maxlength="4" style="width:225px;height:38px"/> &emsp;&emsp;<span class="add-on" >
			  <img id="image" border="0"  onclick="refresh()" src="image.jsp" title="Click To Change Picture">  </span>
			</div>
			<div class="prompt">
				<span style="font-weight: bold;"></span><span
					class="loginMessage" id="loginMessage"></span>
			</div>
			
			<div style="width:350px;">
			<p class="loginp" style="text-align: center;width:350px;">
				<a href="javascript:void(0);" id="login_btn" class="loginButton"
					onclick="btnLogin_Click()">登录</a>
			</p>
			</div>
		</form>
	</div>
	
	<!-- <div class="footerEPM"></div> -->
</body>
</html>