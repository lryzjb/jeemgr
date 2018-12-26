<%@ include file="/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">

<title>Character Management</title>

<script type="text/javascript" src="./static/jquery/base/format.js"></script>
<script type="text/javascript" src="./static/jquery/base/datagrid.js"></script>
<script type="text/javascript" src="./static/jquery/base/window.js"></script>
<script type="text/javascript" src="./static/jquery/base/validatebox.js"></script>
<script type="text/javascript" src="./static/jquery/base/settings.js"></script>
<script type="text/javascript" src="./static/jquery/base/object.js"></script>
<script type="text/javascript" src="./static/jquery/base/messager.js"></script>

<script type="text/javascript" src="./static/js/system/roleConfig.js"></script>

</head>

<body>

    <!-- Toolbar -->
    <div id="toolbar">
    	
	    <div style="padding:3px;">
	    	<shiro:hasPermission name="system.role:query"> 
				<a id="btnAdd" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="btnQuery_Click()">查询</a>
			</shiro:hasPermission> 
	     	<shiro:hasPermission name="system.role:add"> 
				<a id="btnAdd" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="btnAdd_Click()">添加</a>
			</shiro:hasPermission> 
			<shiro:hasPermission name="system.role:edit"> 
				<a id="btnEdit" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="btnEdit_Click()">修改</a>
			</shiro:hasPermission> 
			<shiro:hasPermission name="system.role:delete">
				<a id="btnDelete" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="btnDelete_Click()">删除</a>
			</shiro:hasPermission> 
			
		</div>
    </div>
	
	<!-- query window -->
    <div id="window-query" class="easyui-window" title="Query"
		style="width: 280px; height: 130px;"
		data-options="closed:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false"
				style="height: 60px;">
				<form id='form-query' method="post">
					<div class="panel-grid">
						<span class="colspan"> <label for="roleName">角色名称</label> <input
							type='text' class="easyui-validatebox" name="roleName" id='roleNameQuery'
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
	
	<!-- add window -->
    <div id="window-add" class="easyui-window" title="The Role Of Information"  
    	 style="width:750px;height:400px;" data-options="closed:true">
	    <div class="easyui-layout"  data-options="fit:true">
	    	<div data-options="region:'west',split:true,cache:false,title:'Role'" style="width:150px;">
		       <form id='form-add' method="post">
		           <div class="panel-grid">
						<span class="colspan">
							<label for="roleName">角色名称</label>
					        <input type='text' name="roleName" id='roleName'  size='18'  required="required" class="easyui-validatebox" data-options="validType:'maxLength[10]'"  />
			            </span>
		          </div>
		      </form>
	      	</div>
	      	
	      	<div data-options="region:'center',split:true,cache:false,title:'Functional Authority Structure'" style="background: #ccc none; padding: 5px;">
	      		<ul id="addFunctionTree"></ul>
	      	</div>
	      	
	      	<div data-options="region:'south',border:false">
		        <div class="button-row">
		            <a href="javascript:void(0)" onclick="btnAddSave_Click()" class="easyui-linkbutton"  data-options="iconCls:'icon-save'" id="btnAddSave">确定</a> 
			        <a href="javascript:void(0)" onclick="btnAddCancel_Click()" class="easyui-linkbutton minor" data-options="iconCls:'icon-cancel'" id="btnAddCancel">取消</a>
		        </div>
	        </div>
		</div>
	</div>

    <!-- edit window -->
	<div id="window-edit" class="easyui-window" title="The Role Of Information"
		style="width: 750px; height: 400px;" data-options="closed:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',split:true,cache:false,title:' Role'"
				style="width: 150px;">
				<form id='form-edit' method="post">
					<input type="hidden" name="roleId">
					<div class="panel-grid">
						 <span class="colspan"> <label for="roleName">角色名称</label>
							<input type='text' name="roleName" id='roleNames' size='18'
							required="required" class="easyui-validatebox"
							data-options="validType:'maxLength[10]'" />
						</span> 
					</div>
				</form>
			</div>
			<div
				data-options="region:'center',split:true,cache:false,title:'Functional Authority Structure'"
				style="background: #ccc none; padding: 5px;">
				<ul id="editFunctionTree"></ul>
			</div>
			<div data-options="region:'south',border:false">
				<div class="button-row">
					<a href="javascript:void(0)" onclick="btnEditSave_Click()"
						class="easyui-linkbutton" data-options="iconCls:'icon-save'"
						id="btnEditSave">确认</a>  
					<a href="javascript:void(0)" onclick="btnEditCancel_Click()"
						class="easyui-linkbutton minor"
						data-options="iconCls:'icon-cancel'" id="btnEditCancel">取消</a>
				</div>
			</div>
		</div>
	</div>


	<!-- DataGrid -->
	<div id="dataGrid"></div>

</body>
</html>