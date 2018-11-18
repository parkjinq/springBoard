package kr.or.ddit.user.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.util.ControllerTestConfig;

public class UserControllerTest extends ControllerTestConfig {

	@Test
	public void loginViewTest() throws Exception {
		/*** Given ***/
		MvcResult mvcResult = mockMvc.perform(get("/user/loginView")).andReturn();

		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/*** Then ***/
		assertEquals("login/login", mav.getViewName());
	}

	@Test
	public void loginCheckTest() throws Exception {
		/*** Given ***/
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("remember_id", "Y");
		params.add("userId", "parkjinq");
		params.add("pass", "java");
		
		MvcResult mvcResult = mockMvc.perform(post("/user/loginCheck").params(params)).andReturn();
		
		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();
		
		/*** Then ***/
		assertEquals("main", mav.getViewName());

	}

	@Test
	public void logoutTest() throws Exception {
		/*** Given ***/
		MvcResult mvcResult = mockMvc.perform(get("/user/logout")).andReturn();
		
		/*** When ***/
		ModelAndView mav = mvcResult.getModelAndView();

		/*** Then ***/
		assertEquals("login/login", mav.getViewName());
	}

}
