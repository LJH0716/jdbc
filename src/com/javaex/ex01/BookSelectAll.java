package com.javaex.ex01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAll {

	public static void main(String[] args) throws IOException {

		// 0.import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버(ORACLE) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			// (1)SQL문 준비
			String query ="";
			query += " select book_id, ";
			query += "        title, ";
			query += "        pubs, ";
			query += "        pub_date, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from book b, author a ";
			query += " where a.author_id = b.author_id ";
			System.out.println(query);
			
			// (2)바인딩
			pstmt = conn.prepareStatement(query); // 문자열 쿼리로 만들기
			
			// (3) 실행
			rs = pstmt.executeQuery();
			
			// 4. 결과처리
			while (rs.next()) {
			int bookId = rs.getInt(1);
			String title = rs.getString(2);
			String pubs = rs.getString(3);
			String pub_date = rs.getString(4);
			String authorName = rs.getString(5);
			String authorDesc = rs.getString(6);
			
			System.out.println(bookId + ", " + title + ", " + pubs + "," +pub_date+","+","+authorName+","+authorDesc);
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("errer: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
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

	}
}