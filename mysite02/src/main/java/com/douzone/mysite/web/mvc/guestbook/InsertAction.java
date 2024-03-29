package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("name");
		String password = request.getParameter("password");
		String content= request.getParameter("content");
		
		GuestbookVo vo =new GuestbookVo(name,password,content);
		new GuestbookDao().insert(vo);
		
		MvcUtil.redirect(request.getContextPath()+"/guestbook", request, response);
	}

}
