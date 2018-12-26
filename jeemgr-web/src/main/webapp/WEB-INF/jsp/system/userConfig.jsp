<%@ include file="/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">

<title>User Management</title>

<script type="text/javascript" src="./static/jquery/base/format.js"></script>
<script type="text/javascript" src="./static/jquery/base/datagrid.js"></script>
<script type="text/javascript" src="./static/jquery/base/window.js"></script>
<script type="text/javascript" src="./static/jquery/base/validatebox.js"></script>
<script type="text/javascript" src="./static/jquery/base/settings.js"></script>
<script type="text/javascript" src="./static/jquery/base/object.js"></script>
<script type="text/javascript" src="./static/jquery/base/messager.js"></script>

<script type="text/javascript" src="./static/js/system/userConfig.js"></script>

</head>

<body>

<!-- Toolbar -->
	<div id="toolbar">

		<div style="padding: 3px;">
			<shiro:hasPermission name="system.user:query">
				<a id="btnQuery" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-query'"
				onclick="btnQuery_Click()">查询</a> 
			</shiro:hasPermission>
			<shiro:hasPermission name="system.user:add">
				<a id="btnAdd" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add'"
				onclick="btnAdd_Click()">添加</a> 
			</shiro:hasPermission>
			<shiro:hasPermission name="system.user:edit">
				<a id="btnEdit" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit'"
				onclick="btnEdit_Click()">修改</a> 
			</shiro:hasPermission>
			<shiro:hasPermission name="system.user:delete">
				<a id="btnDelete" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove'"
				onclick="btnDelete_Click()">删除</a>
			</shiro:hasPermission>
		</div>

	</div>
	
	<div id="window-query" class="easyui-window" title="Query"
		style="width: 280px; height: 130px;"
		data-options="closed:true">
		<div class="easyui-layout" data-options="fit:true">

			<div data-options="region:'north',border:false"
				style="height: 60px;">
				<form id='form-query' method="post">
					<div class="panel-grid">
						<span class="colspan"> <label for="username">用户名</label> <input
							type='text' class="easyui-validatebox" name="username" id='usernameQuery'
							size='8'
							data-options="validType:['onlyLetterNumberUnderline','maxLength[20]']" />
						</span> 
					</div>
				</form>
			</div>
			<div data-options="region:'south',border:false">
				<div class="button-row">
					<a href="javascript:void(0)" onclick="btnSerach_Click()"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-query'" id="btnQuery">确定</a>
					<a href="javascript:void(0)" onclick="serachCancel_Click()"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" id="btnQueryCancel">取消</a>
				</div>
			</div>
		</div>
	</div>
	
	<!-- add -->
	<div id="window-add" class="easyui-window" title="Add"
		style="width: 750px; height: 400px;"
		data-options="closed:true">
		<div class="easyui-layout" data-options="fit:true">

			<div data-options="region:'north',border:false"
				style="height: 60px;">
				<form id='form-add' method="post">
					<div class="panel-grid">
						<span class="colspan"> <label for="username">用户名</label> <input
							type='text' class="easyui-validatebox" name="username" id='username'
							size='8'
							data-options="validType:['onlyLetterNumberUnderline','maxLength[20]']" />
						</span> <span class="colspan"> <label for="password">密码</label> <input
							type='password' class="easyui-validatebox" name="password" id='password'
							size='8'
							data-options="validType:['onlyLetterNumberUnderline','englishDescription_Less[16]']" />
						</span> <span class="colspan" id="email"> <label
							for="userEmail">邮箱</label> <input type="text"
							class="easyui-validatebox" name="email" id='email'
							data-options="validType:'maxLength[20]'" />
						</span> <span class="colspan"> <label for="mobile">电话</label>
							<input type='text' class="easyui-numberbox" name="mobile"
							id='mobile' data-options="validType:'maxLength[20]'" />
						</span> <span class="colspan"> <label for="status">状态</label> <select
							class="easyui-combobox"
							data-options="panelHeight:'auto',panelWidth:'100',editable:false "
							required="required" name="status"
							id="status">
								<option value="0">正常</option>
								<option value="1">停用</option>
						</select>
						</span>
					</div>
				</form>
			</div>

			<div data-options="region:'west',split:true,cache:false,title:'角色列表'"
				style="padding: 8px; width: 160px;">
				<ul id="addRoleTree"></ul>
			</div>

			<div
				data-options="region:'center',split:true,cache:false,title:'权限列表'"
				style="padding: 8px; background: #eee;">
				<ul id="addMenuTree"></ul>
			</div>
			<div data-options="region:'south',border:false">
				<div class="button-row">
					<a href="javascript:void(0)" onclick="btnAddSave_Click()"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-save'" id="btnAddSave">确定</a>
					<a href="javascript:void(0)" onclick="btnAddCancel_Click()"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" id="btnAddCancel">取消</a>

				</div>
			</div>
		</div>
	</div>

	<!-- edit -->
    <div id="window-edit" class="easyui-window" title="User Information " style="width:750px;height:400px;" data-options="iconCls:'icon-edit'" closed="true">  
    <div class="easyui-layout" data-options="fit:true">
        
       <div data-options="region:'north',border:false" style="height:130px;">
        <form id='form-edit' method="post">
        	<input type="hidden" name="userId">
			<!--<input type="hidden" name="createId">
			<input type="hidden" name="createTime">-->
			 <input type="hidden" name="password"> 
	        <div class="panel-grid">
		        <span class="colspan">
		        <label for="username">用户名</label>
		        <input type='text' class="easyui-validatebox" name="username" id='usernames' size='8' data-options="validType:['onlyLetterNumberUnderline','maxLength[20]']" readonly="readonly"/>
		        </span>
		         <span class="colspan">
		        <label for="email">邮箱</label>
		        <input type='text' class="easyui-validatebox"  name="email" id='emails' data-options="validType:'maxLength[20]'"  />
		        </span>
		        <span class="colspan">
		        <label for="mobile">电话</label>
		        <input type='text' name="mobile" id='mobiles' size='10'  class="easyui-numberbox"  data-options="validType:'maxLength[20]'"/>
		        </span>
		        <span class="colspan">
		        <label for="status">状态</label>
		        <select class="easyui-combobox" data-options="required:true,panelHeight:'auto',panelWidth:'100',editable:false"
		         name="status" id="statuss">
		         <option  value="0">正常</option>
		         <option value="1">停用</option>
		        </select>
		        </span>
	        </div>
        </form>
        </div>
            
       <div data-options="region:'west',split:true,cache:false,title:'User Role'" style="padding:8px;width:160px;">
           <ul id="editRoleTree"></ul>
       </div>
       
       <div data-options="region:'center',split:true,cache:false,title:'Role Function'" style="padding:8px;background:#eee;">
          <ul id="editMenuTree"></ul>
       </div>
       <div data-options="region:'south',border:false">
        <div class="button-row">
        	<a href="javascript:void(0)" onclick="btnEditSave_Click()" class="easyui-linkbutton" 
        	data-options="iconCls:'icon-save'" id="btnEditSave">确定</a> 
             
            <a href="javascript:void(0)" onclick="btnEditCancel_Click()" class="easyui-linkbutton" 
            data-options="iconCls:'icon-cancel'" id="btnEditCancel">取消</a>
            
        </div>
        </div>
        </div>
      </div>  

    
    <!-- DataGrid -->
    <div id="dataGrid"></div>

</body>
</html>