package kr.or.ddit.posts.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.util.ControllerTestConfig;

public class PostsControllerTest extends ControllerTestConfig {

	@Test
	public void postsPageListTest() throws Exception {
		/*** Given ***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("bd_id", "bd001");
		params.add("page", "1");
		params.add("pageSize", "10");
		
		MvcResult mvcResult = mockMvc.perform(get("/posts/postsPageList").params(params)).andReturn();
		
		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/*** Then ***/
		assertEquals("postsPageList", mav.getViewName());
	}
	
	@Test
	public void postsPageListAjaxTest() throws Exception {
		/*** Given ***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("bd_id", "bd001");
		params.add("page", "1");
		params.add("pageSize", "10");
		params.add("searchBox", "title");
		params.add("searchInput", "");
		
		MvcResult mvcResult = mockMvc.perform(get("/posts/postsPageListAjax").params(params)).andReturn();
		
		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/*** Then ***/
		assertEquals("posts/postslPageList_pagination", mav.getViewName());
	}
	
	@Test
	public void postsInsertViewTest() throws Exception {
		/*** Given ***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("bd_id", "bd001");
		params.add("ps_id2", "");
		
		MvcResult mvcResult = mockMvc.perform(get("/posts/postsInsertView_new").params(params)).andReturn();
		
		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/*** Then ***/
		assertEquals("postsInsert_new", mav.getViewName());
	}
	
	@Test
	public void postsDetailTest() throws Exception {
		/*** Given ***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("ps_id", "ps001");
		
		MvcResult mvcResult = mockMvc.perform(get("/posts/postsDetail").params(params)).andReturn();
		
		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/*** Then ***/
		assertEquals("postsDetail", mav.getViewName());
	}
	
	@Test
	public void postsDeleteTest() throws Exception {
		/*** Given ***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("bd_id", "bd001");
		params.add("ps_id", "ps001");
		
		MvcResult mvcResult = mockMvc.perform(get("/posts/postsDelete").params(params)).andReturn();
		
		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/*** Then ***/
		assertEquals("redirect:postsPageList?bd_id=bd001&page=1&pageSize=10", mav.getViewName());
	}
	
	@Test
	public void postsUpdateViewTest() throws Exception {
		/*** Given ***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("ps_id", "ps001");
		
		MvcResult mvcResult = mockMvc.perform(get("/posts/postsUpdateView").params(params)).andReturn();
		
		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/*** Then ***/
		assertEquals("postsUpdate", mav.getViewName());
	}
	
}
