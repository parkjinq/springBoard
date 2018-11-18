package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVO;

@Repository
public class BoardDao implements IBoardDao {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public List<BoardVO> selectAllBoard() {
		List<BoardVO> boardVOs = template.selectList("board.selectAllBoard");
		return boardVOs;
	}

	@Override
	public BoardVO selectBoard(String bd_id) {
		BoardVO boardVO = template.selectOne("board.selectBoard", bd_id);
		return boardVO;
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		int insertCnt = template.insert("board.insertBoard", boardVO);
		return insertCnt;
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		int updateCnt = template.update("board.updateBoard", boardVO);
		return updateCnt;
	}

}
