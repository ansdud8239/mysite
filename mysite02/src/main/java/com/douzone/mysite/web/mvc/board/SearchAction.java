package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PagingVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kwd = request.getParameter("kwd");
		
		
		// board list
		int pageNum = 1;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		System.out.println(kwd+":"+pageNum);
		List<BoardVo> list = new BoardDao().search(kwd,pageNum);
		request.setAttribute("list", list);

		// paging
		int boardTotalCount = new BoardDao().findBoardSerchTotalCount(kwd);
		PagingVo pageVo = new PagingVo();
		pageVo.setTotalCount(boardTotalCount);
		pageVo.setPage(pageNum);
		pageVo.paging();
		request.setAttribute("paging", pageVo);

		MvcUtil.forward("/board/list", request, response);

	}

}
