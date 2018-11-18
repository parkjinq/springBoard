package kr.or.ddit.posts.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.attachments.model.AttachmentsVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.posts.model.PostsVO;
import kr.or.ddit.posts.service.IPostsService;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.util.model.PageVO;

@RequestMapping("/posts")
@Controller
public class PostsController {

	private Logger logger = LoggerFactory.getLogger(PostsController.class);

	@Resource(name = "postsService")
	private IPostsService postsService;

	@Resource(name = "boardService")
	private IBoardService boardService;

	@RequestMapping(value = "/postsPageList", method = RequestMethod.GET)
	public String postsPageList(@RequestParam("bd_id") String bd_id, PageVO pageVO, Model model) {

		BoardVO boardVO = boardService.selectBoard(bd_id);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bd_id", bd_id);
		params.put("pageVO", pageVO);
		params.put("search_title", "");
		params.put("search_userid", "");

		Map<String, Object> resultMap = postsService.selectPostsPageList(params);
		resultMap.put("boardVO", boardVO);
		resultMap.put("searchBox", "title");
		resultMap.put("searchInput", "");
		model.addAttribute("resultMap", resultMap);

		return "postsPageList";
	}

	@RequestMapping(value = "/postsPageListAjax", method = RequestMethod.GET)
	public String postsPageListAjax(@RequestParam("bd_id") String bd_id, PageVO pageVO, Model model,
			@RequestParam("searchBox") String searchBox, @RequestParam("searchInput") String searchInput) {

		searchInput = searchInput == null || searchInput.equals("undefined") ? "" : searchInput;
		searchBox = searchBox == null || searchBox.equals("undefined") ? "title" : searchBox;

		logger.debug("searchInput : {}", searchInput);
		logger.debug("searchBox : {}", searchBox);

		BoardVO boardVO = boardService.selectBoard(bd_id);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bd_id", bd_id);
		params.put("pageVO", pageVO);
		if ("id".equals(searchBox)) {
			params.put("search_title", "");
			params.put("search_userid", searchInput);
		} else {
			params.put("search_title", searchInput);
			params.put("search_userid", "");
		}

		Map<String, Object> resultMap = postsService.selectPostsPageList(params);
		resultMap.put("boardVO", boardVO);
		resultMap.put("searchBox", searchBox);
		resultMap.put("searchInput", searchInput);
		model.addAttribute("resultMap", resultMap);

		return "posts/postslPageList_pagination";
	}

	@RequestMapping("/postsInsertView_new")
	public String postsInsert(@RequestParam("bd_id") String bd_id, @RequestParam("ps_id2") String ps_id2, Model model) {
		model.addAttribute("bd_id", bd_id);
		model.addAttribute("ps_id2", ps_id2);
		return "postsInsert_new";
	}

	@RequestMapping("/postsDetail")
	public String postsDetail(@RequestParam("ps_id") String ps_id, Model model) {

		Map<String, Object> resultMap = postsService.selectPostsDetail(ps_id);
		model.addAllAttributes(resultMap);

		return "postsDetail";
	}

	@RequestMapping(value = "/postsInsert", method = RequestMethod.POST)
	public String postsInsert(@RequestPart("file") MultipartFile[] parts, PostsVO postsVO, HttpServletRequest request,
			Model model) throws IOException, ServletException {

		logger.debug("postsVO : {}", postsVO);

		if (postsVO.getPs_id2() == null) {
			postsVO.setPs_id2("");
		}

		List<AttachmentsVO> attachmentsVOs = new ArrayList<AttachmentsVO>(); // 아마 이것때문?
		for (MultipartFile part : parts) {
			String path = request.getServletContext().getRealPath("/attachments");
			String fileName = UUID.randomUUID().toString();
			String originalFileName = part.getOriginalFilename();
			String fileExt = FileUtil.getFileExt(originalFileName);

			File file = new File(path + File.separator + fileName + fileExt);
			AttachmentsVO attachmentsVO = new AttachmentsVO();
			attachmentsVO.setAtt_path(fileName + fileExt);
			attachmentsVO.setAtt_originname(originalFileName);
			attachmentsVO.setPs_id(postsVO.getPs_id());

			attachmentsVOs.add(attachmentsVO);

			part.transferTo(file);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postsVO", postsVO);
		params.put("attachmentsVOs", attachmentsVOs);

		int insertCnt = postsService.insertPosts(params);
		logger.debug("postsInsert : {}", insertCnt);

		return "redirect:postsPageList?bd_id=" + postsVO.getBd_id() + "&page=1&pageSize=10";
	}

	@RequestMapping("/postsDelete")
	public String postsDelete(PostsVO postsVO, Model model) {
		int deleteCnt = postsService.deletePosts(postsVO.getPs_id());
		return "redirect:postsPageList?bd_id=" + postsVO.getBd_id() + "&page=1&pageSize=10";
	}

	@RequestMapping("/postsUpdateView")
	public String postsUpdateView(@RequestParam("ps_id") String ps_id, Model model) {
		Map<String, Object> resultMap = postsService.selectPostsDetail(ps_id);
		model.addAllAttributes(resultMap);
		return "postsUpdate";
	}

	@RequestMapping(value = "/postsUpdate", method = RequestMethod.POST)
	public String postsUpdate(@RequestPart("file") MultipartFile[] parts, @RequestParam("temp") String temp,
			PostsVO postsVO, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		logger.debug("postsVO : {}", postsVO);

		if (postsVO.getPs_id2() == null) {
			postsVO.setPs_id2("");
		}

		List<AttachmentsVO> attachmentsVOs = new ArrayList<AttachmentsVO>();
		for (MultipartFile part : parts) {
			// 1. 파일write
			String path = request.getServletContext().getRealPath("/attachments");
			String fileName = UUID.randomUUID().toString();
			String originalFileName = part.getOriginalFilename();
			String fileExt = FileUtil.getFileExt(originalFileName);

			File file = new File(path + File.separator + fileName + fileExt);
			AttachmentsVO attachmentsVO = new AttachmentsVO();
			attachmentsVO.setAtt_path(fileName + fileExt);
			attachmentsVO.setAtt_originname(originalFileName);
			attachmentsVO.setPs_id(postsVO.getPs_id());

			attachmentsVOs.add(attachmentsVO);

			part.transferTo(file);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postsVO", postsVO);
		params.put("attachmentsVOs", attachmentsVOs);
		params.put("temp", temp);

		int updateCnt = postsService.updatePosts(params);
		logger.debug("postsUpdate : {}", updateCnt);
		return "redirect:postsPageList?bd_id=" + postsVO.getBd_id() + "&page=1&pageSize=10";
	}
}
