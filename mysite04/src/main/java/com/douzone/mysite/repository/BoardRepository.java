package com.douzone.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	// select
	public List<BoardVo> findAllByPageAndKeyWord(String keyword, int pageNo, int size) {
		Map<String, Object> map = Map.of("startOffset", (pageNo - 1) * size, "keyword", keyword, "size", size);
		return sqlSession.selectList("board.findAllByPageAndKeyWord", map);
	}

	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	public BoardVo findByNoAndUserNo(Long no, Long userNo) {
		Map<String, Object> map = Map.of("no", no, "userNo", userNo);
		return sqlSession.selectOne("board.findByNoAndUserNo", map);
	}

	public int getTotalCount(String keyword) {
		return sqlSession.selectOne("board.getTotalCount", keyword);
	}

	// insert
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}

	public void insertComment(BoardVo vo) {
		sqlSession.insert("insertComment", vo);

	}

	// upeate
	public void updateByNo(BoardVo vo) {
		sqlSession.update("board.updateByNo", vo);
	}

	public void updateHit(Long no, int hit) {
		Map<String, Object> map = Map.of("no", no, "hit", hit+1);
		sqlSession.update("board.updateHit", map);
	}

	public void deleteByNoAndUserNo(Long no, Long userNo) {
		Map<String, Object> map = Map.of("no", no, "userNo", userNo);
		sqlSession.update("board.deleteByNoAndUserNo", map);
	}

}
