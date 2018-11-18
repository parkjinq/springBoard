package kr.or.ddit.comments.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.comments.model.CommentsVO;
import kr.or.ddit.comments.service.ICommentsService;

@RequestMapping("/comments")
@Controller
public class CommentsController {

	private Logger logger = LoggerFactory.getLogger(CommentsController.class);
	
	@Resource(name="commentsService")
	private ICommentsService commentsService;
	
	@RequestMapping("/commentsFirst")
	public String commentsFirst(@RequestParam("ps_id") String ps_id, Model model) {
		logger.debug("ps_id 오호 : {}", ps_id);
		List<CommentsVO> commentsVOs = commentsService.selectCommentsDetail(ps_id);
		logger.debug("size 오호 : {}", commentsVOs.size());
		model.addAttribute("commentsVOs", commentsVOs);
		return "comments/commentsAjax";
	}
	
	@RequestMapping(value="/commentsInsert", method=RequestMethod.POST)
	public String commentsInsert(CommentsVO commentsVO, Model model) {
		logger.debug("{}", commentsVO);
		int insertCnt = commentsService.insertComments(commentsVO);
		
		List<CommentsVO> commentsVOs = commentsService.selectCommentsDetail(commentsVO.getPs_id());
		model.addAttribute("commentsVOs", commentsVOs);
		return "comments/commentsAjax";
	}
	
	@RequestMapping(value="/commentsDelete", method=RequestMethod.POST)
	public String commentsDelete(CommentsVO commentsVO, Model model) {
		int deleteCnt = commentsService.deleteComments(commentsVO.getCm_id());
		
		List<CommentsVO> commentsVOs = commentsService.selectCommentsDetail(commentsVO.getPs_id());
		model.addAttribute("commentsVOs", commentsVOs);
		return "comments/commentsAjax";
	}
	
}
