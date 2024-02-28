package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestDao {
	
	public int guestDelete(int no) {
		int count=-1;
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guest_db";
			conn = DriverManager.getConnection(url, "guest", "guest");
		// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비
			String query = "";
			query += " delete from guest ";
			query += " where no=? ";
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
	
			// - 실행
			count = pstmt.executeUpdate();
		// 4.결과처리
			System.out.println(count + "건 삭제 되었습니다.");
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
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
		System.out.println("error:" + e);
		}
		}
		
		return count;
		
	}//delete
	
	public List<GuestbookVo> guestSelect() {
		
		List<GuestbookVo> guestList=new ArrayList<GuestbookVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guest_db";
			conn = DriverManager.getConnection(url, "guest", "guest");
		// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" select no, ";
			query+=" 		name, ";
			query+=" 		password, ";
			query+=" 		content, ";
			query+=" 		date ";
			query+=" from guest ";
			
			pstmt=conn.prepareStatement(query);
			
			rs=pstmt.executeQuery();
			
		// 4.결과처리
			while(rs.next()) {
				int no=rs.getInt("no");
				String name=rs.getString("name");
				String pw=rs.getString("password");
				String content=rs.getString("content");
				String date=rs.getString("date");
				
				//db에서 가져온 데이터 Vo로 묶기
				GuestbookVo guestVo=new GuestbookVo(no, name, pw, content, date);
				
				//리스트에 주소 추가
				guestList.add(guestVo);
			}
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
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
		System.out.println("error:" + e);
		}
		}
		return guestList;
	}//select

	public int guestInsert(GuestbookVo guestVo) {
		
		int count=-1;
	
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/guest_db";
			conn = DriverManager.getConnection(url, "guest", "guest");
		// 3. SQL문 준비 / 바인딩 / 실행
			// - sql문 준비
			String query = "";
			query += " insert into guest ";
			query += " values(null, ?, ?, ?, ?) ";
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestVo.getName());
			pstmt.setString(2, guestVo.getPw());
			pstmt.setString(3, guestVo.getContent());
			pstmt.setString(4, guestVo.getDate());
	
			// - 실행
			count = pstmt.executeUpdate();
		// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
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
		System.out.println("error:" + e);
		}
		}
		
		return count;
		
	}//insert
}
