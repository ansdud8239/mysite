package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbooRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestBookService {
	@Autowired
	private GuestbooRepository guestbookRepository;
	
	public List<GuestbookVo> getMessageList(){	
		return guestbookRepository.findAll();
	}
	public Boolean deleteMessage(Long no, String password) {
		return 1 == guestbookRepository.deleteByNoAndPassword(no, password);
	}
	public void addMessage(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		
	}
	public List<GuestbookVo> getMessageList(Long startNo) {
		return guestbookRepository.findByNo(startNo);
		
	}
}


