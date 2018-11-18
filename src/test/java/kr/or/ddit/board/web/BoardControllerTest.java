package kr.or.ddit.board.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.util.ControllerTestConfig;

public class BoardControllerTest extends ControllerTestConfig {

	@Test
	public void boardManageTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/board/boardManage")).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("boardManage", mav.getViewName());
	}
	
	@Test
	public void boardInsertTest() throws Exception {
		/***Given***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("bd_title", "테스트");
		params.add("bd_use", "Y");
		params.add("userid", "parkjinq");
		
		MvcResult mvcResult = mockMvc.perform(post("/board/boardInsert").params(params)).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("redirect:boardManage", mav.getViewName());
	}
	
	@Test
	public void boardUpdateTest() throws Exception {
		/***Given***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("bd_title", "테스트");
		params.add("bd_use", "N");
		params.add("bd_id", "bd001");
		
		MvcResult mvcResult = mockMvc.perform(get("/board/boardUpdate").params(params)).andReturn();

		/***When***/
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("redirect:boardManage", mav.getViewName());
	}

}
