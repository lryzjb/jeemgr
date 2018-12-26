/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  SessionContext.java
 * @Package com.jeemgr.base
 * @author: zhangjianbin
 * @date:   2018年11月16日 下午2:21:44
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.base;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @ClassName:  SessionContext
 * @Description:shiroSession
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午4:35:06
 * 
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public class SessionContext {
	
	/**
	 * @Title: getCurrentUserId
	 * @Description:获取session中的用户
	 * @author: zhangjianbin
	 * @return String
	 */
	public static String getCurrentUserId() {
		Subject userSubject = null;
		try{
			userSubject = SecurityUtils.getSubject();
		}catch(UnavailableSecurityManagerException e){
			return "sys";
		}
		if (userSubject.isAuthenticated()) {
			String userId = userSubject.getPrincipal().toString();

			if (org.apache.commons.lang.StringUtils.isNotEmpty(userId)) {
				return userId;
			}
		}
		return null;
	}

	public static String getCurrentUserCoPrefix() {
		Subject userSubject = SecurityUtils.getSubject();
		if (userSubject.isAuthenticated()) {
			String userCoPrefix = userSubject.getSession()
					.getAttribute("userCoPrefix").toString();

			if (org.apache.commons.lang.StringUtils.isNotEmpty(userCoPrefix)) {
				return userCoPrefix;
			}
		}
		return null;
	}

	public static Map<String, List<Object>> getCurrentUserDataPermission() {
		Subject userSubject = SecurityUtils.getSubject();
		if (userSubject.isAuthenticated()) {
			Map<String, List<Object>> dataPermissionMap = (Map<String, List<Object>>) userSubject
					.getSession().getAttribute("dataPermission");

			if (org.apache.commons.collections.MapUtils
					.isNotEmpty(dataPermissionMap)) {
				return dataPermissionMap;
			}
		}
		return null;
	}

	public static Session getCurrentSession() {
		Subject userSubject = SecurityUtils.getSubject();
		if (userSubject.isAuthenticated()) {
			return userSubject.getSession();
		} else {
			return null;
		}
	}

	public static String getUserName() {
		Subject userSubject = SecurityUtils.getSubject();
		if (userSubject.isAuthenticated()) {
			String userId = userSubject.getSession().getAttribute("userName")
					.toString();

			if (org.apache.commons.lang.StringUtils.isNotEmpty(userId)) {
				return userId;
			}
		}
		return null;
	}

}
