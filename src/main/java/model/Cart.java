package model;

public class Cart {
	
	private int id;
	private int buyerId;
	private int bookId;
	private int amount;
	
	
	public Cart(){
		
	}
	
	public Cart(int buyerId,int bookId,int amount){
		this.buyerId=buyerId;
		this.bookId=bookId;
		this.amount=amount;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getBuyerId(){
		return buyerId;
	}
	
	public void setBuyerId(int buyerId){
		this.buyerId=buyerId;
	}
	
	public int getBookId(){
		return bookId;
	}
	
	public void setBookId(int bookId){
		this.bookId=bookId;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public void setAmount(int amount){
		this.amount=amount;
	}
	
}
