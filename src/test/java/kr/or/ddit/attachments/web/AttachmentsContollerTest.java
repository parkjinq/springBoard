package kr.or.ddit.attachments.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import kr.or.ddit.util.ControllerTestConfig;

public class AttachmentsContollerTest extends ControllerTestConfig {

	@Test
	public void attFileDown() throws Exception {
		/*** Given ***/
		MvcResult mvcResult = mockMvc.perform(get("/attachments/attFileDown").param("attpath", "a-t-t-a-c-h-m-e-n-t-s-1.jpg")).andReturn();

		/*** When ***/
		
		/*** Then ***/
	}
	
}
