package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	@Autowired
	private FileuploadService fileuploadService;

	@RequestMapping({ "", "/" })
	public String index(Model model) {
		model.addAttribute("list", galleryService.getImages());
		return "gallery/index";
	}

	@Auth(role = "ADMIN")
	@RequestMapping("/upload")
	public String upload(GalleryVo vo, MultipartFile file) {
		String url = fileuploadService.restore(file);
		vo.setUrl(url);
		System.out.println(vo);
		galleryService.addImage(vo);
		return "redirect:/gallery";
	}

	@Auth(role = "ADMIN")
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		galleryService.removeImage(no);
		return "redirect:/gallery";
	}
}
