package com.douzone.web.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.UserVo;

public class SessionUtill {
	
	public static UserVo accessControl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return null;
		}
		// 로그인이 되어 있는지 체크
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return null;
		}
		return authUser;
	}
}
