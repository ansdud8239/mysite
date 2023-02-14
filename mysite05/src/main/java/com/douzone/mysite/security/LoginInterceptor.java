package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserVo vo = new UserVo();
		vo.setEmail(request.getParameter("email"));
		vo.setPassword(request.getParameter("password"));
		UserVo authUser = userService.getUser(vo);
		System.out.println("로그인 시도");
		if (authUser == null) {
			request.setAttribute("email", vo.getEmail());
			System.out.println("로그인 시도");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
		}
		HttpSession session=request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());

		return false;

	}

}
