package action;

import java.io.PrintWriter;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import model.Book;
import model.Order;
import model.Orderitem;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.AppService;

public class OrderitemAction extends BaseAction{
	private int id;
	private int orderId;
	private int bookId;
	private int amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	private Order order;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	@Override
	public String execute() throws Exception {

		List<Orderitem> orderitems = appService.getAllOrderitems();
		request().setAttribute("orderitems", orderitems);

		List<Order> orders = appService.getAllOrders();
		request().setAttribute("orders", orders);

		List<Book> books = appService.getAllBooks();
		request().setAttribute("books", books);
		return SUCCESS;
	}
	
	public String UserInit() throws Exception {

		List<Orderitem> orderitems = appService.getUserOrderitems();
		request().setAttribute("orderitems", orderitems);

		List<Order> orders = appService.getUserOrders();
		request().setAttribute("orders", orders);

		List<Book> books = appService.getAllBooks();
		request().setAttribute("books", books);
		return "user";
	}
	
	
	public String get() throws Exception {

		List<Orderitem> orderitems = appService.getAllOrderitems();
	
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
        total=orderitems.size();
        result.put("total", total);
        
        for(int i=0;i<orderitems.size()&&count<max;i++){
        	count++;
        	if(count<=min) continue;
        	JSONObject obj = new JSONObject(); 
        	Orderitem orderitem = orderitems.get(i);
			
			obj.put("id", orderitem.getId());
			obj.put("amount", orderitem.getAmount());
			obj.put("bookId",orderitem.getBookId());
			obj.put("orderId",orderitem.getOrderId());
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

		List<Orderitem> orderitems = appService.getUserOrderitems();
	
		JSONObject result = new JSONObject();  
        //以数组的形式保存数据库结果集  
        JSONArray jsonArr= new JSONArray();  
        
        int rows=Integer.parseInt(request().getParameter("rows"));
        int page=Integer.parseInt(request().getParameter("page"));
        
        int total=0;
        int count=0;
        int min=(page-1)*rows;
        int max=min+rows;
        total=orderitems.size();
        result.put("total", total);
        
        for(int i=0;i<orderitems.size()&&count<max;i++){
        	count++;
        	if(count<=min) continue;
        	JSONObject obj = new JSONObject(); 
        	Orderitem orderitem = orderitems.get(i);
			
			obj.put("id", orderitem.getId());
			obj.put("amount", orderitem.getAmount());
			obj.put("bookId",orderitem.getBookId());
			obj.put("orderId",orderitem.getOrderId());
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
	
	public String delete() throws Exception {

		Orderitem orderitem = appService.getOrderitemById(id);
		appService.deleteOrderitem(orderitem);
		System.out.println("su");
		JSONObject obj = new JSONObject(); 
		obj.put("success",true);
        String str = obj.toString();  
        response().getWriter().write(str);  
		return null;
	}
	
	public String add() throws Exception {

		Orderitem orderitem = new Orderitem(orderId, bookId, amount);
		appService.addOrderitem(orderitem);

		return SUCCESS;
	}

	public String update() throws Exception {

		Orderitem orderitem = appService.getOrderitemById(id);
		System.out.println(orderId);
		System.out.println(bookId);
		orderitem.setOrderId(orderId);
		orderitem.setBookId(bookId);
		orderitem.setAmount(amount);
		appService.updateOrderitem(orderitem);

		return SUCCESS;
	}

}
