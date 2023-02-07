package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.GalleryService;


@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping({"","/"})
	public String index() {
		
		return "gallery/index";
	}
//	@RequestMapping("/main/update")
//	public String update(SiteVo vo) {
//		siteService.updateSite(vo);
//		servletConext.setAttribute("site",vo);
//		return "redirect:/admin";
//	}
//
//	@RequestMapping("/guestbook")
//	public String guestbook() {
//		return "admin/guestbook";
//	}
//
//	@RequestMapping("/board")
//	public String board() {
//		return "admin/board";
//	}
//
//	@RequestMapping("/user")
//	public String user() {
//		return "admin/user";
//	}
}
