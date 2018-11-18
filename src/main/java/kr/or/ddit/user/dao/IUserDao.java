package kr.or.ddit.user.dao;

import kr.or.ddit.user.model.UserVO;

public interface IUserDao {

	/**
	 * 
	* Method : loginUserCheck
	* 작성자 : jin
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 해당 유저가 존재한다면 해당 유저정보를 반환하는 메서드
	 */
	public UserVO loginUserCheck(UserVO userVO);
}
