package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.service.GuestBookService;

@RestController("guestbookApiController")
@RequestMapping("/gustbook/api")
public class GuestbookController {

	@Autowired
	private GuestBookService guestbookService;
	
	// create
	
	// read
	
	// delete
	
	
	
}
