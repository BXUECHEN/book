package com.hzdl.bookservice;

import java.util.List;

import com.hzdl.bookDao.BookDao;
import com.hzdl.bookDaoImp.BookDaoImp;
import com.hzdl.entity.Book;

public class BookService {
	
	private BookDao bookDao;

	public BookService() {
		super();
		bookDao = new BookDaoImp();
	}
	
	public List<Book> getBooksByCID(String cid) {
		return  bookDao.getBooksByCID(cid) ;
	}
	
	public List<Book> getBooksByCID(String cid,int pageNo){
		return bookDao.getBooksByCID(cid, pageNo);
	}
	
	public List<Book> searchBook(String condition){
		return bookDao.searchBook(condition);
	}
	
	public long getBooksCount(String cid){
		return bookDao.getBooksCount(cid);
	}
	//删除书
	public boolean deleteBook(String BID){
		return bookDao.deleteBook(BID);
	}
	
	public boolean addBook(Book book){
		return bookDao.addBook(book);
	}
	
	public Book getBookByBID(String BID){
		return bookDao.getBookByBID(BID);
	}
	
	public boolean editBook(Book book){
		return bookDao.editBook(book);
	}

}
