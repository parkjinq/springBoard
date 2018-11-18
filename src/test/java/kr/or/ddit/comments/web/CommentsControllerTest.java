package kr.or.ddit.comments.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.comments.model.CommentsVO;
import kr.or.ddit.util.ControllerTestConfig;

public class CommentsControllerTest extends ControllerTestConfig {

	@SuppressWarnings("unchecked")
	@Test
	public void commentsFirstTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/comments/commentsFirst").param("ps_id", "ps001")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		List<CommentsVO> commentsVOs = (List<CommentsVO>) mav.getModel().get("commentsVOs");
		/***Then***/
		assertEquals(4, commentsVOs.size());
		assertEquals("comments/commentsAjax", mav.getViewName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commentsInsertTest() throws Exception {
		/***Given***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("ps_id", "ps001");
		params.add("userid", "parkjinq");
		params.add("cm_cnt", "commentsTest");
		
		MvcResult mvcResult = mockMvc.perform(post("/comments/commentsInsert").params(params)).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		List<CommentsVO> commentsVOs = (List<CommentsVO>) mav.getModel().get("commentsVOs");
		/***Then***/
		assertEquals(5, commentsVOs.size());
		assertEquals("comments/commentsAjax", mav.getViewName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commentsDeleteTest() throws Exception {
		/***Given***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("ps_id", "ps001");
		params.add("cm_id", "cm002");
		
		MvcResult mvcResult = mockMvc.perform(post("/comments/commentsDelete").params(params)).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();
		List<CommentsVO> commentsVOs = (List<CommentsVO>) mav.getModel().get("commentsVOs");
		/***Then***/
		assertEquals(4, commentsVOs.size());
		assertEquals("comments/commentsAjax", mav.getViewName());
	}

}
