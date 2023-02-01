package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public List<BoardVo> getBoardList(String kwd, int pageNum) {
		return boardRepository.search(kwd, pageNum);
	}

	public List<BoardVo> getBoardList(int pageNum) {
		return boardRepository.findAll(pageNum);
	}

	public int getBoardCount(String kwd) {
		return boardRepository.findBoardSerchTotalCount(kwd);
	}

	public int getBoardCount() {
		return boardRepository.findBoardTotalCount();
	}

	public void addBoard(BoardVo vo) {
		boardRepository.insert(vo);
	}

	public void addBoardHit(Long no, int hit) {
		boardRepository.updateHit(no, hit);
	}

	public BoardVo getBoard(Long no) {
		return boardRepository.findByNo(no);
	}

	public void deleteBoard(Long no) {
		boardRepository.delete(no);	
	}
}
