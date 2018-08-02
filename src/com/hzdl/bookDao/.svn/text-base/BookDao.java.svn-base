package com.hzdl.bookDao;

import java.util.List;

import com.hzdl.entity.Book;

public interface BookDao {

	public List<Book> getBooksByCID(String cid);
	public List<Book> getBooksByCID(String cid,int pageNo);
	public List<Book> searchBook(String condition);
	public long getBooksCount(String cid);
	
	public boolean deleteBook(String BID);
	public boolean addBook(Book book);
	
	public Book getBookByBID(String BID);
	
	public boolean editBook(Book book);
}
