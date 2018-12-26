$(function() {
	$('#treeMenu').tree({
		animate : true,
		onClick : function(node) {
			$(this).tree('toggle', node.target);
		}
	});
	$('#tabs').tabs({
		  onBeforeClose: function(title,index){
			$('#treeMenu').find('.tree-node-selected').removeClass('tree-node-selected');
		   // return true;  // 阻止关闭
		  }
		});
});

$(function() {
	$('#btnUserLogout').click(function() {
		$.messager.confirm("提示","确认退出系统?",function(r){
			if (r){
				 $.ajax({
		   				url : '/jeemgr-web/tJeemgrUser/logout.do',
		   				type:'post',
		   				dataType : 'json',
		   				success : function(r) {
		   					if(r.flag){
		   						window.location.href="/jeemgr-web/login.jsp";
		   					}else{
		   						showAlert(r.message);
		   					}
		   				}
		   			});
			}
		});
});
	
	

	
	
	
	
	
	// Change Password
	$('#btnChangePassword').click(function() {
		openWindow('window-changePassword');
		var uid = '${userId}';
		$("#uid").val(uid);
	});

});



function changePassword(){	
	var uid='${userId}';
	var password=$("#password").val();
	var newPassword=$("#newPassword").val();
	var confirmPassword=$("#confirmPassword").val();
	if(uid){
		
		if($('#form-changePassword').form('validate')){
			
			$.ajax({
				url : 'system/login_updatePassword.action',
				data : {
					uid:uid,
					password:password,
					newPassword:newPassword,
					confirmPassword:confirmPassword
				},
				type:'post',
				dataType : 'json',
				success : function(r) {
					if(r){
						if(r.success){
				        	  showInfo(r.message);
				     		   closeWindow('window-changePassword');
				        	  $('#list').datagrid('load');
				        	  $('#list').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
				          }else{
				          
				        	 showAlert(r.message);
				        	  $('#list').datagrid('load'); 
				        	  $('#list').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
				          }
						
					}else{
						 showAlert('System Exceptions');
					}
		       
				}
			});
			
		}else{
			showAlert('Data Validation Failed');
		}
		
		
	
	
	}else{
		showAlert('User ID Does Not Exist');
	}
		
}




$(function(){
	tabClose(); 
	tabCloseEven();
	
	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});

	var themes = {
		'metro-blue' : 'template/metro-blue/easyui.css',
		'metro-gray' : 'template/metro-gray/easyui.css',
		'cupertino' : 'template/cupertino/easyui.css'
	};

	var skins = $('.li-skinitem span').click(function() {
		var $this = $(this);
		if($this.hasClass('cs-skin-on')) return;
		skins.removeClass('cs-skin-on');
		$this.addClass('cs-skin-on');
		var skin = $this.attr('rel');
		$('#swicth-style').attr('href', themes[skin]);
		setCookie('cs-skin', skin);
		skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
	});

	if(getCookie('cs-skin')) {
		var skin = getCookie('cs-skin');
		$('#swicth-style').attr('href', themes[skin]);
		$this = $('.li-skinitem span[rel='+skin+']');
		$this.addClass('cs-skin-on');
		skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
	}
});
function setCookie(name,value) {//
    var Days = 30; 
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;
}


function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,fit:true,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe id="mainContent" name="mainContent" scrolling="auto" frameborder="0" allowtransparency="true" application="true" src="'+url+'" style="width:100%; height:100%; position: relative; margin-bottom:-6px"></iframe>';          
	return s;
}

function tabClose() {
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}
function tabCloseEven() {
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		});
	});
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	});
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			$('#tabs').tabs('close', t);
		});
	});
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});

	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});

}


$(function(){
$('#help').click(function() {
	$('#layout').layout('panel', 'east').panel('resize',{width:200});
	$('#layout').layout('resize');
});
$('.layout-button-right').click(function() {
	$('#layout').layout('panel', 'east').panel('resize',{width:4});
	$('#layout').layout('resize');
});
});

function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

function changeTheme(){
	var thems = $("#easyuiTheme").combobox("getValue");
	var url = $easyuiTheme.attr('href');
	var href = url.substr(0, url.indexOf('static')) + 'static/themes/' + thems + '/easyui.css';
	$easyuiTheme.attr('href',href);
	/* 如果使用了iframe  则要添加下面这段代码、否则的话iframe中的内容不会出现样式的改变*/
//	var $iframe = $('iframe');
//	if($ifram.length > 0){
//		for ( var i = 0; i < $iframe.length; i++) {
//			var ifr = $iframe[i];
//			$(ifr).contents.find('#easyuiTheme').attr('href', href);
//		}
//	}
	$.cookie('easyuiThemeName', thems, {
		expires : 7
	});
}