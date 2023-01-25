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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Access Control(접근제어)
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
		UserVo vo = new UserDao().findByNo(authUser.getNo());
		request.setAttribute("userVo", vo);
		MvcUtil.forward("user/updateform", request, response);

	}

}
