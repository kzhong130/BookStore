package dao;

import java.util.List;

import model.Query;

public interface QueryDao {


	public List<Query> getAllQuery();
	
	public void QueryAll();
	
	public void QueryByUser(int userid);
	
	public void QueryByBook(int bookid);
	
	public void QueryByCatagory(String catagory);
	
	public void QueryByTime(String startTime,String endTime);

}