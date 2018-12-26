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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.shiro.SecurityUtils;
import org.springframework.transaction.annotation.Transactional;

import com.dovepay.jeemgr.dao.mapper.TJeemgrSysUserMapper;
import com.dovepay.jeemgr.dao.mapper.TJeemgrSysUserRoleMapper;
import com.dovepay.jeemgr.dao.model.TJeemgrSysUser;
import com.dovepay.jeemgr.dao.model.TJeemgrSysUserExample;
import com.dovepay.jeemgr.dao.model.TJeemgrSysUserExample.Criteria;
import com.dovepay.jeemgr.dao.model.TJeemgrSysUserRole;
import com.jeemgr.base.SessionContext;
import com.jeemgr.service.TJeemgrSysUserService;
import com.jeemgr.shiro.util.ShiroPasswordUtils;
import com.jeemgr.util.UUIDUtils;
/**
 * @ClassName:  TJeemgrSysUserServiceImpl
 * @Description:
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午5:18:02
 * 
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
@Transactional
public class TJeemgrSysUserServiceImpl implements TJeemgrSysUserService{
	
	private TJeemgrSysUserMapper tJeemgrSysUserMapper;
	private TJeemgrSysUserRoleMapper tJeemgrSysUserRoleMapper;
	
	public TJeemgrSysUserMapper gettJeemgrSysUserMapper() {
		return tJeemgrSysUserMapper;
	}
	public void settJeemgrSysUserMapper(TJeemgrSysUserMapper tJeemgrSysUserMapper) {
		this.tJeemgrSysUserMapper = tJeemgrSysUserMapper;
	}
	public TJeemgrSysUserRoleMapper gettJeemgrSysUserRoleMapper() {
		return tJeemgrSysUserRoleMapper;
	}
	public void settJeemgrSysUserRoleMapper(
			TJeemgrSysUserRoleMapper tJeemgrSysUserRoleMapper) {
		this.tJeemgrSysUserRoleMapper = tJeemgrSysUserRoleMapper;
	}
	/**
	 * <p><B>Title:</B> selectByUserId</p>
	 * <p><B>Description:根据用户名获取用户 </B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param userName
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysUserService#selectByUserId(java.lang.String)
	 */
	@Override
	public TJeemgrSysUser selectByUserId(String userName) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		String sysAdminUser=rb.getString("sysAdminUser");
		TJeemgrSysUserExample example = new TJeemgrSysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		if(!userName.trim().equals(sysAdminUser)){
			criteria.andOnlineSignEqualTo(ONLINE_SIGN);
		}
		List<TJeemgrSysUser> list = tJeemgrSysUserMapper.selectByExample(example);
		if (list!=null && list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * <p><B>Title:</B> selectByUserName</p>
	 * <p><B>Description: 根据用户名获取用户</B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param userName
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysUserService#selectByUserName(java.lang.String)
	 */
	@Override
	public TJeemgrSysUser selectByUserName(String userName) {
		TJeemgrSysUser tJeemgrSysUser=selectByUserId(userName);
		return tJeemgrSysUser;
	}
	
	/**
	 * <p><B>Title:</B> selectAllUserInfo</p>
	 * <p><B>Description: 获取所有用户列表分页</B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param paraMap
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysUserService#selectAllUserInfo(java.util.Map)
	 */
	public List<TJeemgrSysUser> selectAllUserInfo(Map<String,Object> paraMap){
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		paraMap.put("ONLINE_SIGN", ONLINE_SIGN);
		List<TJeemgrSysUser> list = tJeemgrSysUserMapper.selectAllInfoByPage(paraMap);
		return list;
	}
	
	/**
	 * <p><B>Title:</B> addUserInfo</p>
	 * <p><B>Description: 添加用户</B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param sysUser
	 * @see com.jeemgr.service.TJeemgrSysUserService#addUserInfo(com.dovepay.jeemgr.dao.model.TJeemgrSysUser)
	 */
	@Override
	public void addUserInfo(TJeemgrSysUser sysUser) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		sysUser.setPassword(ShiroPasswordUtils
				.encryptPassword(sysUser.getPassword()));
		sysUser.setCreateTime(new Date());
		sysUser.setUpdateTime(new Date());
		if(SecurityUtils.getSubject().getSession().getAttribute("USER_SESSION")!=null){
			TJeemgrSysUser user=(TJeemgrSysUser) SecurityUtils.getSubject().getSession().getAttribute("USER_SESSION");
			sysUser.setCreateId(user.getUsername());
			sysUser.setUpdateId(user.getUsername());
		}
		sysUser.setOnlineSign(ONLINE_SIGN);
		sysUser.setUserId(UUIDUtils.uuidReplace());
		tJeemgrSysUserMapper.insertSelective(sysUser);
//		String userId = SessionContext.getCurrentUserId();
		if(sysUser.getRoleIdList()!=null && sysUser.getRoleIdList().size()>0){
			for (String roleId : sysUser.getRoleIdList()) {
				TJeemgrSysUserRole tJeemgrSysUserRole=new TJeemgrSysUserRole();
				tJeemgrSysUserRole.setId(UUIDUtils.uuidReplace());
				tJeemgrSysUserRole.setUserId(sysUser.getUsername());
				tJeemgrSysUserRole.setRoleId(roleId);
				tJeemgrSysUserRole.setOnlineSign(ONLINE_SIGN);
				tJeemgrSysUserRoleMapper.insertSelective(tJeemgrSysUserRole);
			}
		}
	}

	/**
	 * <p><B>Title:</B> updateUser</p>
	 * <p><B>Description:修改用户 </B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param sysUser
	 * @see com.jeemgr.service.TJeemgrSysUserService#updateUser(com.dovepay.jeemgr.dao.model.TJeemgrSysUser)
	 */
	@Override
	public void updateUser(TJeemgrSysUser sysUser) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		sysUser.setUpdateId(SessionContext.getCurrentUserId());
		sysUser.setUpdateTime(new Date());
		tJeemgrSysUserMapper.updateByPrimaryKeySelective(sysUser);
		if(sysUser.getUsername().equals(rb.getString("sysAdminUser"))){
			ONLINE_SIGN=null;
		}
		tJeemgrSysUserRoleMapper.deleteUserRoleByUserName(sysUser.getUsername(),ONLINE_SIGN);
		if(sysUser.getRoleIdList()!=null && sysUser.getRoleIdList().size()>0){
			for (String roleId : sysUser.getRoleIdList()) {
				TJeemgrSysUserRole tJeemgrSysUserRole = new TJeemgrSysUserRole();
				tJeemgrSysUserRole.setId(UUIDUtils.uuidReplace());
				tJeemgrSysUserRole.setUserId(sysUser.getUsername());
				tJeemgrSysUserRole.setRoleId(roleId);
				tJeemgrSysUserRole.setOnlineSign(ONLINE_SIGN);
				tJeemgrSysUserRoleMapper.insertSelective(tJeemgrSysUserRole);
			}
		}
	}

	/**
	 * <p><B>Title:</B> deleteUser</p>
	 * <p><B>Description:删除用户 </B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param tJeemgrSysUser
	 * @see com.jeemgr.service.TJeemgrSysUserService#deleteUser(com.dovepay.jeemgr.dao.model.TJeemgrSysUser)
	 */
	@Override
	public void deleteUser(TJeemgrSysUser tJeemgrSysUser) {
		ResourceBundle rb = ResourceBundle.getBundle("config/source");
		String ONLINE_SIGN=rb.getString("ONLINE_SIGN");
		tJeemgrSysUserMapper.deleteUserByIdAndType(tJeemgrSysUser.getUsername(),ONLINE_SIGN);
		tJeemgrSysUserRoleMapper.deleteUserRoleByUserName(tJeemgrSysUser.getUsername(),ONLINE_SIGN);
	}
	
	/**
	 * <p><B>Title:</B> updateUserPassword</p>
	 * <p><B>Description:修改密码 </B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param user
	 * @see com.jeemgr.service.TJeemgrSysUserService#updateUserPassword(com.dovepay.jeemgr.dao.model.TJeemgrSysUser)
	 */
	@Override
	public void updateUserPassword(TJeemgrSysUser user) {
		tJeemgrSysUserMapper.updateByPrimaryKeySelective(user);
	}
	
	/**
	 * <p><B>Title:</B> countByExample</p>
	 * <p><B>Description: 角色管理条件查询</B></p>
	 * <p><B>Author:</B> zhangjianbin</p>
	 * @param paraMap
	 * @return
	 * @see com.jeemgr.service.TJeemgrSysUserService#countByExample(java.util.Map)
	 */
	@Override
	public long countByExample(Map<String, Object> paraMap) {
		TJeemgrSysUserExample tJeemgrSysUserExample=new TJeemgrSysUserExample();
		Criteria criteria = tJeemgrSysUserExample.createCriteria();
		if(paraMap.get("username")!=null){
			criteria.andUsernameEqualTo(paraMap.get("username").toString());
		}
		int count=tJeemgrSysUserMapper.countByExample(tJeemgrSysUserExample);
		return (long)count;
	}
}
