/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  LoginUserRealm.java
 * @Package com.jeemgr.shiro.realm
 * @author: zhangjianbin
 * @date:   2018年11月16日 下午2:21:44
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.shiro.realm;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dovepay.jeemgr.dao.model.TJeemgrSysMenu;
import com.dovepay.jeemgr.dao.model.TJeemgrSysUser;
import com.jeemgr.service.TJeemgrSysMenuService;
import com.jeemgr.service.TJeemgrSysUserService;

/**
 * @ClassName:  LoginUserRealm
 * @Description:shiro用户登录自定义安全数据源realm
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午4:40:47
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public class LoginUserRealm extends AuthorizingRealm{
	public final static String REALM_NAME = "LoginCasRealm";

	public LoginUserRealm() {
		setName(REALM_NAME); // This name must match the name in the User

	}
	@Autowired
	private TJeemgrSysUserService tJeemgrSysUserService;

	@Autowired
	private TJeemgrSysMenuService tJeemgrSysMenuService;
	
	
	 public LoginUserRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
	        super(cacheManager, matcher);
	 }

	
	/**
	 * <p><B>Title:</B> doGetAuthenticationInfo</p>
	 * <p><B>Description: </B></p>用户认证
	 * <p><B>Author:</B> yangliehui yangliehui@163.com</p>
	 * @param authcToken
	 * @return
	 * @throws AuthenticationException
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();

		try {
			if (userName==null || userName.trim().equals("")) {
				throw new AccountException("can not handle this login");
			}
			TJeemgrSysUser userEntity = null;
			try {
				userEntity = tJeemgrSysUserService.selectByUserId(userName);
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
			//账号不存在
			if (userEntity == null) {
				return null;
			}
			//账号已停用
//			if(userEntity.getStatus()==1){
//				throw new AccountException("该账户已停用");
//			}
			String realmName = getName();
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
					userEntity.getUsername(), userEntity.getPassword(),
					realmName);
			return simpleAuthenticationInfo;
		} catch (Exception e) {
			throw translateAuthenticationException(e);
		}
	}

	/**
	 * <p><B>Title:</B> doGetAuthorizationInfo</p>
	 * <p><B>Description: </B></p>用户授权
	 * <p><B>Author:</B> yangliehui yangliehui@163.com</p>
	 * @param principals
	 * @return
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		Subject userSubject = SecurityUtils.getSubject();
		if (userSubject.isAuthenticated()) {
			SimpleAuthorizationInfo currentAuthorizationInfo = (SimpleAuthorizationInfo) userSubject
					.getSession().getAttribute("authorizationInfo");
			if (currentAuthorizationInfo != null) {
				return currentAuthorizationInfo;
			} else {
				String userId = (String) getAvailablePrincipal(principals);
				List<TJeemgrSysMenu> tJeemgrSysMenuList = null;
				try {
					tJeemgrSysMenuList = tJeemgrSysMenuService.selectByUserId(userId);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
				}
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				if (tJeemgrSysMenuList!=null && tJeemgrSysMenuList.size()>0) {
					for (TJeemgrSysMenu tJeemgrSysMenu : tJeemgrSysMenuList) {
						authorizationInfo.addStringPermission(tJeemgrSysMenu
								.getUrl());
					}
				}
				userSubject.getSession().setAttribute("authorizationInfo",
						authorizationInfo);

				return authorizationInfo;
			}
		} else {
			return null;
		}

	}

	/**
	 * @Title: translateAuthenticationException
	 * @Description:异常转换
	 * @author: zhangjianbin
	 * @param e
	 * @return AuthenticationException
	 */
	private AuthenticationException translateAuthenticationException(Exception e) {
		if (e instanceof AuthenticationException) {
			return (AuthenticationException) e;
		}
		if (e instanceof DisabledAccountException) {
			return (DisabledAccountException) e;
		}
		if (e instanceof UnknownAccountException) {
			return (UnknownAccountException) e;
		}
		return new AuthenticationException(e);
	}
	
	public void clearAuthz(){
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}

}
