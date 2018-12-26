package com.jeemgr.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/config/spring-service.xml","/jeemgr-spring-dao.xml"})
public class TViracctOrganizationServiceTest {

	@Autowired
	public TJeemgrSysUserService tJeemgrSysUserService;
	
	@Test
	public void selectTViracctOrganizationByKey() {
		tJeemgrSysUserService.selectByUserId("abc");
	}
}
