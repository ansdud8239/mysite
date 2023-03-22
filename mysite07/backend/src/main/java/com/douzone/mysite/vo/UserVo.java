package com.douzone.mysite.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserVo {

	private Long no;
	private String name;	
	private String email;
	private String password;
	private String gender;
	private String role;
	private String joinDate;
	
}
