package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.jwt.JwtTokenProvider;
import com.douzone.mysite.service.CustomUserDetailService;

import lombok.RequiredArgsConstructor;

public class AuthInterceptor implements HandlerInterceptor {
	
	//private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("token") == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		return true;
	}

}
