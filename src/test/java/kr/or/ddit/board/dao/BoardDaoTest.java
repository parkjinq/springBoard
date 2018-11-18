package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.util.ServiceDaoTestConfig;

public class BoardDaoTest extends ServiceDaoTestConfig {

	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	@Test
	public void selectAllBoard() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardVOs = boardDao.selectAllBoard();
		/***Then***/
		assertEquals(3, boardVOs.size());
	}
	
	@Test
	public void selectBoard() {
		/***Given***/
		String bd_id = "bd001";

		/***When***/
		BoardVO boardVO = boardDao.selectBoard(bd_id);

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
		int insertCnt = boardDao.insertBoard(boardVO);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateBoard() {
		/***Given***/
		BoardVO boardVO = new BoardVO();
		boardVO.setBd_id("bd001");
		boardVO.setBd_title("DaoTest");
		boardVO.setBd_use("Y");

		/***When***/
		int updateCnt = boardDao.updateBoard(boardVO);

		/***Then***/
		assertEquals(1, updateCnt);
	}

}
