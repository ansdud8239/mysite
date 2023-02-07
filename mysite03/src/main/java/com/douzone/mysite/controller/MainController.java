package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index(Model model) {
		return "main/index";
	}

	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		return "Hello World";
	}

	@ResponseBody
	@RequestMapping("/msg02")
	public String message02(String name) {
		return "안녕" + name;
	}

	@ResponseBody
	@RequestMapping("/msg03")
	public Object message03() {
		UserVo vo = new UserVo("둘리2", "둘리2", "1234", "female");

		return vo;
	}
}
