/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  TJeemgrSysRoleAction.java
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

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dovepay.jeemgr.dao.model.TJeemgrSysRole;
import com.dovepay.jeemgr.dao.model.TJeemgrSysUser;
import com.jeemgr.base.BaseAction;
import com.jeemgr.service.TJeemgrSysMenuService;
import com.jeemgr.service.TJeemgrSysRoleService;
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
@RequestMapping(value="tJeemgrRole")
public class TJeemgrSysRoleAction extends BaseAction{
	
	@Autowired
	private TJeemgrSysRoleService tJeemgrSysRoleService;
	@Autowired
	private TJeemgrSysMenuService tJeemgrSysMenuService;
	
	/**
	 * @Title: openPage
	 * @Description:跳转到权限列表页面
	 * @author: zhangjianbin
	 * @return String
	 */
	@RequiresPermissions("system.role:view")
	@RequestMapping(value="openPage")
	public String openPage() {
		return "system/roleConfig";
	}
	
	/**
	 * @Title: selectRoleInPage
	 * @Description:获取权限列表
	 * @author: zhangjianbin
	 * @return Map<String,Object>
	 */
	@RequiresPermissions("system.role:view")
	@RequestMapping(value="selectRoleInPage")
	@ResponseBody
	public Map<String,Object> selectRoleInPage(String page, String rows) {
		PageUtils pageBean = new PageUtils(Integer.parseInt(page), Integer.parseInt(rows));
	    Map<String,Object> paraMap=new HashMap<String,Object>();
	    Map<String,Object> reMap=new HashMap<String,Object>();
	    paraMap.put("begins", pageBean.getPage()*pageBean.getRows()+1);
	    paraMap.put("ends", (pageBean.getPage()-1)*pageBean.getRows());
	    try {
	    	List<TJeemgrSysRole> userList=tJeemgrSysRoleService.selectRoleInPage(paraMap);
	    	paraMap.clear();
	    	long total=tJeemgrSysRoleService.selectCountByExample(paraMap);
	        reMap.put("rows", userList);     //存放每页记录数
	        reMap.put("total", total);   //存放总记录数
	    } catch (Exception e) {    
	        e.printStackTrace();
	    }
	     return reMap; 
	}
	
	/**
	 * @Title: loadMenuTreeForAddRole
	 * @Description:添加角色时加载所有权限
	 * @author: zhangjianbin
	 * @return Map<String,Object>
	 */
	@RequestMapping(value="loadMenuTreeForAddRole")
	@ResponseBody
	public List<Map<String, Object>> loadMenuTreeForAddRole() {
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		treeNodeList=tJeemgrSysMenuService.loadMenuTreeForAddRole();
		return treeNodeList;
	}
	
	/**
	 * @Title: loadMenuTreeForEditRole
	 * @Description:修改角色时获取权限列表
	 * @author: zhangjianbin
	 * @return Map<String,Object>
	 */
	@RequestMapping(value="loadMenuTreeForEditRole")
	@ResponseBody
	public List<Map<String, Object>> loadMenuTreeForEditRole(String roleId) {
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		treeNodeList= tJeemgrSysMenuService.loadMenuTreeForEditRole(roleId);
		return treeNodeList;
	}
	
	/**
	 * @Title: addRole
	 * @Description:添加角色
	 * @author: zhangjianbin
	 * @return String
	 */
	@RequiresPermissions("system.role:add")
	@RequestMapping(value="addRole")
	public String addRole(TJeemgrSysRole role) {
		JSONObject jsonDate = new JSONObject();
		tJeemgrSysRoleService.addRoleInfo(role);
		jsonDate.put("flag", true);
		jsonDate.put("message", "添加成功");
		writeString(jsonDate.toString(),response);
		return null;
	}
	
	/**
	 * @Title: deleteRole
	 * @Description:删除角色
	 * @author: zhangjianbin
	 * @return String
	 */
	@RequiresPermissions("system.role:delete")
	@RequestMapping(value="deleteRole")
	public String deleteRole(TJeemgrSysRole role) {
		JSONObject jsonDate = new JSONObject();
		tJeemgrSysRoleService.deleteRole(role);
		jsonDate.put("flag", true);
		jsonDate.put("message", "删除成功");
		writeString(jsonDate.toString(),response);
		return null;
	}
	
	/**
	 * @Title: updateRole
	 * @Description:修改角色
	 * @author: zhangjianbin
	 * @return String
	 */
	@RequiresPermissions("system.role:edit")
	@RequestMapping(value="updateRole")
	public String updateRole(TJeemgrSysRole role) {
		JSONObject jsonDate = new JSONObject();
		tJeemgrSysRoleService.updateRole(role);
		jsonDate.put("flag", true);
		jsonDate.put("message", "修改成功");
		writeString(jsonDate.toString(),response);
		return null;
	}
	
	/**
	 * @Title: queryRoleInfo
	 * @Description:条件查询
	 * @author: zhangjianbin
	 * @param page
	 * @param rows
	 * @param roleName
	 * @return Map<String,Object>
	 */
	@RequiresPermissions("system.role:query")
	@RequestMapping(value = "queryRoleInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> queryRoleInfo( String page, String rows,String roleName) {
		PageUtils pageBean = new PageUtils(Integer.parseInt(page), Integer.parseInt(rows));
	    Map<String,Object> paraMap=new HashMap<String,Object>();
	    Map<String,Object> reMap=new HashMap<String,Object>();
	    paraMap.put("begins", pageBean.getPage()*pageBean.getRows()+1);
	    paraMap.put("ends", (pageBean.getPage()-1)*pageBean.getRows());
	    paraMap.put("roleName", roleName);
	    try {
	    	List<TJeemgrSysRole> userList=tJeemgrSysRoleService.selectRoleInPage(paraMap);
	    	paraMap.clear();
	    	paraMap.put("roleName", roleName);
	    	long total=tJeemgrSysRoleService.selectCountByExample(paraMap);
	        reMap.put("rows", userList);     //存放每页记录数
	        reMap.put("total", total);   //存放总记录数
	    } catch (Exception e) {    
	        e.printStackTrace();
	    }
	     return reMap; 
	}
}
