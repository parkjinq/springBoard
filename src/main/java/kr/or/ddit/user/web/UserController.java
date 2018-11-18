package kr.or.ddit.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import kr.or.ddit.user.service.IUserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@RequestMapping("/loginView")
	public String loginView() {
		return "login/login";
	}
	
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	public String loginCheck(HttpServletRequest request, HttpServletResponse response, HttpSession session, UserVO userVO, Model model) {
		String remember_id = request.getParameter("remember_id");
		UserVO userVO_chk = userService.loginUserCheck(userVO);
		
		logger.debug("remember_id : {}", remember_id);
		
		if(remember_id == null) {
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				if("userId".equals(cookie.getName())) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				} else if("remember_id".equals(cookie.getName())) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		} else {
			Cookie cookie_remember_id = new Cookie("remember_id", "Y");
			Cookie cookie_userId = new Cookie("userId", userVO.getUserId());
			response.addCookie(cookie_remember_id);
			response.addCookie(cookie_userId);
		}
		
		if(userVO_chk == null || "".equals(userVO_chk)) {
			return "login/login";
		} else {
			session.setAttribute("loginUserVO", userVO_chk);
			
			List<BoardVO> boardVOs = boardService.selectAllBoard();
			request.getServletContext().setAttribute("boardVOs", boardVOs);
			model.addAttribute(request);
			model.addAttribute(response);
			
			return "main";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "login/login";
	}
	
}
