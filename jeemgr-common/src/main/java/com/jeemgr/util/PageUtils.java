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
 * @ClassName:  PageUtils
 * @Description:easyui分页工具类
 * @author: zhangjianbin
 * @date:   2018年11月27日 下午2:38:35
 * @Copyright: (c) 2018 www.dovepay.com Inc. All rights reserved.
 */
public class PageUtils {
	private int page;     //当前第几页
    private int rows;     //每页显示记录数
    
    public PageUtils(int page, int rows){
        this.page = page;
        this.rows = rows;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
}
