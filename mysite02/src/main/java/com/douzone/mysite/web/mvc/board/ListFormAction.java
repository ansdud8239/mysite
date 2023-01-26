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

public class ListFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// board list
		int pageNum = 1;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		String kwd = request.getParameter("kwd");

		if (!("".equals(kwd)) && kwd != null) {

			List<BoardVo> list = new BoardDao().search(kwd, pageNum);
			request.setAttribute("list", list);

			// paging
			int boardTotalCount = new BoardDao().findBoardSerchTotalCount(kwd);
			PagingVo pageVo = new PagingVo();
			pageVo.setTotalCount(boardTotalCount);
			pageVo.setPage(pageNum);
			pageVo.paging();
			request.setAttribute("paging", pageVo);
			request.setAttribute("kwd", kwd);
		} else {
			List<BoardVo> list = new BoardDao().findAll(pageNum);
			request.setAttribute("list", list);

			// paging
			int boardTotalCount = new BoardDao().findBoardTotalCount();
			PagingVo pageVo = new PagingVo();
			pageVo.setTotalCount(boardTotalCount);
			pageVo.setPage(pageNum);
			pageVo.paging();
			request.setAttribute("paging", pageVo);
		}

		MvcUtil.forward("/board/list", request, response);

	}

}
