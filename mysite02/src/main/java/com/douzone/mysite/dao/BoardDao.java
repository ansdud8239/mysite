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
	public void insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnetion();
			String sql = "insert into board values(null,?,?,1,now(),(select g_no from (select max(g_no)+1 'g_no' from board) tmp),1,0,?)";
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

	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnetion();
			String sql = "select a.no,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,b.name from board a "
					+ "join user b on a.user_no = b.no";
			pstmt = conn.prepareStatement(sql);

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
				vo.setUserName(rs.getString(10));
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
			String sql = "select a.no,a.title,a.contents,a.hit,a.reg_date,a.g_no,a.o_no,a.depth,a.user_no,b.name from board a "
					+ "join user b on a.user_no = b.no where a.no=?";
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
				vo.setUserName(rs.getString(10));
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

	public boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnetion();
			String sql = "delete from board where no=?";
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
		System.out.println(vo);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnetion();
				String sql = "update board set title=?,contents=?,reg_date=now() where no=?";
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
