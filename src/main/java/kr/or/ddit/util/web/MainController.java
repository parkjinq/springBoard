package kr.or.ddit.util.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/main")
	public String mainView() {
		
		return "main";
	}
	
}
