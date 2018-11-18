package kr.or.ddit.posts.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.posts.model.PostsVO;

public interface IPostsDao {
	/**
	* Method : selectPostsPageList
	* 작성자 : jin
	* 변경이력 :
	* @param params
	* @return
	* Method 설명 : 해당 게시판페이지 리스트를 반환하는 메서드
	 */
	List<PostsVO> selectPostsPageList(Map<String, Object> params);
	
	/**
	* Method : getPostsAllCount
	* 작성자 : jin
	* 변경이력 :
	* @param bd_id
	* @return
	* Method 설명 : 해당 게시판의 전체 포스트수 반환하는 메서드
	 */
	int getPostsAllCount(Map<String, Object> params);

	/**
	* Method : insertPosts_new
	* 작성자 : jin
	* 변경이력 :
	* @param postsVO
	* @return
	* Method 설명 : 새로운 게시물을 insert하고 게시글 id를 반환하는 메서드
	 */
	String insertPosts_new(PostsVO postsVO);
	
	/**
	 * Method : insertPosts_re
	 * 작성자 : jin
	 * 변경이력 :
	 * @param postsVO
	 * @return
	 * Method 설명 : 게시물 답글을 insert하고 게시글 id를 반환하는 메서드
	 */
	String insertPosts_re(PostsVO postsVO);

	/**
	* Method : selectPostsDetail
	* 작성자 : jin
	* 변경이력 :
	* @param ps_id
	* @return
	* Method 설명 : 선택한 게시글의 정보를 반환하는 메서드
	 */
	PostsVO selectPostsDetail(String ps_id);

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
	* @param postsVO
	* @return
	* Method 설명 : 게시글 수정하는 메서드
	 */
	int updatePosts(PostsVO postsVO);
}
