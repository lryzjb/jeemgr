/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  UUIDUtils.java
 * @Package com.jeemgr.util
 * @author: zhangjianbin
 * @date:   2018年11月27日 下午2:38:35
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.util;

import java.util.UUID;

/**
 * @ClassName:  UUIDUtils
 * @Description:ID工具类
 * @author: zhangjianbin
 * @date:   2018年11月27日 下午2:38:35
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public class UUIDUtils {
	/**
	 * @Title: uuidReplace
	 * @Description:获取唯一ID
	 * @author: zhangjianbin
	 * @return String
	 */
	public static String uuidReplace(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
