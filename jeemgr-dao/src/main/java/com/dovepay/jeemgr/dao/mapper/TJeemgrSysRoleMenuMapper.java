package com.dovepay.jeemgr.dao.mapper;

import com.dovepay.jeemgr.dao.model.TJeemgrSysRoleMenu;
import com.dovepay.jeemgr.dao.model.TJeemgrSysRoleMenuExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface TJeemgrSysRoleMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int countByExample(TJeemgrSysRoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int deleteByExample(TJeemgrSysRoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int insert(TJeemgrSysRoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int insertSelective(TJeemgrSysRoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    List<TJeemgrSysRoleMenu> selectByExample(TJeemgrSysRoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    TJeemgrSysRoleMenu selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int updateByExampleSelective(@Param("record") TJeemgrSysRoleMenu record, @Param("example") TJeemgrSysRoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int updateByExample(@Param("record") TJeemgrSysRoleMenu record, @Param("example") TJeemgrSysRoleMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int updateByPrimaryKeySelective(TJeemgrSysRoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_ROLE_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int updateByPrimaryKey(TJeemgrSysRoleMenu record);

	int isExistByRoleMenu(@Param("roleId") String roleId, @Param("menuId") String menuId,@Param("ONLINE_SIGN") String ONLINE_SIGN);

	void deleteByRoleIdAndType(@Param("roleId") String roleId,@Param("ONLINE_SIGN") String ONLINE_SIGN);
}