<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
String sessionID = session.getId();

String easyuiThemeName = "ui-cupertino";
Cookie[] cookies = request.getCookies();
//以下代码根据个人情况自己写,原理就是找到指定名称的Cookie就将此名称的Cookie值赋值赋值给定义的变量
if(cookies != null && cookies.length > 0) {
    int b;
    for(int i = 0; i < cookies.length; i++) {
        if(cookies[i].getName().equals("themeName")) {
        	easyuiThemeName = cookies[i].getValue();
            b = 2;
            if(b == 1)
                break;
        }
    }
}
%>
<base href="<%=basePath%>">
<!-- Meta  ui-cupertino-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="后台管理系统" />
<meta name="description" content="后台管理系统" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 如果安装了GCF，则使用GCF来渲染页面，如果为安装GCF，则使用最高版本的IE内核进行渲染  -->
<!-- End of Meta -->

<link rel="icon" href="./img/logo/travelsky.ico" type="image/x-icon" />
<link rel="shortcut icon" href="./img/logo/travelsky.ico" type="image/x-icon" />

<!-- Libraries -->

<!-- Themes -->
<link rel="stylesheet" type="text/css" href="./static/css/generic.css">
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="./static/themes/<%=easyuiThemeName%>/easyui.css">
<link rel="stylesheet" type="text/css" href="./static/themes/icon.css">
<link rel="stylesheet" type="text/css" href="./static/css/global.css">
<link rel="stylesheet" type="text/css" href="./static/css/font-awesome.min.css">

<script type="text/javascript" src="./static/jquery/lib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="./static/jquery/lib/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./static/jquery/lib/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="./static/jquery/lib/jquery.cookie.js"></script>

<script type="text/javascript" src="./static/jquery/base/settings.js"></script>
<script type="text/javascript" src="./static/jquery/base/window.js"></script>
<script type="text/javascript" src="./static/jquery/base/datagrid.js"></script>
<script type="text/javascript" src="./static/jquery/base/messager.js"></script>
<script type="text/javascript" src="./static/jquery/base/validatebox.js"></script>
<script type="text/javascript" src="./static/jquery/base/object.js"></script>
<script type="text/javascript" src="./static/jquery/base/easyui.datebox.js"></script>

<script type="text/javascript">
 var basePath = '<%=basePath%>';
 var sessionID = '<%=sessionID%>';
</script> 
<!-- End of Libraries -->