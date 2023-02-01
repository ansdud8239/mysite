package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PagingVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/")
	public String list(@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum,
			@RequestParam(value = "k", required = true, defaultValue = "") String kwd, Model model) {

		PagingVo pageVo = new PagingVo();
		int boardTotalCount = 0;
		if (!("".equals(kwd)) && kwd != null) {

			List<BoardVo> list = boardService.getBoardList(kwd, pageNum);
			model.addAttribute("list", list);

			boardTotalCount = boardService.getBoardCount(kwd);
		} else {
			List<BoardVo> list = boardService.getBoardList(pageNum);
			model.addAttribute("list", list);

			boardTotalCount = boardService.getBoardCount();
		}
		pageVo.setTotalCount(boardTotalCount);
		pageVo.setPage(pageNum);
		pageVo.paging();

		model.addAttribute("kwd", kwd);
		model.addAttribute("paging", pageVo);

		return "board/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			return "redirect:/";
		}
		vo.setUserNo(authUser.getNo());
		boardService.addBoard(vo);
		return "redirect:/board/";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "h", required = true, defaultValue = "1") int hit,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum, Model model,
			HttpServletResponse response, HttpServletRequest request) {

		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		String viewCookie = "";
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				// System.out.println(cookie);
				if ((no.toString()).equals(cookie.getName())) {
					viewCookie = cookie.getValue();
				}
			}
		}

		if (viewCookie.equals("")) {
			// 해당 글을 조회하지 않은 경우
			Cookie cookieCnt = new Cookie(no.toString(), "cookie");
			cookieCnt.setPath(request.getContextPath());
			// 초단위 : 1일 -> 24*60*60
			cookieCnt.setMaxAge(24 * 60 * 60);
			response.addCookie(cookieCnt);

			// 조회수 +1
			boardService.addBoardHit(no, hit);
		}

		model.addAttribute("vo", boardService.getBoard(no));
		model.addAttribute("pageNum", pageNum);
		return "board/view";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum, Model model) {

		boardService.deleteBoard(no);
		model.addAttribute("pageNum", pageNum);
		return "redirect:/board/";
	}

	@RequestMapping(value ="/comment",method = RequestMethod.GET)
	public String comment(@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum, Model model) {

		boardService.deleteBoard(no);
		model.addAttribute("pageNum", pageNum);
		return "redirect:/board/";
	}
	
}
