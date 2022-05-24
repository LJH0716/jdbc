package com.javaex.ex04;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		//등록
		BookDao bookDao = new BookDao();

		BookVo vo01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", "이문열");
		BookVo vo02 = new BookVo("삼국지", "민음사", "2002-03-01", "이문열");
		BookVo vo03 = new BookVo("토지", "마로니에북스", "2012-08-15", "박경리");
		BookVo vo04 = new BookVo("유시민의 글쓰기 특강", "생각의길", "2015-04-01", "유시민");
		BookVo vo05 = new BookVo("패션왕", "중앙북스(books)", "2012-02-22", "기안84");
		BookVo vo06 = new BookVo("순정만화", "재미주의", "2011-08-03", "강풀");
		BookVo vo07 = new BookVo("오직 두 사람", "문학동네", "2017-05-04", "김영하");
		BookVo vo08 = new BookVo("26년", "재미주의", "2012-08-04", "강풀");

		bookDao.bookInsert(vo01);
		bookDao.bookInsert(vo02);
		bookDao.bookInsert(vo03);
		bookDao.bookInsert(vo04);
		bookDao.bookInsert(vo05);
		bookDao.bookInsert(vo06);
		bookDao.bookInsert(vo07);
		bookDao.bookInsert(vo08);
		
		//수정
		BookVo uVo = new BookVo(4,"자바프로그래밍 입문", "위키북스", "2015-04-01", "이고잉");
		bookDao.bookUpdate(uVo);
		
		//삭제
		int dCount = bookDao.bookDelete(4); System.out.println("삭제건수:" + dCount);

		//조회

		List<BookVo> bookList = bookDao.bookSelect();
		for (int i = 0; i < bookList.size(); i++) {

			BookVo bookVo = bookList.get(i);
			System.out.println(bookVo.getBookId() + "," + bookVo.getTitle() + "," + bookVo.getPubs() + ","
					+ bookVo.getPubDate() + "," + bookVo.getAuthorName());

			/*
			 * 다른 방법 System.out.println(bookList.get(i).getBookId() + "," +
			 * bookList.get(i).getTitle() + "," + bookList.get(i).getPubs() + "," +
			 * bookList.get(i).getPubDate() + "," + bookList.get(i).getAuthorName());
			 */
		}

	}

}