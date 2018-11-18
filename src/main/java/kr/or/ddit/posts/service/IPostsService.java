package kr.or.ddit.posts.service;

import java.util.Map;

public interface IPostsService {
	/**
	* Method : selectPostsPageList
	* 작성자 : jin
	* 변경이력 :
	* @param params
	* @return
	* Method 설명 : 해당 게시판페이지 리스트를 반환하는 메서드
	 */
	Map<String, Object> selectPostsPageList(Map<String, Object> params);

	/**
	* Method : insertPosts
	* 작성자 : jin
	* 변경이력 :
	* @param params
	* @return
	* Method 설명 : 새로운 게시글을 생성하는 메서드 (첨부파일 포함)
	 */
	int insertPosts(Map<String, Object> params);
	
	/**
	* Method : selectPostsDetail
	* 작성자 : jin
	* 변경이력 :
	* @param ps_id
	* @return
	* Method 설명 : 선택한 게시물 게시물VO, 첨부파일VO, 댓글VO를 반환하는 메서드
	 */
	Map<String, Object> selectPostsDetail(String ps_id);

	/**
	* Method : deletePosts
	* 작성자 : jin
	* 변경이력 :
	* @param ps_id
	* @return
	* Method 설명 : 게시글을 의미상 삭제하는 메서드(삭제여부 데이터 변경)
	 */
	int deletePosts(String ps_id);

	/**
	* Method : updatePosts
	* 작성자 : jin
	* 변경이력 :
	* @param params
	* @return
	* Method 설명 : 게시글을 수정하는 메서드(첨부파일 포함)
	 */
	int updatePosts(Map<String, Object> params);
}
