package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.web.mvc.Action;

public class GuestbookDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		int no = Integer.parseInt(request.getParameter("no"));

		boolean result = new GuestbookDao().delete(no, password);
		if (result) {
			response.sendRedirect(request.getContextPath()+"/guestbook");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>alert('비밀번호가 틀렸습니다.'); history.go(-1);</script>");
			pw.flush();
			pw.close();
		}

	}

}
