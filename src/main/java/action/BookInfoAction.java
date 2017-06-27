package action;

import model.Book;
import service.AppService;

public class BookInfoAction extends BaseAction{
	private int id;
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return id;
	}
	
	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}
	
	public String showInfo() throws Exception{
		System.out.println(23333333);
		return appService.showBookInfo(id);
	}
}

