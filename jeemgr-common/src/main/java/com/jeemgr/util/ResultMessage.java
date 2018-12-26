/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  PageUtils.java
 * @Package com.jeemgr.util
 * @author: zhangjianbin
 * @date:   2018年11月22日 下午4:21:44
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.util;
/**
 * @ClassName:  ResultMessage
 * @Description:返回消息处理类
 * @author: zhangjianbin
 * @date:   2018年11月27日 下午2:38:35
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public class ResultMessage {
	/**是否成功**/
	private Boolean success = false ;
	/**返回消息**/
	private String message;
	/**返回数据**/
	private Object data;
	
	
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
