package com.douzone.mysite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.jwt.JwtTokenProvider;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@PreAuthorize(value = "")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	private final JwtTokenProvider jwtTokenProvider;
	
//	@PostMapping("/login")
//	public ResponseEntity<JsonResult> login(@RequestBody UserVo vo) {
//		UserVo authUser = userRepository.findByEmailAndPassword(vo.getEmail(), vo.getPassword());
//		
//		return ResponseEntity.status(HttpStatus.OK).body
//				(JsonResult.success(authUser));
//	}
	
	@PostMapping("/login")
	public ResponseEntity<JsonResult> login(@RequestBody UserVo vo,HttpServletRequest request) {
		System.out.println(vo);
//		UserVo member = userRepository.findByEmail(vo.getEmail())
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
		UserVo member = userRepository.findByEmailAndPassword(vo.getEmail(),vo.getPassword());

		if(member==null) {
			return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(null));
		}
		
		String token = jwtTokenProvider.createToken(member.getNo().toString(), member.getRole(),member.getName());
		System.out.println(token);
		System.out.println(jwtTokenProvider.validateToken(token));
		HttpSession session=request.getSession(true);
		session.setAttribute("token", token);
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(token));
	}
}
