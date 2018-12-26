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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dovepay.jeemgr.dao.mapper.TJeemgrSysUserRoleMapper;
import com.jeemgr.service.TJeemgrSysUserRoleService;
/**
 * @ClassName:  TJeemgrSysUserRoleServiceImpl
 * @Description:
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午5:17:35
 * 
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
@Transactional
public class TJeemgrSysUserRoleServiceImpl implements TJeemgrSysUserRoleService{
	@Autowired
	private TJeemgrSysUserRoleMapper tJeemgrSysUserRoleMapper;

	public TJeemgrSysUserRoleMapper gettJeemgrSysUserRoleMapper() {
		return tJeemgrSysUserRoleMapper;
	}

	public void settJeemgrSysUserRoleMapper(
			TJeemgrSysUserRoleMapper tJeemgrSysUserRoleMapper) {
		this.tJeemgrSysUserRoleMapper = tJeemgrSysUserRoleMapper;
	}

}
