package com.douzone.mysite.vo;


public class GuestBookVo {

	private Long no;
	private String name;
	private String password;
	private String message;
	private String regDate;
	
	public GuestBookVo() {}
	
	public GuestBookVo(String name,String password,String message,String reDate) {
		this.name = name;
		this.password = password;
		this.message = message;
		this.regDate = reDate;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "GuestBookVo [no=" +no + ", name=" + name + ", password="+password
				+", message=" +message + ", + date="+regDate+"]";
	}
	
	
}
