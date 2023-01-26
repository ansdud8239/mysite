package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));
		int hit = Integer.parseInt(request.getParameter("hit"));

		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		String viewCookie = "";
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				// System.out.println(cookie);
				if ((request.getParameter("no").equals(cookie.getName()))) {
					viewCookie = cookie.getValue();
				}
			}
		}

		if (viewCookie.equals("")) {
			// 해당 글을 조회하지 않은 경우
			Cookie cookieCnt = new Cookie(request.getParameter("no"), "cookie");
			cookieCnt.setPath(request.getContextPath());
			// 초단위 : 1일 -> 24*60*60
			cookieCnt.setMaxAge(24 * 60 * 60);
			response.addCookie(cookieCnt);

			// 조회수 +1	
			new BoardDao().updateHit(no, hit);
		}

		BoardVo vo = new BoardDao().findByNo(no);
		request.setAttribute("vo", vo);
		MvcUtil.forward("board/view", request, response);

	}

}
