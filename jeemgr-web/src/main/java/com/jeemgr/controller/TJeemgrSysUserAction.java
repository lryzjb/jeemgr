/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  TJeemgrSysUserAction.java
 * @Package com.jeemgr.control
 * @author: zhangjianbin
 * @date:   2018年11月19日 下午4:21:44
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */

package com.jeemgr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dovepay.jeemgr.dao.model.TJeemgrSysUser;
import com.jeemgr.base.BaseAction;
import com.jeemgr.base.SessionContext;
import com.jeemgr.service.TJeemgrSysMenuService;
import com.jeemgr.service.TJeemgrSysRoleService;
import com.jeemgr.service.TJeemgrSysUserRoleService;
import com.jeemgr.service.TJeemgrSysUserService;
import com.jeemgr.shiro.realm.LoginUserRealm;
import com.jeemgr.shiro.util.ShiroPasswordUtils;
import com.jeemgr.util.PageUtils;
/**
 * @ClassName:  TJeemgrSysUserAction
 * @Description:
 * @author: zhangjianbin
 * @date:   2018年11月20日 上午11:18:18
 * 
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
@Controller
@RequestMapping(value="tJeemgrUser")
public class TJeemgrSysUserAction extends BaseAction{
	
	@Autowired
	private TJeemgrSysUserService tJeemgrSysUserService;
	@Autowired
	private TJeemgrSysUserRoleService tJeemgrSysUserRoleService;
	@Autowired
	private TJeemgrSysRoleService tJeemgrSysRoleService;
	@Autowired
	private TJeemgrSysMenuService tJeemgrSysMenuService;
	
	/**
	 * @Title: login
	 * @Description:用户登录
	 * @author: zhangjianbin
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "userLogin", method = RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response,
            String userName, String password,String inputCode,String adcode){
		JSONObject jsonDate = new JSONObject();
		//存入session
		Subject userSubject = SecurityUtils.getSubject();
		HttpSession htsession = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		String code = (String) htsession.getAttribute("code");//图形验证码
		String strcode = exChange(inputCode);//手动输入验证码
		if (code!=null && code.length()>0) {
			adcode = code;
		}else{
			jsonDate.put("flag", false);
			jsonDate.put("message", "验证码异常");
			writeString(jsonDate.toString(),response);
			return null;
		}
		if (!code.equals(strcode)) {
			jsonDate.put("flag", false);
			jsonDate.put("message", "验证码输入不正确");
			writeString(jsonDate.toString(),response);
			return null;
		}
		Session shiroSession = userSubject.getSession();
		TJeemgrSysUser users = tJeemgrSysUserService.selectByUserName(userName);
		if(users==null){
			jsonDate.put("flag", false);
			jsonDate.put("message", "用户名不正确");
			writeString(jsonDate.toString(),response);
			return null;
		}else if(users.getStatus()==1){
			jsonDate.put("flag", false);
			jsonDate.put("message", "该用户被冻结");
			writeString(jsonDate.toString(),response);
			return null;
		}else{
			if(ShiroPasswordUtils.encryptPassword(password.trim()).equals(users.getPassword())){
				jsonDate.put("flag", true);
				jsonDate.put("mesg", "登录成功");
				UsernamePasswordToken token = new UsernamePasswordToken(userName,
						password);
				userSubject.login(token);
				shiroSession.setAttribute(USER_SESSION, users);
				writeString(jsonDate.toString(),response);
				return null;
			}else{
				jsonDate.put("flag", false);
				jsonDate.put("message", "密码不正确");
				writeString(jsonDate.toString(),response);
				return null;
			}
		}
	}
	
	/**
	 * @Title: openPage
	 * @Description:跳转到用户列表页面
	 * @author: zhangjianbin
	 * @return String
	 */
	@RequiresPermissions("system.user:view")
	@RequestMapping(value = "openPage")
	public String openPage() {
		return "system/userConfig";
	}
	
	/**
	 * @Title: selectAllUserInfo
	 * @Description:获取用户列表
	 * @author: zhangjianbin
	 * @return Map<String,Object>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequiresPermissions("system.user:view")
	@RequestMapping(value = "selectAllUserInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> selectAllUserInfo( String page, String rows) {
//		List<TJeemgrSysUser> userList=tJeemgrSysUserService.selectAllUserInfo();
//		JSONArray json=JSONArray.fromObject(userList);
//		JSONObject json2=new JSONObject();
//		json2.put("rows", userList);
//		json2.put("total", userList.size());
		PageUtils pageBean = new PageUtils(Integer.parseInt(page), Integer.parseInt(rows));
	    Map<String,Object> paraMap=new HashMap<String,Object>();
	    Map<String,Object> reMap=new HashMap<String,Object>();
	    paraMap.put("begins", pageBean.getPage()*pageBean.getRows()+1);
	    paraMap.put("ends", (pageBean.getPage()-1)*pageBean.getRows());
	    try {
	    	List<TJeemgrSysUser> userList=tJeemgrSysUserService.selectAllUserInfo(paraMap);
	    	paraMap.clear();
	    	long total=tJeemgrSysUserService.countByExample(paraMap);
	        reMap.put("rows", userList);     //存放每页记录数
	        reMap.put("total",total);   //存放总记录数
	    } catch (Exception e) {    
	        e.printStackTrace();
	    }
	     return reMap; 
	}
	
	/**
	 * @Title: logout
	 * @Description:登出
	 * @author: zhangjianbin
	 * @return String
	 */
	@RequestMapping(value="logout")
	public String logout(){
		JSONObject jsonDate = new JSONObject();
		try {
			//退出权限验证
			SecurityUtils.getSubject().logout();
			RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();
			LoginUserRealm realm = (LoginUserRealm)rsm.getRealms().iterator().next();
			realm.clearAuthz();
			//销毁session
//			session.invalidate(); 
			jsonDate.put("flag", true);
			writeString(jsonDate.toString(),response);
		} catch (Exception e) {
			jsonDate.put("flag", false);
			jsonDate.put("message", "退出异常");
			writeString(jsonDate.toString(),response);
		}
		return null;
		
	}
	
	/**
	 * @Title: loadRoleTreeForAddUser
	 * @Description:添加用户时加载所有角色
	 * @author: zhangjianbin
	 * @return List<Map<String, Object>>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "loadRoleTreeForAddUser")
	@ResponseBody
	public List<Map<String, Object>> loadRoleTreeForAddUser() {
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		treeNodeList=tJeemgrSysRoleService.loadRoleTreeForAddUser();
		return treeNodeList;
	}
	
	/**
	 * @Title: loadMenuTreeForUserConfig
	 * @Description:添加用户时根据角色加载对应权限
	 * @author: zhangjianbin
	 * @return List<Map<String, Object>>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="loadMenuTreeForUserConfig")
	@ResponseBody
	public List<Map<String, Object>> loadMenuTreeForUserConfig(String roleId) {
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		treeNodeList=tJeemgrSysMenuService.loadMenuTreeByRoleId(roleId);
		return treeNodeList;
	}
	
	/**
	 * @Title: loadRoleTreeForEditUser
	 * @Description:修改用户时加载出权限树
	 * @author: zhangjianbin
	 * @return List<Map<String, Object>>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="loadRoleTreeForEditUser")
	@ResponseBody
	public List<Map<String, Object>> loadRoleTreeForEditUser(String userId) {
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		treeNodeList= tJeemgrSysRoleService.loadRoleTreeForEditUser(userId);
		return treeNodeList;
	}
	
	/**
	 * @Title: addUser
	 * @Description:添加用户数据
	 * @author: zhangjianbin
	 * @return String
	 */
	@RequiresPermissions("system.user:add")
	@RequestMapping(value="addUser")
	public String addUser(TJeemgrSysUser sysUser) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		JSONObject jsonDate = new JSONObject();
		if(sysUser.getUsername().equals(rb.getString("sysAdminUser"))){
			jsonDate.put("flag", false);
			jsonDate.put("message", "该用户名已存在");
			writeString(jsonDate.toString(),response);
			return null;
		}
		TJeemgrSysUser user=null;
		user=tJeemgrSysUserService.selectByUserId(sysUser.getUsername());
		if(user==null){
			tJeemgrSysUserService.addUserInfo(sysUser);
			jsonDate.put("flag", true);
			jsonDate.put("message", "添加成功");
			writeString(jsonDate.toString(),response);
			return null;
		}else{
			jsonDate.put("flag", false);
			jsonDate.put("message", "该用户名已存在");
			writeString(jsonDate.toString(),response);
			return null;
		}
	}
	
	/**
	 * @Title: updateUser
	 * @Description:修改用户数据
	 * @author: zhangjianbin
	 * @return String
	 */
	@RequiresPermissions("system.user:edit")
	@RequestMapping(value="updateUser")
	public String updateUser(TJeemgrSysUser sysUser) {
		JSONObject jsonDate = new JSONObject();
		try {
			tJeemgrSysUserService.updateUser(sysUser);
			jsonDate.put("flag", true);
			jsonDate.put("message", "修改成功");
			writeString(jsonDate.toString(),response);
			return null;
		} catch (Exception e) {
			jsonDate.put("flag", false);
			jsonDate.put("message", "修改失败");
			writeString(jsonDate.toString(),response);
			return null;
		}
	}
	
	/**
	 * @Title: deleteUser
	 * @Description:删除用户数据
	 * @author: zhangjianbin
	 */
	@RequiresPermissions("system.user:delete")
	@RequestMapping(value="deleteUser")
	public void deleteUser(HttpServletRequest request,HttpServletResponse response,TJeemgrSysUser tJeemgrSysUser) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		try {
			if(tJeemgrSysUser.getUsername()==null || tJeemgrSysUser.getUsername().equals("")){
				writeString("1",response);//1表示userId不能为空
			}else if(tJeemgrSysUser.getUsername().equals(rb.getString("sysAdminUser"))){
				writeString("3",response);//3表示系统超级管理员不能删除
			}else{
				tJeemgrSysUserService.deleteUser(tJeemgrSysUser);
				writeString("0",response);//0表示删除成功
			}
		} catch (Exception e) {
			writeString("2",response);//2表示删除失败
		}
	}
	
	/**
	 * @Title: editPassword
	 * @Description:用户修改密码
	 * @author: zhangjianbin
	 */
	@RequestMapping(value="editPassword")
	public void editPassword(HttpServletRequest request,HttpServletResponse response,String userPassword,String userPassword1){
		try {
			TJeemgrSysUser user=tJeemgrSysUserService.selectByUserId(SessionContext.getCurrentUserId());
			if(user.getPassword().equals(ShiroPasswordUtils.encryptPassword(userPassword.trim()))){
				user.setPassword(ShiroPasswordUtils.encryptPassword(userPassword1.trim()));
				tJeemgrSysUserService.updateUserPassword(user);
				writeString("0",response);
			}else{
				writeString("1",response);
			}
		} catch (Exception e) {
			writeString("2",response);
		}
	}
	
//	/**
//	 * @Title: toSqlControl
//	 * @Description:跳转到sql监控页面
//	 * @author: zhangjianbin
//	 */
//	@RequiresPermissions("control.sql:view")
//	@RequestMapping(value="toSqlControl")
//	public String toSqlControl(){
//		return "druid/sql.html";
//	}
	
	
	/**
	 * @Title: queryUserInfo
	 * @Description:用户管理条件查询
	 * @author: zhangjianbin
	 * @param page
	 * @param rows
	 * @param username
	 * @return Map<String,Object>
	 */
	@RequiresPermissions("system.user:query")
	@RequestMapping(value = "queryUserInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> queryUserInfo( String page, String rows,String username) {
		PageUtils pageBean = new PageUtils(Integer.parseInt(page), Integer.parseInt(rows));
	    Map<String,Object> paraMap=new HashMap<String,Object>();
	    Map<String,Object> reMap=new HashMap<String,Object>();
	    paraMap.put("begins", pageBean.getPage()*pageBean.getRows()+1);
	    paraMap.put("ends", (pageBean.getPage()-1)*pageBean.getRows());
	    paraMap.put("username", username);
	    try {
	    	List<TJeemgrSysUser> userList=tJeemgrSysUserService.selectAllUserInfo(paraMap);
	    	paraMap.clear();
	    	paraMap.put("username", username);
	    	long total=tJeemgrSysUserService.countByExample(paraMap);
	        reMap.put("rows", userList);     //存放每页记录数
	        reMap.put("total", total);   //存放总记录数
	    } catch (Exception e) {    
	        e.printStackTrace();
	    }
	     return reMap; 
	}
}
