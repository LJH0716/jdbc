package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		// 3. SQL문 준비 / 바인딩 / 실행
		
		//(1)SQL문 준비
			String query = "";
			query += " select   author_id ";
			query += " 			,author_name ";	
			query += "  		,author_desc ";
			query += " from author ";
			
		//(2)바인딩	
			pstmt = conn.prepareStatement(query);
			  		
		//(3)실행	
			rs = pstmt.executeQuery(); //-->select만 다름
			
		// 4.결과처리 -->cursor 개념이 있음(cursor는 한줄씩)
			while(rs.next()) {
				
				//1번-->getInt("컬럼명"),getString("컬럼명") 사용
				
				//int authorId = rs.getInt("author_id");
				//String authorName = rs.getString("author_name");
				//String authorDesc = rs.getString("author_desc");
				
				//2번-->getInt(번호), getString(번호) 사용
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				
				System.out.println(authorId +","+ authorName + "," + authorDesc);
				
			}
			
			//rs.next(); //-->커서가 제일 위 컬럼명에 위치해있어서 한줄 내려주기
			//getInt("컬럼명") ,getInt(번호)
			//getString("컬럼명"), getString(번호) -->메소드 오버로딩
			//2개 방식으로 결과값 도출 가능 --> 각각 장,단점
			
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

	}

}
