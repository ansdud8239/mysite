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

	public List<GuestbookVo> findAll() {		
		return guestbookRepository.findAll();
	}

	public void insert(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		
	}

	public boolean delete(int no, String password) {
		return guestbookRepository.delete(no, password);
	}
}
