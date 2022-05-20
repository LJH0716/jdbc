/***
자바프로그램(웹서버)에서 코드를 짜면 jdbc를 통해 오라클(db서버)로 보내짐, 확인은 오라클에서 
오라클에서 rollback-> 자바에서 코드 짜서 실행-> 다시 오라클에서 조회(select)하면 OK
***/
package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorInsert {
	public static void main(String[] args) {

		// 0. import java.sql.*; (ctrl+shift+o)
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null; -->select문에서만 사용
		
		try {
			//*** 1. JDBC 드라이버 (Oracle) 로딩 -->기본 세팅(buildpath 라이브러리 잘 넣기!)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//*** 2. Connection 얻어오기 -->기본 세팅
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			       //url,아이디,패스워드
			       //DriverManager 은 static(미리 정해져 있는 메소드,대문자)
				   //socket 통신이라고 생각하면 됨
			
			
			//*** 3. SQL문 준비 / 바인딩 / 실행 --> 직접 코드 짜야하는 부분,쿼리문 오라클에서 짜고 확인 한 뒤에 가져와서 수정
			
			//(1)SQL문 준비
			String query = "";
			query += "insert into author";
			query += " values(seq_author_id.nextval, ?, ?)";
				//String query = "insert into author values(seq_author_id.nextval, '이문열', '경북 영양')";
						//쿼리문에 작성되어 있던 ;는 빼줘야함
						//? -->문법, 사용자 입력값으로 세팅해 두는 것
			System.out.println(query);
			
			//(2)바인딩		
			pstmt = conn.prepareStatement(query); //문자열을 쿼리로 만들기(바인딩)
			pstmt.setString(1, "유시민");   //?(물음표 중 첫번째)-->순서 중요!
			pstmt.setString(2, "17대 국회의원"); 
			
			//(3)실행
			int count = pstmt.executeUpdate();  //쿼리문 실행-->성공갯수 리턴
				//int count = pstmt.executeUpdate(); //insert, update, delete
			    //ResultSet rs = pstmt.executeQuery(); //select
			
			//*** 4.결과처리
			System.out.println(count + "건이 등록 되었습니다.");
			
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리 -->연결 닫아주는 것
			try {
				/*
				if (rs != null) {
					rs.close();
				}
				*/
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
