package action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.*;

import com.opensymphony.xwork2.ActionContext;

import model.Book;
import model.Cart;
import model.Order;
import model.Orderitem;
import model.User;
import net.sf.json.JSONObject;
import service.AppService;

public class PurchaseAction extends BaseAction{
	private String strCartNos;
	
	public void setStrCartNos(String strCartNos){
		this.strCartNos=strCartNos;
	}
	
	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}
	
	@Override
	public String execute() throws Exception {

		System.out.println(strCartNos);
        String[] CartNos = strCartNos.split(",");
        int num=CartNos.length;
        //for(int i=0;i<num;i++){
        	//System.out.println(CartNos[i]);
        //}
        int[] Ids=new int[11];
        for(int i=0;i<num;i++){
        	Ids[i]=Integer.valueOf(CartNos[i]);
        	System.out.println(Ids[i]);	
        }
        int userId=(int) ActionContext.getContext().getSession().get("id");
        User user=appService.getUserById(userId);
		//java.util.Date date = new java.util.Date();
		//String time=String.valueOf( date );
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  
	    String time=df.format(new Date());// new Date()为获取当前系统时间  
	    System.out.println("mtime:"+time);
        Order order=new Order(userId,time,user.getAddress());
        int orderId=appService.addOrder(order);
        System.out.println(orderId);
        for(int i=0;i<num;i++){
        	Cart cart=appService.getCartById(Ids[i]);
        	
    		Book book=appService.getBookById(cart.getBookId());
    		int originalNum=book.getBookNumber();
    		if(originalNum<cart.getAmount()) continue;
    		book.setBookNumber(originalNum-cart.getAmount());
    		appService.updateBook(book);
    		//int buyerId= (int) ActionContext.getContext().getSession().get("id");
        	Orderitem orderitem=new Orderitem(orderId,cart.getBookId(),cart.getAmount());
        	appService.addOrderitem(orderitem);
        	appService.deleteCart(cart);
        }
		JSONObject obj = new JSONObject(); 
		obj.put("success",true);
        String str = obj.toString();  
        response().getWriter().write(str);  
		return null;
		//return SUCCESS;
	}
}
