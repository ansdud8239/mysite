package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long no =Long.parseLong(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNo(no);
		new BoardDao().update(vo);
		//board?a=viewform&no=43&hit=1
		MvcUtil.redirect(request.getContextPath()+"/board?a=viewform&no="+no+"&hit="+vo.getHit()+"&pageNum="+pageNum, request, response);

	}

}
