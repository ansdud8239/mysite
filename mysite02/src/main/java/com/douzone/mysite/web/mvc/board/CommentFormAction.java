package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class CommentFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("groupNo", request.getParameter("groupNo"));
		request.setAttribute("depth", request.getParameter("depth"));
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		request.setAttribute("no", request.getParameter("no"));
		MvcUtil.forward("/board/comment", request, response);
	}

}
