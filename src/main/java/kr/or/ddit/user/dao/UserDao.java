package kr.or.ddit.user.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVO;

@Repository
public class UserDao implements IUserDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public UserVO loginUserCheck(UserVO userVO) {
		UserVO userVO_chk = template.selectOne("user.loginUserCheck", userVO);
		return userVO_chk;
	}

	
	
}
