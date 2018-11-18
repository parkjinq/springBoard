package kr.or.ddit.board.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@RequestMapping("/boardManage")
	public String boardManage(HttpServletRequest request) {
		List<BoardVO> boardVOs = boardService.selectAllBoard();
		request.getServletContext().setAttribute("boardVOs", boardVOs);
		return "boardManage";
	}
	
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
	public String boardInsert(BoardVO boardVO, HttpServletRequest request) {
		logger.debug("MMDDTTboardInsert : {}", boardVO);
		if(!boardVO.getBd_title().isEmpty()) {
			int insertCnt = boardService.insertBoard(boardVO);
			logger.debug("insertCnt : {}", insertCnt);
		}
		
		List<BoardVO> boardVOs = boardService.selectAllBoard();
		request.getServletContext().setAttribute("boardVOs", boardVOs);
		return "redirect:boardManage";
	}
	
	@RequestMapping("/boardUpdate")
	public String boardUpdate(BoardVO boardVO, HttpServletRequest request) {
		logger.debug("MMDDTTboardUpdate : {}", boardVO);
		int updateCnt = boardService.updateBoard(boardVO);
		logger.debug("updateCnt : {}", updateCnt);
		return "redirect:boardManage";
	}
}







