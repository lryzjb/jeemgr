<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<%@ include file="./include.jsp"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="./static/css/mainframe.css">
<script type="text/javascript" src="./static/js/mainframe.js"></script>

<title>后台管理系统</title>
</head>

<body class="easyui-layout backgrd" id="layout">

	<div region="north" border="false" style="height: 52px;background:url(static/img/backgroundEPM/head.png) no-repeat;">
		<!-- Header -->
		<div id="header">
			<!-- Top -->
			<div id="top">
			<img alt="JEEMGR" title="JEEMGR"
					style="margin: 5px 0 0 10px; width: 44px; float: left;"
					src="./static/img/logo/acca6.png">
				<h5
					style="display: inline-block; 
					padding-left: 0px; line-height: 40px; margin: 5px 0 0 5px; float: left; font-family: '微软雅黑'; 
					-webkit-text-shadow: 1px 1px 2px #999; -moz-text-shadow: 1px 1px 2px #999; 
					text-shadow: 1px 1px 2px #999; font-size: 18px">后台管理系统</h5>
				<div class="meta">
					<table style="width: 300px;height:50px;float:right;">
						<tr>
							<td width="60px"><a class="easyui-linkbutton"  
								title="欢迎" id="userid"
								data-options="iconCls:'icon-user',plain:true"></a>${USER_SESSION.username}</td>
							<!-- <td width="60px">更换主题<select id="easyuiTheme" name="easyuiTheme" class="easyui-combobox" onChange="changeTheme()">
												  <option value="metro-blue">metro-blue</option>
											</select></td> -->
							<td width="50px"><i class="fa fa-key fa-fw fa-1x"></i><a href="javascript:void(0)"
								class="easyui-linkbutton" title="修改密码"  data-options="plain:true"
								onclick="addTab('修改密码','./changePassword.jsp','icon-key')">修改密码</a></td>
							<td width="40px"><a class="easyui-linkbutton"
								href="javascript:void(0)" title="注销" id="btnUserLogout"
								data-options="iconCls:'icon-logout',plain:true">注销</a></td>
						</tr>
					</table>

				</div>
			</div>
		</div>
	</div>

	<div region="west" split="true" title="主菜单"
		style="width: 250px;">

		<ul id="treeMenu" class="easyui-tree">
			<shiro:hasPermission name="master:view">
				<li data-options="state:'closed',iconCls:'icon-master'"><span>报表展示</span>
					<ul>
						<shiro:hasPermission name="master.supplier:view">	
							<li data-options="iconCls:'icon-mini-edit'"><a
								href="javascript:void(0);"
								onclick="addTab('省中心信息','master/masSupplier_openPage.action','icon-mini-edit')"><span>demoList</span></a></li>
						</shiro:hasPermission>
					</ul></li>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="system:view">
				<li data-options="state:'closed',iconCls:'icon-set'"><span>系统设置</span>
					<ul>
						<shiro:hasPermission name="system.user:view">
							<li data-options="iconCls:'folder-user'"><a href="javascript:void(0);" 
								onclick="addTab('用户管理','tJeemgrUser/openPage.do','folder-user')"><span>用户管理</span></a></li>
								</shiro:hasPermission>
						<shiro:hasPermission name="system.role:view">
							<li data-options="iconCls:'folder-table'"><a href="javascript:void(0);"
								onclick="addTab('角色管理','tJeemgrRole/openPage.do','folder-table')"><span>角色管理</span></a></li>
								</shiro:hasPermission>
					<!-- 	<shiro:hasPermission name="system.authority.function:view">
							<li data-options="iconCls:'folder-wrench'"><a href="javascript:void(0);"
								onclick="addTab('权限管理','system/functionConfig_openPage.action','folder-wrench')"><span>权限管理</span></a></li>
								</shiro:hasPermission> -->
					</ul>
				</li>
			</shiro:hasPermission>		
			<shiro:hasPermission name="control:view">	
				<li data-options="state:'closed',iconCls:'icon-tip'"><span>系统监控</span>
					<ul>
						<shiro:hasPermission name="control.sql:view">
							<li data-options="iconCls:'icon-sum'"><a href="javascript:void(0);" 
								onclick="addTab('SQL监控','./druid/sql.html','icon-sum')"><span>SQL监控</span></a></li>
								</shiro:hasPermission>
					</ul>
				</li>
			</shiro:hasPermission>	
		</ul>
	</div>
	<div region="center" id="center" split="true">
		<div class="easyui-tabs" id="tabs"
			data-options="border:false,fit:true">
			<div title="欢迎使用" iconCls="icon-acca-tap"
				data-options="href:''"></div>
		</div>
	</div>
	<div region="south" style="height: 20px;" data-options="border:false">
	<!-- 	<div class="footerEPM"></div> -->
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate" data-options="iconCls:'icon-reload'">重新加载</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose" data-options="iconCls:'icon-cancel'">关闭该标签</div>
		<div id="mm-tabcloseother" data-options="iconCls:'icon-cancel'">关闭其他标签</div>
		<div id="mm-tabcloseall" data-options="iconCls:'icon-cancel'">关闭所有标签</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">关闭所有右侧标签</div>
		<div id="mm-tabcloseleft">关闭所有左侧标签</div>
	</div>
</body>
</html>
