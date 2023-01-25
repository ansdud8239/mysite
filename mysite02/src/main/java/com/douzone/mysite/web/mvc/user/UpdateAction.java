package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web2.mvc.Action;
import com.douzone.web2.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(접근제어)
		// 로그인을 하지 않더라도 jsp페이지 접속 시 새션이 null이 아님
		HttpSession session = request.getSession();
		if (session == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		// 로그인이 되어 있는지 체크
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

		UserVo vo = new UserVo(name, email, password, gender);
		vo.setNo(authUser.getNo());
		new UserDao().update(vo);
		
		authUser.setName(name);
		session.setAttribute("authUser",authUser );
		
		MvcUtil.redirect(request.getContextPath(), request, response);
	}

}
