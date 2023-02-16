package com.douzone.mysite.vo;


public class GuestbookVo {

	private Long no;
	private String name;
	private String password;
	private String content;
	private String regDate;
	
	public GuestbookVo() {}
	
	public GuestbookVo(String name,String password,String content,String reDate) {
		this.name = name;
		this.password = password;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String message) {
		this.content = message;
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
				+", content=" +content + ", + date="+regDate+"]";
	}
	
	
}
