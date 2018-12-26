/*
 * All rights Reserved, Designed By www.dovepay.com
 * @Title:  TJeemgrSysRoleAction.java
 * @Package com.jeemgr.control
 * @author: zhangjianbin
 * @date:   2018年11月18日 上午10:02:04
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 * 注意：本内容为北京亚科技术开发有限责任公司版权所有
 */
package com.jeemgr.service;

import java.util.List;
import java.util.Map;

import com.dovepay.jeemgr.dao.model.TJeemgrSysRole;
import com.dovepay.jeemgr.dao.model.TJeemgrSysUser;
/**
 * @ClassName:  TJeemgrSysRoleService
 * @Description:
 * @author: zhangjianbin
 * @date:   2018年12月4日 下午4:51:59
 * 
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public interface TJeemgrSysRoleService {

	List<TJeemgrSysRole> getRoleListByUserId(String userId);

	List<Map<String, Object>> loadRoleTreeForAddUser();

	List<Map<String, Object>> loadRoleTreeForEditUser(String userId);

	void addRoleInfo(TJeemgrSysRole role);

	void deleteRole(TJeemgrSysRole role);

	void updateRole(TJeemgrSysRole role);

	List<TJeemgrSysRole> selectRoleInPage(Map<String, Object> paraMap);

	long selectCountByExample(Map<String, Object> paraMap);

}
