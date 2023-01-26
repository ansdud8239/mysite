package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;

public class BoardDao {
	private final String INSERT_BOARD = "insert into board values(null,?,?,0,now(),(select g_no from (select if(max(g_no) is null,1,(max(g_no+1))) 'g_no' from board) tmp),1,0,?,'C')";
	private final String INSERT_BOARD_COMMENT = "insert into board values(null,?,?,0,now(),?,(select o_no from (select max(o_no)+1 'o_no' from board where g_no=?) tmp),?,?,'C')";
	private final String SELECT_BOARD_ALL = "select a.no,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,a.status,b.name from board a join user b on a.user_no = b.no order by g_no,o_no limit ?,5";
	private final String SELECT_BOARD_BY_NO = "select a.no,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,a.status,b.name from board a join user b on a.user_no = b.no where a.no=?";
	private final String SELECT_BOARD_ALL_COUNT = "select count(*) from board";
	private final String SELECT_BOARD_SEARCH_COUNT = "select count(*) from board where title like ? ";
	private final String SELECT_BOARD_SEARCH = "select a.no,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,a.status,b.name from board a join user b on a.user_no = b.no where a.title like ? order by g_no,o_no limit ?,5";
	private final String DELTE_BOARD_BY_NO = "update board set status='D' where no=?";
	private final String UPDATE_BOARD_BY_NO = "update board set title=?,contents=?,reg_date=now(),status='U' where no=?";
	private final String UPDATE_BOARD_HIT_BY_NO = "update board set hit=? where no=?";

	public void insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnetion();
			String sql = INSERT_BOARD;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUserNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void insertComment(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnetion();
			String sql = INSERT_BOARD_COMMENT;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getGroupNo());
			pstmt.setLong(4, vo.getGroupNo());
			pstmt.setLong(5, (vo.getDepth() + 1));
			pstmt.setLong(6, vo.getUserNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<BoardVo> findAll(int pageNum) {
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnetion();
			String sql = SELECT_BOARD_ALL;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ((pageNum - 1) * 5));

			rs = pstmt.executeQuery();

			// 5. 결과
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setRegDate(rs.getString(5));
				vo.setGroupNo(rs.getLong(6));
				vo.setOwnerNo(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUserNo(rs.getLong(9));
				vo.setStatus(rs.getString(10));
				vo.setUserName(rs.getString(11));
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public BoardVo findByNo(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			conn = getConnetion();
			String sql = SELECT_BOARD_BY_NO;
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			// 5. 결과
			if (rs.next()) {
				vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setRegDate(rs.getString(5));
				vo.setGroupNo(rs.getLong(6));
				vo.setOwnerNo(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUserNo(rs.getLong(9));
				vo.setStatus(rs.getString(10));
				vo.setUserName(rs.getString(11));
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;

	}

	public int findBoardTotalCount() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardCount = 0;
		try {
			conn = getConnetion();
			String sql = SELECT_BOARD_ALL_COUNT;
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			// 5. 결과
			if (rs.next()) {
				boardCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return boardCount;
	}

	public int findBoardSerchTotalCount(String kwd) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardCount = 0;
		try {
			conn = getConnetion();
			String sql = SELECT_BOARD_SEARCH_COUNT;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kwd + "%");
			rs = pstmt.executeQuery();

			// 5. 결과
			if (rs.next()) {
				boardCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return boardCount;
	}

	public List<BoardVo> search(String kwd, int pageNum) {
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnetion();
			String sql = SELECT_BOARD_SEARCH;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + kwd + "%");
			pstmt.setInt(2, ((pageNum - 1) * 5));

			rs = pstmt.executeQuery();

			// 5. 결과
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setRegDate(rs.getString(5));
				vo.setGroupNo(rs.getLong(6));
				vo.setOwnerNo(rs.getLong(7));
				vo.setDepth(rs.getLong(8));
				vo.setUserNo(rs.getLong(9));
				vo.setStatus(rs.getString(10));
				vo.setUserName(rs.getString(11));
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnetion();

			String sql = DELTE_BOARD_BY_NO;
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnetion();
			String sql = UPDATE_BOARD_BY_NO;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void updateHit(Long no, int hit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnetion();
			String sql = UPDATE_BOARD_HIT_BY_NO;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (hit + 1));
			pstmt.setLong(2, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Connection getConnetion() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.10.109:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}
		return conn;

	}

}
