/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  TJeemgrSysRoleAction.java
 * @Package com.jeemgr.control
 * @author: 
 * @date:   2018年11月18日 上午10:21:20
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.transaction.annotation.Transactional;

import com.dovepay.jeemgr.dao.mapper.TJeemgrSysMenuMapper;
import com.dovepay.jeemgr.dao.mapper.TJeemgrSysRoleMenuMapper;
import com.dovepay.jeemgr.dao.model.TJeemgrSysMenu;
import com.dovepay.jeemgr.dao.model.TJeemgrSysMenuExample;
import com.dovepay.jeemgr.dao.model.TJeemgrSysMenuExample.Criteria;
import com.jeemgr.service.TJeemgrSysMenuService;
/**
 * 
 * @ClassName:  TJeemgrSysMenuServiceImpl
 * @Description:
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午4:53:37
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
@Transactional
public class TJeemgrSysMenuServiceImpl implements TJeemgrSysMenuService{
	
	private TJeemgrSysMenuMapper tJeemgrSysMenuMapper;
	private TJeemgrSysRoleMenuMapper tJeemgrSysRoleMenuMapper;
	
	/**
	 * <p><B>Title:</B> selectByUserId</p>
	 * <p><B>Description: </B></p>根据用户名获取所有权限
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param userId
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysMenuService#selectByUserId(java.lang.String)
	 */
	@Override
	public List<TJeemgrSysMenu> selectByUserId(String userId) {
		Map<String,Object> map=new HashMap<String,Object>();
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		String sysAdminUser=rb.getString("sysAdminUser");
		map.put("userId", userId);
//		if(!userId.equals(sysAdminUser)){
			map.put("ONLINE_SIGN", ONLINE_SIGN);
//		}
		List<TJeemgrSysMenu> list=tJeemgrSysMenuMapper.getMenuListByMap(map);
		return list;
	}

	/**
	 * <p><B>Title:</B> loadMenuTreeByRoleId</p>
	 * <p><B>Description: </B></p>根据权限ID加载菜单树
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param roleId
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysMenuService#loadMenuTreeByRoleId(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> loadMenuTreeByRoleId(String roleId) {
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
//		TJeemgrSysMenuExample example=new TJeemgrSysMenuExample();
//		Criteria criteria=example.createCriteria();
//		criteria.andOnlineSignEqualTo(ONLINE_SIGN);
		map.put("roleId", roleId);
		map.put("ONLINE_SIGN", ONLINE_SIGN);
		List<TJeemgrSysMenu> list=tJeemgrSysMenuMapper.loadMenuTreeByRoleId(map);
		for (TJeemgrSysMenu roleEntity : list) {
			if(roleEntity.getLevelNo()==1){
				Map<String, Object> treeNode = new HashMap<String, Object>();
				treeNode.put("id", roleEntity.getMenuId());
				treeNode.put("text", roleEntity.getName());
				findChildrenTreeNode(treeNode, list);
				treeNodeList.add(treeNode);
			}
			
		}
		return treeNodeList;
	}

	/**
	 * <p><B>Title:</B> loadMenuTreeForAddRole</p>
	 * <p><B>Description: </B></p>添加角色时加载所有权限
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysMenuService#loadMenuTreeForAddRole()
	 */
	@Override
	public List<Map<String, Object>> loadMenuTreeForAddRole() {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		TJeemgrSysMenuExample example=new TJeemgrSysMenuExample();
		Criteria criteria=example.createCriteria();
		criteria.andOnlineSignEqualTo(ONLINE_SIGN);
		example.setOrderByClause("LEVEL_NO,LEVEL_SEQ");
		List<TJeemgrSysMenu> menuList=tJeemgrSysMenuMapper.selectByExample(example);
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		for (TJeemgrSysMenu tJeemgrSysMenu : menuList) {
			if (tJeemgrSysMenu.getLevelNo() == 1) {
				Map<String, Object> treeNode = new LinkedHashMap<String, Object>();
				treeNode.put("id", tJeemgrSysMenu.getMenuId());
				treeNode.put("text", tJeemgrSysMenu.getName());
				findChildrenTreeNode(treeNode, menuList);
				treeNodeList.add(treeNode);
			}
		}
		return treeNodeList;
	}
	
	/**
	 * @Title: findChildrenTreeNode
	 * @Description:获取权限树子节点
	 * @author: zhangjianbin
	 * @param treeNode
	 * @param menuList void
	 */
	private void findChildrenTreeNode(Map<String, Object> treeNode,
			List<TJeemgrSysMenu> menuList) {
		List<Object> childTreeNodeList = new ArrayList<Object>();
		for (TJeemgrSysMenu tJeemgrSysMenu : menuList) {
			if (org.apache.commons.lang.StringUtils
					.equals(tJeemgrSysMenu.getParentId(), treeNode.get("id")
							.toString())) {
				Map<String, Object> childTreeNode = new LinkedHashMap<String, Object>();
				childTreeNode.put("id", tJeemgrSysMenu.getMenuId());
				childTreeNode.put("text", tJeemgrSysMenu.getName());
				childTreeNodeList.add(childTreeNode);
				findChildrenTreeNode(childTreeNode, menuList);
			}
		}
		if (childTreeNodeList.size() > 0) {
			treeNode.put("children", childTreeNodeList);
		}
	}

	/**
	 * <p><B>Title:</B> loadMenuTreeForEditRole</p>
	 * <p><B>Description: </B></p>修改角色时加载所有权限
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param roleId
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysMenuService#loadMenuTreeForEditRole(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> loadMenuTreeForEditRole(String roleId) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		TJeemgrSysMenuExample example=new TJeemgrSysMenuExample();
		Criteria criteria=example.createCriteria();
		criteria.andOnlineSignEqualTo(ONLINE_SIGN);
		example.setOrderByClause("LEVEL_NO,LEVEL_SEQ");
		List<TJeemgrSysMenu> menuList = tJeemgrSysMenuMapper.selectByExample(example);
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		for (TJeemgrSysMenu tJeemgrSysMenu : menuList) {
			if (tJeemgrSysMenu.getLevelNo() == 1) {
				Map<String, Object> treeNode = new LinkedHashMap<String, Object>();
				treeNode.put("id", tJeemgrSysMenu.getMenuId());
				treeNode.put("text", tJeemgrSysMenu.getName());
				findChildrenTreeNodeForEdit(treeNode, menuList,roleId,ONLINE_SIGN);
				if (!treeNode.containsKey("children")) {
					int num=tJeemgrSysRoleMenuMapper.isExistByRoleMenu(roleId, tJeemgrSysMenu.getMenuId(),ONLINE_SIGN);
					if (num>0) {
						treeNode.put("checked", true);
					} else {
						treeNode.put("checked", false);
					}
				}
				treeNodeList.add(treeNode);
			}
		}
		return treeNodeList;
	}
	
	/**
	 * @Title: findChildrenTreeNodeForEdit
	 * @Description:修改角色时获取所有权限树子节点
	 * @author: zhangjianbin
	 * @param treeNode
	 * @param menuList
	 * @param roleId
	 * @param ONLINE_SIGN void
	 */
	private void findChildrenTreeNodeForEdit(Map<String, Object> treeNode,
			List<TJeemgrSysMenu> menuList, String roleId,String ONLINE_SIGN) {
		List<Object> childTreeNodeList = new ArrayList<Object>();
		for (TJeemgrSysMenu tJeemgrSysMenu : menuList) {
			if (org.apache.commons.lang.StringUtils.equals(tJeemgrSysMenu
					.getParentId(), treeNode.get("id").toString())) {

//					// 测试代码
//					if ("671a2fa4-16b3-4463-8196-57b2c882ede2"
//							.equals(functionEntity.getId())) {
//						System.out.println("***");
//					}
				Map<String, Object> childTreeNode = new LinkedHashMap<String, Object>();
				childTreeNode.put("id", tJeemgrSysMenu.getMenuId());
				childTreeNode.put("text", tJeemgrSysMenu.getName());
				// if (StringUtils.isEmpty(roleId)) {
				// childTreeNode.put("checked", false);
				// } else {
				// if (authRelationDao.isExistByRoleFunction(roleId,
				// functionEntity.getId())) {
				// childTreeNode.put("checked", true);
				// } else {
				// childTreeNode.put("checked", false);
				// }
				// }
				childTreeNodeList.add(childTreeNode);
				findChildrenTreeNodeForEdit(childTreeNode,
						menuList, roleId,ONLINE_SIGN);
			}
		}
		if (childTreeNodeList.size() > 0) {
			treeNode.put("children", childTreeNodeList);
		} else {
			int num=tJeemgrSysRoleMenuMapper.isExistByRoleMenu(roleId,treeNode.get("id").toString(),ONLINE_SIGN);
			if (num>0) {
				treeNode.put("checked", true);
			} else {
				treeNode.put("checked", false);
			}
		}
	}

	public TJeemgrSysMenuMapper gettJeemgrSysMenuMapper() {
		return tJeemgrSysMenuMapper;
	}

	public void settJeemgrSysMenuMapper(TJeemgrSysMenuMapper tJeemgrSysMenuMapper) {
		this.tJeemgrSysMenuMapper = tJeemgrSysMenuMapper;
	}

	public TJeemgrSysRoleMenuMapper gettJeemgrSysRoleMenuMapper() {
		return tJeemgrSysRoleMenuMapper;
	}

	public void settJeemgrSysRoleMenuMapper(
			TJeemgrSysRoleMenuMapper tJeemgrSysRoleMenuMapper) {
		this.tJeemgrSysRoleMenuMapper = tJeemgrSysRoleMenuMapper;
	}
}
