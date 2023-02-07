package com.douzone.mysite.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController{

	@Autowired
	private BoardService boardService;

	@RequestMapping({"","/"})
	public String list(
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNo,
			@RequestParam(value = "k", required = true, defaultValue = "") String keyword, 
			Model model) {

		Map<String, Object> map = boardService.getContentsList(pageNo, keyword);
		// model.addAllAttributes(map);

		for (Entry<String, Object> m : map.entrySet()) {
			model.addAttribute(m.getKey(), m.getValue());
		}
		return "board/list";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(
			@AuthUser UserVo authUser,
			BoardVo vo) {

		vo.setUserNo(authUser.getNo());
		boardService.addContents(vo);
		return "redirect:/board/";
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(
			@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "h", required = true, defaultValue = "1") int hit,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum, 
			@RequestParam(value = "k", required = true, defaultValue = "") String keyword,
			Model model,
			HttpServletResponse response, HttpServletRequest request,
			@CookieValue(value = "visit", required=true,defaultValue="") String value) {
		
		//System.out.println("visit : "+value);
		boolean result = true;
		String[] visitCookie=null;
		if(!"".equals(value)) {
			visitCookie = value.split("/");
			for(String v:visitCookie) {
				//System.out.println(v);
				if(no.toString().equals(v)) {
					result = false;
					break;
				}
			}			
		}

		if("".equals(value) || result) {
			boardService.addContentsHit(no, hit);
			Cookie cookieCnt = new Cookie("visit", (value+no+"/"));
			cookieCnt.setPath(request.getContextPath());
			// 초단위 : 1일 -> 24*60*60
			cookieCnt.setMaxAge(24 * 60 * 60);
			response.addCookie(cookieCnt);
		}

		model.addAttribute("vo", boardService.getContents(no));
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("kwd", keyword);
		return "board/view";
	}

	@Auth
	@RequestMapping("/delete")
	public String delete(
			@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum,
			@AuthUser UserVo authUser,
			Model model) {

		boardService.deleteContents(no, authUser.getNo());
		model.addAttribute("p", pageNum);
		return "redirect:/board/";
	}
	
	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String update(
			@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum, 
			@AuthUser UserVo authUser, 
			Model model) {

		BoardVo vo = boardService.getContents(no,authUser.getNo());		
		model.addAttribute("vo",vo);
		model.addAttribute("pageNum", pageNum);
		return "board/modify";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String update(
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum, 
			BoardVo vo,
			Model model) {

		boardService.updateContents(vo);
		
		model.addAttribute("n",vo.getNo());
		model.addAttribute("h",vo.getHit());
		model.addAttribute("p",pageNum);
		return "redirect:/board/view";
	}

	@Auth
	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public String comment(
			@RequestParam(value = "n", required = true, defaultValue = "1") Long no,
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum,
			@RequestParam(value = "g", required = true, defaultValue = "1") int groupNo,
			@RequestParam(value = "d", required = true, defaultValue = "0") int depth,
			Model model) {
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("depth", depth);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("no", no);
		return "/board/comment";
	}
	
	@Auth
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String comment(
			@RequestParam(value = "p", required = true, defaultValue = "1") int pageNum,
			@AuthUser UserVo authUser,
			BoardVo vo,
			Model model) {

		vo.setUserNo(authUser.getNo());
		boardService.addComment(vo);

		model.addAttribute("n",vo.getNo());
		model.addAttribute("h",0);
		model.addAttribute("p",pageNum);

		return "redirect:/board/view";
	}

}
