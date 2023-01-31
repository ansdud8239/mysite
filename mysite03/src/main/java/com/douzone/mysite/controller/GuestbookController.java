package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestbookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestBookService guestbookService;
	
	@RequestMapping("/")
	public String list(Model model) {				
		List<GuestbookVo> list = guestbookService.findAll();
		model.addAttribute("list",list);
		return "guestbook/list";
	}

	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String add(GuestbookVo vo) {	
		guestbookService.insert(vo);		
		return "redirect:/guestbook/";
	}
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String delete(String no,Model model) {	
		System.out.println("durl");
		model.addAttribute("no",no);
		return "guestbook/delete";
	}
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public String delete(int no,String password) {	
		boolean result =guestbookService.delete(no, password);
		return "redirect:/guestbook/";
	}
	
}