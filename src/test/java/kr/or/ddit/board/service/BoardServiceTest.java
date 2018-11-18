package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.util.ServiceDaoTestConfig;

public class BoardServiceTest extends ServiceDaoTestConfig {

	@Resource(name="boardService")
	private IBoardService boardService;
	
	@Test
	public void selectAllBoard() {
		/***Given***/

		/***When***/
		List<BoardVO> boardVOs = boardService.selectAllBoard();
		/***Then***/
		assertEquals(3, boardVOs.size());
	}
	
	@Test
	public void selectBoard() {
		/***Given***/
		String bd_id = "bd001";

		/***When***/
		BoardVO boardVO = boardService.selectBoard(bd_id);

		/***Then***/
		assertEquals("게시판1", boardVO.getBd_title());
	}
	
	@Test
	public void insertBoard() {
		/***Given***/
		BoardVO boardVO = new BoardVO();
		boardVO.setBd_date(new Date());
		boardVO.setBd_id("bdInsertTest");
		boardVO.setBd_title("DaoTest");
		boardVO.setBd_use("Y");
		boardVO.setUserid("parkjinq");

		/***When***/
		int insertCnt = boardService.insertBoard(boardVO);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateBoard() {
		/***Given***/
		BoardVO boardVO = new BoardVO();
		boardVO.setBd_date(new Date());
		boardVO.setBd_id("bd001");
		boardVO.setBd_title("DaoTest");
		boardVO.setBd_use("Y");

		/***When***/
		int updateCnt = boardService.updateBoard(boardVO);

		/***Then***/
		assertEquals(1, updateCnt);
	}

}
