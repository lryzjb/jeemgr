/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  TJeemgrSysRoleAction.java
 * @Package com.jeemgr.control
 * @author: zhangjianbin
 * @date:   2018年11月18日 上午10:06:30
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.service;

import java.util.List;
import java.util.Map;

import com.dovepay.jeemgr.dao.model.TJeemgrSysUser;
/**
 * 
 * @ClassName:  TJeemgrSysUserService
 * @Description:
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午4:52:51
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public interface TJeemgrSysUserService {

	TJeemgrSysUser selectByUserId(String userName);

	TJeemgrSysUser selectByUserName(String userName);

	void addUserInfo(TJeemgrSysUser sysUser);

	void updateUser(TJeemgrSysUser sysUser);

	void deleteUser(TJeemgrSysUser tJeemgrSysUser);

	List<TJeemgrSysUser> selectAllUserInfo(Map<String,Object> paraMap);

	void updateUserPassword(TJeemgrSysUser user);

	long countByExample(Map<String, Object> paraMap);

}
