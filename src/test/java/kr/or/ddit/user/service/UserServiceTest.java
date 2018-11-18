package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.ServiceDaoTestConfig;

public class UserServiceTest extends ServiceDaoTestConfig {

	@Resource(name="userService")
	private IUserService userService;
	
	@Test
	public void loginUserCheckTest() {
		/***Given***/
		UserVO userVO = new UserVO();
		userVO.setUserId("parkjinq");
		userVO.setPass("java");
		/***When***/
		userVO = userService.loginUserCheck(userVO);
		/***Then***/
		assertEquals("박진", userVO.getName());
	}

}
