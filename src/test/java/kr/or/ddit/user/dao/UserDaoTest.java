package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.ServiceDaoTestConfig;

public class UserDaoTest extends ServiceDaoTestConfig {

	@Resource(name="userDao")
	private IUserDao userDao;
	
	@Test
	public void loginUserCheckTest() {
		/***Given***/

		/***When***/
		UserVO userVO = new UserVO();
		userVO.setUserId("parkjinq");
		userVO.setPass("java");
		
		userVO = userDao.loginUserCheck(userVO);
		/***Then***/
		assertEquals("박진", userVO.getName());
	}

}
