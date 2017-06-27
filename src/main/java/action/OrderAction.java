package action;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Book;
import model.Order;
import model.Orderitem;
import model.User;
import service.AppService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class OrderAction extends BaseAction{
	private int id;
	private int buyerId;
	private String time;
	private String address;
	
	private AppService appService;

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
	
	public void setAppService(AppService appService) {
		this.appService = appService;
	}
	
	
	public String getAll() throws Exception {

		List<Order> orders = appService.getAllOrders();
		//request().setAttribute("orders", orders);

		List<User> users = appService.getAllUsers();
		//request().setAttribute("users", users);
		
		List<Book> books = appService.getAllBooks();
		//request().setAttribute("books", books);

		JSONObject result = new JSONObject();  
        //以数组的形式保存数据库结果集  
        JSONArray jsonArr= new JSONArray();  
        
        int rows=Integer.parseInt(request().getParameter("rows"));
        int page=Integer.parseInt(request().getParameter("page"));
        
        //System.out.println(rows);
        
        //System.out.println(22222);
        
        int total=0;
        int count=0;
        int min=(page-1)*rows;
        int max=min+rows;
        total=orders.size();
        result.put("total", total);
        
        for(int i=0;i<orders.size()&&count<max;i++){
        	count++;
        	if(count<=min) continue;
        	JSONObject obj = new JSONObject(); 
        	Order order = orders.get(i);
			Set<Orderitem> orderitems = order.getOrderitems();
			ArrayList<String> orderitemStr = new ArrayList<String>();
		    double sum = 0.0;														
			Iterator iterator = orderitems.iterator();     
			while(iterator.hasNext()){
				Orderitem item = (Orderitem)iterator.next();
				orderitemStr.add(item.getId()+"");
				int itemBookId = item.getBookId();
				
				for(int j=0; j<books.size(); j++) {
					Book book = books.get(j);
					if(itemBookId==book.getId()) {
						sum += book.getPrice() * item.getAmount();
					
					}
				}
			}
			sum=sum/100;
			obj.put("id", order.getId());
			obj.put("address", order.getAddress());
			obj.put("buyerId",order.getBuyerId());
			obj.put("orderitems",orderitemStr);
			obj.put("time", order.getTime());
			obj.put("sum", sum);
			jsonArr.add(obj);
        }
        result.put("rows", jsonArr);  
        response().setCharacterEncoding("UTF-8");  
        response().setContentType("application/json;charset=utf-8");  
        String str = result.toString();  
        PrintWriter out=response().getWriter();
        out.write(str);
        
        System.out.println(str);
        
		return null;
		
	}	
	
	public String getUser() throws Exception {

		List<Order> orders = appService.getUserOrders();
		//request().setAttribute("orders", orders);

		List<User> users = appService.getAllUsers();
		//request().setAttribute("users", users);
		
		List<Book> books = appService.getAllBooks();
		//request().setAttribute("books", books);

		JSONObject result = new JSONObject();  
        //以数组的形式保存数据库结果集  
        JSONArray jsonArr= new JSONArray();  
        
        int rows=Integer.parseInt(request().getParameter("rows"));
        int page=Integer.parseInt(request().getParameter("page"));
        
        //System.out.println(rows);
        
        //System.out.println(22222);
        
        int total=0;
        int count=0;
        int min=(page-1)*rows;
        int max=min+rows;
        total=orders.size();
        result.put("total", total);
        
        for(int i=0;i<orders.size()&&count<max;i++){
        	count++;
        	if(count<=min) continue;
        	JSONObject obj = new JSONObject(); 
        	Order order = orders.get(i);
			Set<Orderitem> orderitems = order.getOrderitems();
			ArrayList<String> orderitemStr = new ArrayList<String>();
		    double sum = 0.0;														
			Iterator iterator = orderitems.iterator();     
			while(iterator.hasNext()){
				Orderitem item = (Orderitem)iterator.next();
				orderitemStr.add(item.getId()+"");
				int itemBookId = item.getBookId();
				
				for(int j=0; j<books.size(); j++) {
					Book book = books.get(j);
					if(itemBookId==book.getId()) {
						sum += book.getPrice() * item.getAmount();
					
					}
				}
			}
			sum=sum/100;

			obj.put("id", order.getId());
			obj.put("address", order.getAddress());
			obj.put("buyerId",order.getBuyerId());
			obj.put("orderitems",orderitemStr);
			obj.put("time", order.getTime());
			obj.put("sum", sum);
			jsonArr.add(obj);
        }
        result.put("rows", jsonArr);  
        response().setCharacterEncoding("UTF-8");  
        response().setContentType("application/json;charset=utf-8");  
        String str = result.toString();  
        PrintWriter out=response().getWriter();
        out.write(str);
        
        System.out.println(str);
        
		return null;
		
	}	
	
	
	public String add() throws Exception {
		
		Order order = new Order(buyerId, address,time);
		appService.addOrder(order);

		return SUCCESS;
	}
	
	public String delete() throws Exception {

		Order order = appService.getOrderById(id);
		appService.deleteOrder(order);
		JSONObject obj = new JSONObject(); 
		obj.put("success",true);
        String str = obj.toString();  
        response().getWriter().write(str);  
		return null;
	}

	public String update() throws Exception {

		Order order = appService.getOrderById(id);
		order.setAddress(address);
		order.setTime(time);
		order.setBuyerId(buyerId);
		appService.updateOrder(order);

		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {

		List<Order> orders = appService.getAllOrders();
		request().setAttribute("orders", orders);

		List<User> users = appService.getAllUsers();
		request().setAttribute("users", users);
		
		List<Book> books = appService.getAllBooks();
		request().setAttribute("books", books);

		return SUCCESS;
	}
}
