/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  LoginInterceptor.java
 * @Package com.jeemgr.interceptor
 * @author: zhangjianbin
 * @date:   2018年11月16日 下午2:21:44
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dovepay.jeemgr.dao.model.TJeemgrSysUser;

/**
 * @ClassName:  LoginInterceptor
 * @Description:登录拦截器
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午4:38:12
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		
		Subject subject = SecurityUtils.getSubject();
		
		TJeemgrSysUser user = (TJeemgrSysUser) subject.getPrincipal();
		
		if (user != null) {
			return true;
		}
		
		response.sendRedirect("/login.jsp");
		
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}
}
