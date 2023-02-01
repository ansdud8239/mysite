package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PagingVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public void addContents(BoardVo vo) {
		boardRepository.insert(vo);
	}

	public void addContentsHit(Long no, int hit) {
		boardRepository.updateHit(no, hit);
	}

	public void addComment(BoardVo vo) {
		vo.setDepth(vo.getDepth() + 1);
		boardRepository.insertComment(vo);
	}

	public void addBoardHit(Long no, int hit) {
		boardRepository.updateHit(no, hit);
	}

	public Map<String, Object> getContentsList(int pageNo, String keyword) {
		int toTalCount = boardRepository.getTotalCount(keyword);
		PagingVo pageVo = new PagingVo();
		pageVo.setTotalCount(toTalCount);
		pageVo.setPage(pageNo);
		pageVo.paging();

		List<BoardVo> list = boardRepository.findAllByPageAndKeyWord(keyword, pageNo, pageVo.getDisplayRow());
		// view에서 게시판 리스트를 렌더링 하기 위한 데이터 값 계산
		// 페이징 계산
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("paging", pageVo);
		map.put("kwd", keyword);
		return map;
	}

	public BoardVo getContents(Long no) {
		// view
		return boardRepository.findByNo(no);
	}

	public BoardVo getContents(Long no, Long userNo) {
		// update 전
		return boardRepository.findByNoAndUserNo(no, userNo);
	}

	public void updateContents(BoardVo vo) {
		// update
		boardRepository.updateByNo(vo);
	}

	public void deleteContents(Long no, Long userNo) {
		boardRepository.deleteByNoAndUserNo(no, userNo);
	}

}
