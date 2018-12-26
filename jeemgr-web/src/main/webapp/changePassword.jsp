<%@ include file="/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>Modify The Login Password</title>
<link type="text/css" href="./static/css/global.css" rel="stylesheet" />
<script type="text/javascript" src="./static/js/changePassWord.js"></script>
</head>
<body>
	
	<div class = "backGround">
	 <form id='form-password' method="post">
	 		<input type = "hidden" name = "ajaxresult" id = "ajaxresult"/>
	        <div class="panel-grid">
		         <span class="colspan" id="password_id"> 
		        	 <label for="userPassword">当前用户密码</label>
		        		 <input type="password" class="easyui-validatebox"	name="userPassword" id='userPassword'
							data-options="validType:'englishDescription_Less[16]'" 
							 required="required" missingMessage="请输入当前用户密码"/> 
				</span> 
		         <span class="colspan" id="password_id"> 
		         	<label for="userPassword">新密码</label>
		         		 <input type="password" class="easyui-validatebox" name="userPassword1" id='userPassword1'
							data-options="validType:'englishDescription_Less[16]'" 
							required="required" missingMessage="请输入新密码"
							 />
				</span>
		     <span class="colspan" id="password_id">
		      	<label for="userPassword">确认密码</label>
		      		 <input type="password" class="easyui-validatebox" name="userPassword2" id='userPassword2'
		      		       required="required" missingMessage="请输入确认密码"
							data-options="validType:'englishDescription_Less[16]'" />
			</span> 
				<div data-options="region:'south',border:false">
					<div class="button-row">
						<a href="javascript:void(0)" onclick="btnAddSave_Click()"
							class="easyui-linkbutton" data-options="iconCls:'icon-save'"
							id="btnAddSave">保存</a> <a href="javascript:void(0)"
							onclick="cancel_ClickBtn('window-changePassword')" class="easyui-linkbutton"
							data-options="iconCls:'icon-cancel'" id="btnAddCancel">取消</a>

					</div>
				</div>
			</div>
        </form>
	</div>
</body>