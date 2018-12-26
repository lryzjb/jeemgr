/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  TJeemgrSysRoleAction.java
 * @Package com.jeemgr.control
 * @author: zhangjianbin
 * @date:   2018年11月18日 上午10:21:20
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.transaction.annotation.Transactional;

import com.dovepay.jeemgr.dao.mapper.TJeemgrSysRoleMapper;
import com.dovepay.jeemgr.dao.mapper.TJeemgrSysRoleMenuMapper;
import com.dovepay.jeemgr.dao.mapper.TJeemgrSysUserRoleMapper;
import com.dovepay.jeemgr.dao.model.TJeemgrSysRole;
import com.dovepay.jeemgr.dao.model.TJeemgrSysRoleExample;
import com.dovepay.jeemgr.dao.model.TJeemgrSysRoleExample.Criteria;
import com.dovepay.jeemgr.dao.model.TJeemgrSysRoleMenu;
import com.jeemgr.base.SessionContext;
import com.jeemgr.service.TJeemgrSysRoleService;
import com.jeemgr.util.UUIDUtils;
/**
 * @ClassName:  TJeemgrSysRoleServiceImpl
 * @Description:
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午5:09:37
 * 
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
@Transactional
public class TJeemgrSysRoleServiceImpl implements TJeemgrSysRoleService{

	private TJeemgrSysRoleMapper tJeemgrSysRoleMapper;
	private TJeemgrSysRoleMenuMapper tJeemgrSysRoleMenuMapper;
	private TJeemgrSysUserRoleMapper tJeemgrSysUserRoleMapper;
	public TJeemgrSysRoleMapper gettJeemgrSysRoleMapper() {
		return tJeemgrSysRoleMapper;
	}
	public TJeemgrSysRoleMenuMapper gettJeemgrSysRoleMenuMapper() {
		return tJeemgrSysRoleMenuMapper;
	}
	public TJeemgrSysUserRoleMapper gettJeemgrSysUserRoleMapper() {
		return tJeemgrSysUserRoleMapper;
	}
	public void settJeemgrSysRoleMapper(TJeemgrSysRoleMapper tJeemgrSysRoleMapper) {
		this.tJeemgrSysRoleMapper = tJeemgrSysRoleMapper;
	}
	public void settJeemgrSysRoleMenuMapper(
			TJeemgrSysRoleMenuMapper tJeemgrSysRoleMenuMapper) {
		this.tJeemgrSysRoleMenuMapper = tJeemgrSysRoleMenuMapper;
	}
	public void settJeemgrSysUserRoleMapper(
			TJeemgrSysUserRoleMapper tJeemgrSysUserRoleMapper) {
		this.tJeemgrSysUserRoleMapper = tJeemgrSysUserRoleMapper;
	}
	
	/**
	 * <p><B>Title:</B> selectRoleInPage</p>
	 * <p><B>Description:获取所有角色列表分页 </B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param paraMap
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysRoleService#selectRoleInPage(java.util.Map)
	 */
	@Override
	public List<TJeemgrSysRole> selectRoleInPage(Map<String, Object> paraMap){
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		paraMap.put("ONLINE_SIGN", ONLINE_SIGN);
		List<TJeemgrSysRole> roleEntityList = tJeemgrSysRoleMapper.selectRoleInPage(paraMap);
		return roleEntityList;
	}
	
	/**
	 * <p><B>Title:</B> getRoleListByUserId</p>
	 * <p><B>Description: 根据用户名获取拥有角色</B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param userId
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysRoleService#getRoleListByUserId(java.lang.String)
	 */
	@Override
	public List<TJeemgrSysRole> getRoleListByUserId(String userId) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("ONLINE_SIGN", ONLINE_SIGN);
		List<TJeemgrSysRole> list=tJeemgrSysRoleMapper.selectRoleListByUserId(map);
		return list;
	}
	
	/**
	 * <p><B>Title:</B> loadRoleTreeForAddUser</p>
	 * <p><B>Description: 添加用户时加载所有角色树</B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysRoleService#loadRoleTreeForAddUser()
	 */
	@Override
	public List<Map<String, Object>> loadRoleTreeForAddUser() {
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		TJeemgrSysRoleExample example=new TJeemgrSysRoleExample();
		Criteria criteria=example.createCriteria();
		criteria.andOnlineSignEqualTo(ONLINE_SIGN);
		List<TJeemgrSysRole> list=tJeemgrSysRoleMapper.selectByExample(example);
		for (TJeemgrSysRole roleEntity : list) {
			Map<String, Object> treeNode = new HashMap<String, Object>();
			treeNode.put("id", roleEntity.getRoleId());
			treeNode.put("text", roleEntity.getRoleName());
			treeNodeList.add(treeNode);
		}
		return treeNodeList;
	}

	/**
	 * <p><B>Title:</B> loadRoleTreeForEditUser</p>
	 * <p><B>Description:修改用户时根据用户名加载所有角色树 </B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param userId
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysRoleService#loadRoleTreeForEditUser(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> loadRoleTreeForEditUser(String userId) {
		Map<String,Object> map=new HashMap<String,Object>();
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		TJeemgrSysRoleExample roleExample = new TJeemgrSysRoleExample();
		Criteria criteria=roleExample.createCriteria();
		criteria.andOnlineSignEqualTo(ONLINE_SIGN);
		List<TJeemgrSysRole> roleEntityList = tJeemgrSysRoleMapper.selectByExample(roleExample);
		map.put("userId", userId);
		map.put("ONLINE_SIGN", ONLINE_SIGN);
		List<TJeemgrSysRole> selectedRoleEntityList = tJeemgrSysRoleMapper.selectRoleListByUserId(map);
		List<Map<String, Object>> treeNodeList = new ArrayList<Map<String, Object>>();
		for (TJeemgrSysRole roleEntity : roleEntityList) {
			Map<String, Object> treeNode = new HashMap<String, Object>();
			treeNode.put("id", roleEntity.getRoleId());
			treeNode.put("text", roleEntity.getRoleName());
			boolean isSelected = false;
			for (TJeemgrSysRole selectedRoleEntity : selectedRoleEntityList) {
				if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(
						roleEntity.getRoleId(),
						selectedRoleEntity.getRoleId())) {
					isSelected = true;
					break;
				}
			}
			if (isSelected) {
				treeNode.put("checked", true);
			} else {
				treeNode.put("checked", false);
			}
			treeNodeList.add(treeNode);
		}
		return treeNodeList;
	}

	/**
	 * <p><B>Title:</B> addRoleInfo</p>
	 * <p><B>Description: 添加角色信息</B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param role
	 * @see com.jeemgr.service.TJeemgrSysRoleService#addRoleInfo(com.dovepay.jeemgr.dao.model.TJeemgrSysRole)
	 */
	@Override
	public void addRoleInfo(TJeemgrSysRole role) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		role.setRoleId(UUIDUtils.uuidReplace());
		role.setCreatId(SessionContext.getCurrentUserId());
		role.setCreateTime(new Date());
		role.setOnlineSign(ONLINE_SIGN);
		role.setUpdateId(SessionContext.getCurrentUserId());
		role.setUpdateTime(new Date());
		int num=tJeemgrSysRoleMapper.insertSelective(role);
		if (role.getFunctionIdList()!=null && role.getFunctionIdList().size()>0) {
			for (String menuId : role.getFunctionIdList()) {
				TJeemgrSysRoleMenu tJeemgrSysRoleMenu=new TJeemgrSysRoleMenu();
				tJeemgrSysRoleMenu.setId(UUIDUtils.uuidReplace());
				tJeemgrSysRoleMenu.setMenuId(menuId);
				tJeemgrSysRoleMenu.setRoleId(role.getRoleId());
				tJeemgrSysRoleMenu.setOnlineSign(ONLINE_SIGN);
				tJeemgrSysRoleMenuMapper.insertSelective(tJeemgrSysRoleMenu);
			}
		}
	}

	/**
	 * <p><B>Title:</B> deleteRole</p>
	 * <p><B>Description:根据角色ID删除角色 </B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param role
	 * @see com.jeemgr.service.TJeemgrSysRoleService#deleteRole(com.dovepay.jeemgr.dao.model.TJeemgrSysRole)
	 */
	@Override
	public void deleteRole(TJeemgrSysRole role) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		tJeemgrSysRoleMapper.deleteByIdAndType(role.getRoleId(),ONLINE_SIGN);
		tJeemgrSysUserRoleMapper.deleteByRoleIdAndType(role.getRoleId(),ONLINE_SIGN);
		tJeemgrSysRoleMenuMapper.deleteByRoleIdAndType(role.getRoleId(),ONLINE_SIGN);
	}

	/**
	 * <p><B>Title:</B> updateRole</p>
	 * <p><B>Description: 修改角色</B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param role
	 * @see com.jeemgr.service.TJeemgrSysRoleService#updateRole(com.dovepay.jeemgr.dao.model.TJeemgrSysRole)
	 */
	@Override
	public void updateRole(TJeemgrSysRole role) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		role.setOnlineSign(ONLINE_SIGN);
		role.setUpdateId(SessionContext.getCurrentUserId());
		role.setUpdateTime(new Date());
		tJeemgrSysRoleMapper.updateByPrimaryKeySelective(role);
		tJeemgrSysRoleMenuMapper.deleteByRoleIdAndType(role.getRoleId(),ONLINE_SIGN);
		if (role.getFunctionIdList()!=null && role.getFunctionIdList().size()>0) {
			for (String menuId : role.getFunctionIdList()) {
				TJeemgrSysRoleMenu tJeemgrSysRoleMenu=new TJeemgrSysRoleMenu();
				tJeemgrSysRoleMenu.setId(UUIDUtils.uuidReplace());
				tJeemgrSysRoleMenu.setRoleId(role.getRoleId());
				tJeemgrSysRoleMenu.setMenuId(menuId);
				tJeemgrSysRoleMenu.setOnlineSign(ONLINE_SIGN);
				tJeemgrSysRoleMenuMapper.insertSelective(tJeemgrSysRoleMenu);
			}
		}
	}
	
	/**
	 * <p><B>Title:</B> selectCountByExample</p>
	 * <p><B>Description:条件查询 </B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param paraMap
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysRoleService#selectCountByExample(java.util.Map)
	 */
	@Override
	public long selectCountByExample(Map<String, Object> paraMap) {
		TJeemgrSysRoleExample tJeemgrSysRoleExample=new TJeemgrSysRoleExample();
		Criteria criteria=tJeemgrSysRoleExample.createCriteria();
		if(paraMap.get("roleName")!=null){
			criteria.andRoleNameEqualTo(paraMap.get("roleName").toString());
		}
		int count=tJeemgrSysRoleMapper.countByExample(tJeemgrSysRoleExample);
		return (long)count;
	}
}
