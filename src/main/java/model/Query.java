package model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Query {

	private int id;
	private int buyerId;
	private String time;
	private String address;
	

	public Query() {
	}

	public Query(int buyerId, String time,String address) {
		this.buyerId=buyerId;
		this.time=time;
		this.address=address;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId=buyerId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address=address;
	}
	
	/*private Set<Orderitem> orderitems = new HashSet<Orderitem>();

	public Set<Orderitem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Set<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}*/
}
