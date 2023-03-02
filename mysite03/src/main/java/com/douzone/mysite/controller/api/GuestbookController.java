package com.douzone.mysite.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestbookVo;


@RestController("guestbookApiController")
@RequestMapping("/guestbook/api/")
public class GuestbookController {

	@Autowired
	private GuestBookService guestbookService;

	// create
	@PostMapping("")
	public JsonResult add(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		vo.setNo(vo.getNo());
		vo.setPassword("");
		
		return JsonResult.success(vo);
	}
	// read
	@GetMapping("")
	public JsonResult index(@RequestParam(value="sno",required=true,defaultValue="0") Long startNo) {
		
		List<GuestbookVo> list =guestbookService.getMessageList(startNo);
		for(GuestbookVo vo :list) {
			//System.out.println("****"+vo);
		}
		return JsonResult.success(list);
	}
	
	// delete
	@DeleteMapping("{no}")
	public JsonResult delete(@PathVariable Long no,@RequestParam(value="password",required=true,defaultValue="0") String password) {

		//System.out.println(guestbookService.deleteMessage(no, password));
		return guestbookService.deleteMessage(no, password)? JsonResult.success(no) : JsonResult.success(null);
	}
	
}
