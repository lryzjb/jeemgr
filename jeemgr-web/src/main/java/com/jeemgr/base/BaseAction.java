/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  BaseAction.java
 * @Package com.jeemgr.base
 * @author: zhangjianbin
 * @date:   2018年11月16日 下午2:21:44
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.base;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.dovepay.jeemgr.dao.model.TJeemgrSysUser;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeemgr.util.ResultMessage;

/**
 * @ClassName:  BaseAction
 * @Description:Control基类
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午4:30:36
 * 
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
@SuppressWarnings("deprecation")
public class BaseAction {
	public static final String USER_SESSION = "USER_SESSION";
	protected static ObjectMapper mapper = new ObjectMapper();
	protected static JsonFactory factory = mapper.getJsonFactory();
	protected static ResultMessage result = new ResultMessage();
	
	protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }
	
	/**
	 * @Title: writeJSON
	 * @Description:将json字符串输出
	 * @author: zhangjianbin
	 * @param json
	 * @throws IOException void
	 */
	protected void writeJSON(String json) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
	}
	
	/**
	 * @Title: writeJSON
	 * @Description:将对象转成json输出
	 * @author: zhangjianbin
	 * @param obj
	 * @throws JsonProcessingException
	 * @throws IOException void
	 */
	protected void writeJSON(Object obj) throws JsonProcessingException, IOException{
			response.setContentType("text/html;charset=utf-8");
			JsonGenerator responseJsonGenerator = factory.createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
			responseJsonGenerator.writeObject(obj);
	}

	/**
	 * @Title: getUser
	 * @Description:获得session用户对象
	 * @author: zhangjianbin
	 * @return TJeemgrSysUser
	 */
	protected TJeemgrSysUser getUser(){
		Object userObj = session.getAttribute(USER_SESSION);
		if(userObj == null){
			return null;
		}
		return (TJeemgrSysUser)userObj;
	}

	/**
	 * 
	 * @Title: redirect
	 * @Description:重定向
	 * @author: zhangjianbin
	 * @param url 目标url
	 * @return String
	 */
	protected String redirect(String url) {
		return new StringBuilder("redirect:").append(url).toString();
	}
	
	/**
	 * 
	 * @Title: exChange
	 * @Description:验证码大小写转换
	 * @author: zhangjianbin
	 * @param str
	 * @return String
	 */
	public static String exChange(String str) {
		StringBuffer sb = new StringBuffer();
		if (str != null) {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (Character.isLowerCase(c)) {
					sb.append(Character.toUpperCase(c));
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * @Title: writeString
	 * @Description:前台输出字符串
	 * @author: zhangjianbin
	 * @param str
	 * @param response void
	 */
	public void writeString(String str, HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
			pw.write(str);
			pw.flush();
		} catch (IOException e) {
		} finally {
			if (pw != null)
				pw.close();
		}
	}
}

