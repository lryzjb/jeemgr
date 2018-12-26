package com.dovepay.jeemgr.dao.mapper;

import com.dovepay.jeemgr.dao.model.TJeemgrSysMenu;

import com.dovepay.jeemgr.dao.model.TJeemgrSysMenuExample;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface TJeemgrSysMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int countByExample(TJeemgrSysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int deleteByExample(TJeemgrSysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int deleteByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int insert(TJeemgrSysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int insertSelective(TJeemgrSysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    List<TJeemgrSysMenu> selectByExample(TJeemgrSysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    TJeemgrSysMenu selectByPrimaryKey(String menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int updateByExampleSelective(@Param("record") TJeemgrSysMenu record, @Param("example") TJeemgrSysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int updateByExample(@Param("record") TJeemgrSysMenu record, @Param("example") TJeemgrSysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int updateByPrimaryKeySelective(TJeemgrSysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JEEMGR_SYS_MENU
     *
     * @mbggenerated Tue Nov 20 16:55:39 CST 2018
     */
    int updateByPrimaryKey(TJeemgrSysMenu record);

	List<TJeemgrSysMenu> getMenuListByMap(@Param("params") Map<String,Object> map);

	List<TJeemgrSysMenu> loadMenuTreeByRoleId(@Param("params") Map<String, Object> map);
}