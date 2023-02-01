package com.douzone.mysite.controller;

import java.util.Map;
import java.util.Map.Entry;

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
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController{

	@Autowired
	private BoardService boardService;

	@RequestMapping("/")
	public String list(@RequestParam(value = "p", required = true, defaultValue = "1") int pageNo,
			@RequestParam(value = "k", required = true, defaultValue = "") String keyword, Model model) {

		Map<String, Object> map = boardService.getContentsList(pageNo, keyword);
		// model.addAllAttributes(map);

		for (Entry<String, Object> m : map.entrySet()) {
			model.addAttribute(m.getKey(), m.getValue());
		}
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
		boardService.addContents(vo);
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
			boardService.addContentsHit(no, hit);
		}
		model.addAttribute("vo", boardService.getContents(no));
		model.addAttribute("pageNum", pageNum);
		return "board/view";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum, HttpSession session,
			Model model) {

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			return "redirect:/";
		}

		boardService.deleteContents(no, authUser.getNo());
		model.addAttribute("p", pageNum);
		return "redirect:/board/";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String update(@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum, 
			HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			return "redirect:/";
		}
		BoardVo vo = boardService.getContents(no,authUser.getNo());		
		model.addAttribute("vo",vo);
		model.addAttribute("pageNum", pageNum);
		return "board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String update(@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum, 
			BoardVo vo,
			HttpSession session, Model model) {

		boardService.updateContents(vo);
		
		model.addAttribute("n",vo.getNo());
		model.addAttribute("h",vo.getHit());
		model.addAttribute("p",pageNum);
		return "redirect:/board/view";
	}

	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public String comment(@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum,
			@RequestParam(value = "g", required = true, defaultValue = "1") int groupNo,
			@RequestParam(value = "d", required = true, defaultValue = "0") int depth,
			Model model) {
		System.out.println("depth"+depth);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("depth", depth);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("no", no);
		return "/board/comment";
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String comment(@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum,
			BoardVo vo,
			HttpSession session,
			Model model) {
		// 로그인이 되어 있는지 체크
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		
		vo.setUserNo(authUser.getNo());
		boardService.addComment(vo);

		model.addAttribute("n",vo.getNo());
		model.addAttribute("h",0);
		model.addAttribute("p",pageNum);

		return "redirect:/board/view";
	}

}
