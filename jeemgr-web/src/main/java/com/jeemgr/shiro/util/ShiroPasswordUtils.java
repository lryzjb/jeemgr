/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  asd.java
 * @Package com.jeemgr.shiro.util
 * @author: zhangjianbin
 * @date:   2018年11月29日 上午9:31:25
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.shiro.util;


import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashService;
import org.apache.shiro.crypto.hash.format.DefaultHashFormatFactory;
import org.apache.shiro.crypto.hash.format.HashFormat;
import org.apache.shiro.crypto.hash.format.HashFormatFactory;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;

import java.security.MessageDigest;
import java.math.BigInteger;
/**
 * @ClassName:  ShiroPasswordUtils
 * @Description:shiro用户密码加密类
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午4:43:54
 * 
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public class ShiroPasswordUtils {
	private static DefaultPasswordService passwordService;
	
	/**
	 * @Title: encryptPassword
	 * @Description:密码加密
	 * @author: zhangjianbin
	 * @param plaintext
	 * @return String
	 */
	public static String encryptPassword(Object plaintext) {
		if (passwordService == null) {
			passwordService = new DefaultPasswordService();
			HashService hashService = new DefaultHashService();
			HashFormatFactory hashFormatFactory = new DefaultHashFormatFactory();
			HashFormat hashFormat = new Shiro1CryptFormat();
			passwordService.setHashService(hashService);
			passwordService.setHashFormatFactory(hashFormatFactory);
			passwordService.setHashFormat(hashFormat);
		}
		return passwordService.encryptPassword(plaintext);
//		try {
//			BigInteger bigInteger=null;
//		    MessageDigest md = MessageDigest.getInstance("MD5"); 
//		    byte[] inputData = plaintext.toString().getBytes();
//		    md.update(inputData); 
//		    bigInteger = new BigInteger(md.digest()); 
//		    return bigInteger.toString();
//		} catch (Exception e) {
//			return null;
//		}
		 
	}
}
