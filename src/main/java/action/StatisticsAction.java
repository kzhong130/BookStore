package action;

import service.AppService;

public class StatisticsAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bookId;
	
	private String searchStartTime;
	private String searchEndTime;
	private int searchUserId;
	private String searchBookCatagory;
	
	private AppService appService;
	
	public int getBookId(){
		return bookId;
	}
	
	public void setBookId(int bookId){
		this.bookId=bookId;
	}
	
	
	
	public void setSearchStartTime(String searchStartTime){
		this.searchStartTime=searchStartTime;
	}
	
	public String getSearchStartTime(){
		return searchStartTime;
	}
	
	public void setSearchEndTime(String searchEndTime){
		this.searchEndTime=searchEndTime;
	}
	
	public String getSearchEndTime(){
		return searchEndTime;
	}
	
	public void setSearchUserId(int searchUserId){
		this.searchUserId=searchUserId;
	}
	
	public int getSearchUserId(){
		return searchUserId;
	}
	
	public void setSearchBookCatagory(String searchBookCatagory){
		this.searchBookCatagory=searchBookCatagory;
	}
	
	public String getSearchBookCatagory(){
		return searchBookCatagory;
	}
	
	public void setAppService(AppService appService){
		this.appService=appService;
	}
	
	public String SearchByBookId() throws Exception{
		System.out.println(555);
		System.out.println(bookId);
		appService.StatisticsByBookId(bookId);;
		return "book";
	}
	
	public String GetStatisticsByBookId() throws Exception{
		
        int rows=Integer.parseInt(request().getParameter("rows"));
        int page=Integer.parseInt(request().getParameter("page"));
		
		return appService.showOrderitemInfoByBook(rows, page);
	}
}
