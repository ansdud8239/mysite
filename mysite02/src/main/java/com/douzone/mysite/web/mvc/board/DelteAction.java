package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DelteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new BoardDao().delete(Long.parseLong(request.getParameter("no")));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		MvcUtil.redirect(request.getContextPath()+"/board?pageNum="+pageNum, request, response);

	}

}
