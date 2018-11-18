package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {

	/**
	* Method : selectAllBoard
	* 작성자 : jin
	* 변경이력 :
	* @return
	* Method 설명 : 모든 게시판을 리스트로 반환하는 메서드
	 */
	List<BoardVO> selectAllBoard();

	/**
	* Method : selectBoard
	* 작성자 : jin
	* 변경이력 :
	* @param bd_id
	* @return
	* Method 설명 : 해당 게시판 객체를 반환하는 메서드
	 */
	BoardVO selectBoard(String bd_id);

	/**
	* Method : insertBoard
	* 작성자 : jin
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판을 생성하는 메서드
	 */
	int insertBoard(BoardVO boardVO);

	/**
	* Method : updateBoard
	* 작성자 : jin
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판을 수정하는 메서드
	 */
	int updateBoard(BoardVO boardVO);
}
