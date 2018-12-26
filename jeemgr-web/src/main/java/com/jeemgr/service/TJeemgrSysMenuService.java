/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  TJeemgrSysRoleAction.java
 * @Package com.jeemgr.control
 * @author: zhangjianbin
 * @date:   2018年11月18日 上午10:01:24
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.dovepay.jeemgr.dao.model.TJeemgrSysMenu;

/**
 * 
 * @ClassName:  TJeemgrSysMenuService
 * @Description:
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午4:51:21
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public interface TJeemgrSysMenuService {

	List<TJeemgrSysMenu> selectByUserId(String userId);

	List<Map<String, Object>> loadMenuTreeByRoleId(String roleId);

	List<Map<String, Object>> loadMenuTreeForAddRole();

	List<Map<String, Object>> loadMenuTreeForEditRole(String roleId);

}
