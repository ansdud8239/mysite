package com.douzone.mysite.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GuestbookVo {
	private Long no;
	private String name;
	private String password;
	private String regDate;
	private String message;
}