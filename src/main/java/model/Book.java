package model;

import java.sql.*;

public class Book {

	private int id;
	private String bookName;
	private int bookNumber;
	private int price;
	private String catagory;
	private String author;

	public Book() {
	}

	public Book(String bookName,int bookNumber,int price,String catagory,String author) {
		this.bookName=bookName;
		this.bookNumber=bookNumber;
		this.price=price;
		this.catagory=catagory;
		this.author=author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName=bookName;
	}

	public int getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getAuthor(){
		return author;
	}
	
	public void setAuthor(String author){
		this.author=author;
	}
	
}
