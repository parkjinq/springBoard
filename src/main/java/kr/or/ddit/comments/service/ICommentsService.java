package kr.or.ddit.comments.service;

import java.util.List;

import kr.or.ddit.comments.model.CommentsVO;

public interface ICommentsService {

	/**
	* Method : selectCommentsDetail
	* 작성자 : jin
	* 변경이력 :
	* @param ps_id
	* @return
	* Method 설명 : 해당 게시글의 댓글리스트를 반환하는 메서드
	 */
	List<CommentsVO> selectCommentsDetail(String ps_id);

	/**
	* Method : insertComments
	* 작성자 : jin
	* 변경이력 :
	* @param commentsVO
	* @return
	* Method 설명 : 댓글을 등록하는 메서드
	 */
	int insertComments(CommentsVO commentsVO);

	/**
	* Method : deleteComments
	* 작성자 : jin
	* 변경이력 :
	* @param commentsVO
	* @return
	* Method 설명 : 댓글을 의미상 삭제하는 메서드(삭제여부 데이터 변경)
	 */
	int deleteComments(String cm_id);

}
