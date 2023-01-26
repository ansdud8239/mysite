package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class CommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 로그인이 되어 있는지 체크
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		BoardVo vo = new BoardVo();
		vo.setContent(request.getParameter("content"));
		vo.setTitle(request.getParameter("title"));
		vo.setGroupNo(Long.parseLong(request.getParameter("groupNo")));
		vo.setDepth(Long.parseLong(request.getParameter("depth")));
		vo.setUserNo(authUser.getNo());
		new BoardDao().insertComment(vo);
		
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);

	}

}
