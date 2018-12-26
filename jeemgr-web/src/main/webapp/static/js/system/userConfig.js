$(document).ready(
	function(){ 
		dataGrid_LoadData();
	}
);

function dataGrid_LoadData(){
	$('#dataGrid').datagrid({   
	    url: '/jeemgr-web/tJeemgrUser/selectAllUserInfo.do',  //查询全部列表
	    idField: "userId",
	    sortName : 'userId',
		sortOrder : 'asc',
	    rownumbers: true,
	    method:'post',
	    toolbar: '#toolbar',
	    singleSelect:true,
	    pagination:true,
	    fit:true,
	    columns:[[  
	              	{title:'ID',field:'userId',width:120,align:'left',halign:'center',sortable:true,hidden:true},
	                {title:'用户名',field:'username',width:120,align:'left',halign:'center',sortable:true},
	                {title:'状态',field:'status',formatter:columnFormat_Status,width:50,align:'center',halign:'center',sortable:true},
	                {title:'邮箱',field:'email',width:120,align:'left',halign:'center',sortable:true},
	                {title:'电话',field:'mobile',width:120,align:'left',halign:'center',sortable:true},
	                {title:'创建时间',field:'createTime',width:120,align:'left',halign:'center',sortable:true,
	                	formatter : function DateTimeFormatter(value) {
		                    var date=new Date(value);
		                    var y = date.getFullYear();
		                    var m = date.getMonth() + 1;
		                    m = m < 10 ? ('0' + m) : m;
		                    var d = date.getDate();
		                    d = d < 10 ? ('0' + d) : d;
		                    var h = date.getHours();
		                    var minute = date.getMinutes();
		                    minute = minute < 10 ? ('0' + minute) : minute;
		                    return y + '-' + m + '-' + d+' '+h+':'+minute;
	              		}
	                },
	            ]],
	 });
}

function columnFormat_Status(val,row){
	if(val=='0'){
		return '正常';
	}
	if(val=='1'){
		return '停用';
	}
}

function users(obj){
    $.messager.confirm('System Prompts', 'Are You Sure To Change User Identification?', function(r) {
        if (r) {
        	if($(obj).toggleClass("icon-users")){
	        	$(obj).toggleClass("icon-users-disabled");
	        	return false;
	        	};
        	}
    });
}
	



function btnAdd_Click(){
	loadRoleTreeForAddUser();
    //显示窗口
    openWindow('window-add');
	//设置默认焦点
	$("#uid").focus();
}

function btnEdit_Click(){
	//初始化编辑窗口
    var dg = $('#dataGrid');
    //判断是否选中了一样进行编辑
    if(!isSingleSelected(dg)){
	   	showMessage("请选择一条数据");
       return false;
    }
    
    //$("#uid").attr("disabled",true);
    //$("#password").attr("disabled",true);
    //$("#password_id").hide();

	var rowData = getSelected(dg);

	loadRoleTreeForEditUser(rowData.username);
    //$('#authFpmTree').tree({url:null,checkbox:false,data:[{text:'点击左边角色,这里会显示权限信息'}]});
    //显示窗口
    openWindow('window-edit');
    $('#window-edit').window('center');
    $("#form-edit").form("load", rowData);
}

function btnDelete_Click(){
	
	var dg = $('#dataGrid'); //得到list
    //判断是否选中了一样进行编辑
    if(!isSingleSelected(dg)){
	   	showMessage("请选中一条数据");
       return false;
    }
    var rowData = getSelected(dg);
    
    //var params=getObjectFromDataRow(rowData);
    
    showConfirm('提示', '确定删除该用户?', function(r){
    	if (r) {
   		 
   		 $.ajax({
   				url : '/jeemgr-web/tJeemgrUser/deleteUser.do',
   				data : {username:rowData.username,},
   				type:'post',
   				dataType : 'json',
   				success : function(r) {
   					if(r=="0"){
   						showAlert("删除成功");
   						$('#dataGrid').datagrid('load'); 
   					 	$('#dataGrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
   					}else if(r=="1"){
   						showAlert("用户不能为空");
   					}else{
   						showAlert("删除失败");
   					}
   				}
   			});
    	}
    });
}

/**
 * 添加保存操作
 */
function btnAddSave_Click(){
	var checkedNodeList=$("#addRoleTree").tree("getChecked");
	var roleIdList=[];
	if(checkedNodeList&&checkedNodeList.length>0){
		$.each(checkedNodeList, function(i, val) {
			
			roleIdList[i]=val.id;
		});
	}
	
	var params=getObjectFromForm($('#form-add'));
	params.roleIdList = roleIdList;
	
	if($('#form-add').form('validate')){
		
		$.ajax({
			url : '/jeemgr-web/tJeemgrUser/addUser.do',
			data : $.param(params, true),
			type:'post',
			dataType : 'json',
			success : function(r) {
				if(r){
					if(r.flag){
						  closeWindow('window-add');
						  clearInputs('form-add');
						  showMessage(r.message);
						  
			        	 // $('#window-add').window('close');
			        	  $('#dataGrid').datagrid('load');
			        	  $('#dataGrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
			          }else{
			        	  showAlert(r.message);
			        	  $('#dataGrid').datagrid('load'); 
			        	  $('#dataGrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
			          }
					
				}else{
					showAlert('System Exceptions');
				}
	       
			}
		});
		
	}else{
		showAlert('Data Validation Failed');
	}

}

//添加窗口取消
function btnAddCancel_Click(){
	//$('#window-add').window('close');
	closeWindow('window-add');
}

/**
 * 编辑保存操作
 * @param userID
 */
function btnEditSave_Click(){
	var checkedNodeList=$("#editRoleTree").tree("getChecked");
	var roleIdList=[];
	if(checkedNodeList&&checkedNodeList.length>0){
		$.each(checkedNodeList, function(i, val) {
			roleIdList[i]=val.id;
		});
	}
	var params=getObjectFromForm($('#form-edit'));
	params.roleIdList = roleIdList;
	
	//console.info(roleids);
	
	if($('#form-edit').form('validate')){
		$.ajax({
			url : '/jeemgr-web/tJeemgrUser/updateUser.do',
			data : $.param(params, true),
			type:'post',
			dataType : 'json',
			success : function(r) {
				if(r){
					if(r.flag){
			        	 // $('#window-eidt').window('close');
						  closeWindow('window-edit');
						  clearInputs('form-edit');
						  showMessage(r.message);
			        	  $('#dataGrid').datagrid('load');
			        	  $('#dataGrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
			          }else{
			        	  showAlert(r.message);
			        	  showMessage(r.message);
			        	  $('#dataGrid').datagrid('load'); 
			        	  $('#dataGrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
			          }
					
				}else{
					showAlert('System Exceptions');
					 showMessage('System Exceptions');
				}
	       
			}
		});
		
	}else{
		showAlert('Data Validation Failed');
	}


}

//编辑窗口取消
function btnEditCancel_Click(){
	//$('#window-edit').window('close');
	closeWindow('window-edit');
}


function loadRoleTreeForAddUser(){
    $('#addRoleTree').tree({
        checkbox:true,
        cache:false,
        data:[{text:'Character Infomation Is Loading....'}],
        url:'/jeemgr-web/tJeemgrUser/loadRoleTreeForAddUser.do',
        onLoadSuccess: function(node,data){
        },
        onClick: function(node){
            loadAddMenuTreeByRoleId(node.id);
        }
    });
}

function loadRoleTreeForEditUser(userId){
    $('#editRoleTree').tree({
        checkbox:true,
        cache:false,
        data:[{text:'Character Infomation Is Loading....'}],
        url:'/jeemgr-web/tJeemgrUser/loadRoleTreeForEditUser.do?userId='+userId,
        onLoadSuccess: function(node,data){
        },
        onClick: function(node){
            loadEditMenuTreeByRoleId(node.id);
        }
    });
}

function loadAddMenuTreeByRoleId(roleId){
	//$('#rid').val(rid);
    //$('#roleName').val(roleName);
    $('#addMenuTree').tree({
        checkbox:false,lines:true,animate:true,cache:false,
        data: [{text: 'Authority Infomation Of "'+roleId+'" Is Loading.....'}],
        url: '/jeemgr-web/tJeemgrUser/loadMenuTreeForUserConfig.do?roleId='+roleId,
        onLoadSuccess: function(node,data){
            $(this).find('span.tree-checkbox').unbind().click(function(){
                return false;
            });
            $('#btn-edit').linkbutton('enable');
        }
    });
}

function loadEditMenuTreeByRoleId(roleId){
	//$('#rid').val(rid);
    //$('#roleName').val(roleName);
    $('#editMenuTree').tree({
        checkbox:false,lines:true,animate:true,cache:false,
        data: [{text: 'Authority Infomation Of "'+roleId+'" Is Loading.....'}],
        url: '/jeemgr-web/tJeemgrUser/loadMenuTreeForUserConfig.do?roleId='+roleId,
        onLoadSuccess: function(node,data){
            $(this).find('span.tree-checkbox').unbind().click(function(){
                return false;
            });
            $('#btn-edit').linkbutton('enable');
        }
    });
}

function btnQuery_Click(){
	openWindow('window-query');
    $('#window-query').window('center');
}

function btnSerach_Click(){
	if ($('#form-query').form('validate') == true) {//标签是否验证通过 
		queryParam = getObjectFromForm($('#form-query'));
		$('#dataGrid').datagrid({url:'/jeemgr-web/tJeemgrUser/queryUserInfo.do', queryParams :queryParam});
		clearInputs('form-query');//清空控件值
		closeWindow('window-query');//关闭该查询窗体
	} else {
		return false;
	}
}
function serachCancel_Click(){
	closeWindow('window-query');//关闭该查询窗体
}
