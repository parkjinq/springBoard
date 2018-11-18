package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

@Service
public class BoardService implements IBoardService {

	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	
	@Override
	public List<BoardVO> selectAllBoard() {
		List<BoardVO> boardVOs = boardDao.selectAllBoard();
		return boardVOs;
	}


	@Override
	public BoardVO selectBoard(String bd_id) {
		BoardVO boardVO = boardDao.selectBoard(bd_id);
		return boardVO;
	}


	@Override
	public int insertBoard(BoardVO boardVO) {
		int insertCnt = boardDao.insertBoard(boardVO);
		return insertCnt;
	}


	@Override
	public int updateBoard(BoardVO boardVO) {
		int updateCnt = boardDao.updateBoard(boardVO);
		return updateCnt;
	}

}
