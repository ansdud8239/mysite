package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<JsonResult> login(@RequestBody UserVo vo) {
		UserVo authUser = userRepository.findByEmailAndPassword(vo.getEmail(), vo.getPassword());
		
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(authUser));
	}
}
