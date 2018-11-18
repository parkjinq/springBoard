package kr.or.ddit.attachments.dao;

import java.util.List;

import kr.or.ddit.attachments.model.AttachmentsVO;

public interface IAttachmentsDao {

	/**
	* Method : insertAttachments
	* 작성자 : jin
	* 변경이력 :
	* @param attachmentsVO
	* @return
	* Method 설명 : 게시글 첨부파일을 저장하는 메서드
	 */
	int insertAttachments(AttachmentsVO attachmentsVO);

	/**
	* Method : selectAttachmentsDetail
	* 작성자 : jin
	* 변경이력 :
	* @param ps_id
	* @return
	* Method 설명 : 해당 게시글의 첨부파일 리스트를 반환하는 메서드
	 */
	List<AttachmentsVO> selectAttachmentsDetail(String ps_id);

	/**
	* Method : deleteAttachments
	* 작성자 : jin
	* 변경이력 :
	* @param str
	* @return
	* Method 설명 : 첨부파일 삭제하는 메서드
	 */
	int deleteAttachments(String str);
	
}
