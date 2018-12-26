var selectedEditId;

$(document).ready(
	function(){ 
	    
		dataGrid_LoadData();
	}
);

function dataGrid_LoadData(){
	$('#dataGrid').datagrid({   
	    url: '/jeemgr-web/tJeemgrRole/selectRoleInPage.do',  //查询全部列表
	    idField: "id",
	    sortName : 'id',
		sortOrder : 'asc',
	    rownumbers: true,
	    method:'post',
	    toolbar: '#toolbar',
	    singleSelect:true,
	    pagination:true,
	    fit:true,
	    columns:[[   
	              	{title:'角色ID',field:'roleId',width:250,align:'left',halign:'center',sortable:true,hidden:true},
	              	{title:'角色名称',field:'roleName',width:250,align:'left',halign:'center',sortable:true},
	                {title:'创建时间',field:'createTime',width:150,align:'left',halign:'center',sortable:true,
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
	                {title:'创建人',field:'creatId',width:150,align:'left',halign:'center',sortable:true},
	            ]]
	 });
}

function btnAdd_Click(){
	
	loadMenuTreeForAddRole();
	
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
	   	showMessage("Please Select One Data");
       return false;
    }
    
	var rowData = getSelected(dg);

	loadMenuTreeForEditRole(rowData.roleId);
    //$('#authFpmTree').tree({url:null,checkbox:false,data:[{text:'点击左边角色,这里会显示权限信息'}]});
    //显示窗口
    openWindow('window-edit');
    $('#window-edit').window('center');
    $("#form-edit").form("load", rowData);
    
    selectedEditId = rowData.id;
}

function btnDelete_Click(){
	
	var dg = $('#dataGrid'); //得到list
    //判断是否选中了一样进行编辑
    if(!isSingleSelected(dg)){
	   	showMessage("Please Select One Data");
       return false;
    }
    var rowData = getSelected(dg);
    
    showConfirm('提示', '确定删除该角色?', function(r){
    	if (r) {
   		 
   		 $.ajax({
   				url : '/jeemgr-web/tJeemgrRole/deleteRole.do',
   				data : {roleId:rowData.roleId,},
   				type:'post',
   				dataType : 'json',
   				success : function(r) {
   					
   					if(r){
   						
   						if(r.flag){
   				        	  showMessage(r.message);
   				        	  $('#dataGrid').datagrid('load');
   				        	  $('#dataGrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
   				          }else{
   				        	  showAlert(r.message);
   				        	  $('#dataGrid').datagrid('load'); 
   				        	  $('#dataGrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
   				          }
   						
   					}
   					else{
   						showAlert("System Exceptions");
   					}
   		       
   				}
   			});
    	}
    });
}

function loadMenuTreeForAddRole(){
    $('#addFunctionTree').tree({
        checkbox:true,
        cache:false,
        data:[{text:'Character Infomation Is Loading....'}],
        url:'/jeemgr-web/tJeemgrRole/loadMenuTreeForAddRole.do',
        onLoadSuccess: function(node,data){
            
        },
        onClick: function(node){
            
            //loadAddMenuTreeByRoleId(node.text);
        }
    });
}

function loadMenuTreeForEditRole(roleId){
    $('#editFunctionTree').tree({
        checkbox:true,
        cache:false,
        data:[{text: 'Authority Infomation Of "'+roleId+'" Is Loading.....'}],
        url:'/jeemgr-web/tJeemgrRole/loadMenuTreeForEditRole.do?roleId='+roleId,
        onLoadSuccess: function(node,data){
//        	$(this).find('span.tree-checkbox').unbind().click(function(){
//                return false;
//            });
//            $('#btn-edit').linkbutton('enable');
        },
        onClick: function(node){
            
            //loadAddMenuTreeByRoleId(node.text);
        }
    });
}

/**
 * 添加保存操作
 */
function btnAddSave_Click(){
	var checkedNodeList=$("#addFunctionTree").tree("getChecked", ["checked","indeterminate"]);
	var functionIdList=[];
	if(checkedNodeList&&checkedNodeList.length>0){
		$.each(checkedNodeList, function(i, val) {
			
			functionIdList[i]=val.id;
		});
	}
	
	var params=getObjectFromForm($('#form-add'));
	params.functionIdList = functionIdList;
	
	if($('#form-add').form('validate')){
		
		$.ajax({
			url : '/jeemgr-web/tJeemgrRole/addRole.do',
			data : $.param(params, true),
			type:'post',
			dataType : 'json',
			success : function(r) {
				if(r){
					
					if(r.flag){
						  closeWindow('window-add');
						  clearInputs('form-add');
						  showMessage(r.message);
			        	  $('#dataGrid').datagrid('load');
			        	  $('#dataGrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
			          }else{
			        	  showAlert(r.message);
			        	  $('#dataGrid').datagrid('load'); 
			        	  $('#dataGrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
			          }
					
				}
				else{
					showAlert('System Exceptions');
				}
	       
			}
		});
		
	}
	else{
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
	var row=$('#dataGrid').datagrid('getSelected');
//	var uid = $("#uid").val();
//	var password = $("#password").val();
//	var username = $("#username").val();
//	var phone = $("#phone").val();
//	var email = $("#email").val();
//	var remark = $("#remark").val();
//	var isAccountNonLocked =$('#isAccountNonLocked').combobox('getValue');
	var editFunctionTree=$("#editFunctionTree").tree("getChecked",["checked","indeterminate"]);
	var functionIdList=[];
	if(editFunctionTree&&editFunctionTree.length>0){
		$.each(editFunctionTree, function(i, val) {
			//console.info(val.id);
			functionIdList[i]=val.id;
		});
	}
	
	var params=getObjectFromForm($('#form-edit'));
	params.id = selectedEditId;
	params.functionIdList = functionIdList;
	
	if($('#form-edit').form('validate')){
		
		$.ajax({
			url : '/jeemgr-web/tJeemgrRole/updateRole.do',
			data : $.param(params, true),
			type:'post',
			dataType : 'json',
			success : function(r) {
				if(r){
					if(r.flag){
						  closeWindow('window-edit');
						  clearInputs('form-edit');
						  showMessage(r.message);
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

//编辑窗口取消
function btnEditCancel_Click(){
	
	closeWindow('window-edit');
}



function btnQuery_Click(){
	openWindow('window-query');
    $('#window-query').window('center');
}
function btnSerach_Click(){
	if ($('#form-query').form('validate') == true) {//标签是否验证通过 
		queryParam = getObjectFromForm($('#form-query'));
		$('#dataGrid').datagrid({url:'/jeemgr-web/tJeemgrRole/queryRoleInfo.do', queryParams :queryParam});
		clearInputs('form-query');//清空控件值
		closeWindow('window-query');//关闭该查询窗体
	} else {
		return false;
	}
}
function serachCancel_Click(){
	closeWindow('window-query');//关闭该查询窗体
}