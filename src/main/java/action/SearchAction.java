package action;

import service.AppService;

public class SearchAction extends BaseAction{
	
	private int bookId;
	private int userId;
	private String startTime;
	private String endTime;
	private String catagory;
	
	public String getCatagory(){
		return catagory;
	}
	
	public void setCatagory(String catagory){
		this.catagory=catagory;
	}
	
	public int getBookId(){
		return bookId;
	}
	
	public void setBookId(int bookId){
		this.bookId=bookId;
	}
	
	public int getUserId(){
		return userId;
	}
	
	public void setUserId(int userId){
		this.userId=userId;
	}
	
	public String getStartTime(){
		return startTime;
	}
	
	public void setStartTime(String startTime){
		this.startTime=startTime;
	}
	
	public String getEndTime(){
		return endTime;
	}
	
	public void setEndTime(String endTime){
		this.endTime=endTime;
	}
	
	public AppService appService;
	
	public void setAppService(AppService appService){
		this.appService=appService;
	}
	

	public String execute() throws Exception{
        appService.QueryAll();
        System.out.println(bookId);
        System.out.println(catagory);
        System.out.println(startTime);
        //int m=Integer.parseInt(catagory);
        String a=new String();
        
        if(bookId>0){
        	System.out.println(1);
        	appService.QueryByBook(bookId);
        }
        
        if(userId>0){
        	System.out.println(2);
        	appService.QueryByUser(userId);
        }
        
        if(!catagory.equals(a)){
        	System.out.println(3);
        	appService.QueryByCatagory(catagory);
        }
        if(!startTime.equals(a)&&!endTime.equals(a)){
        	System.out.println(4);
        	appService.QueryByTime(startTime+" 00:00:00", endTime+" 00:00:00");
        }
        
		return SUCCESS;
	}
	
	public String get() throws Exception {
		
		int rows=Integer.parseInt(request().getParameter("rows"));
        int page=Integer.parseInt(request().getParameter("page"));
		return appService.ShowQueryInfo(rows, page);
	}

}
