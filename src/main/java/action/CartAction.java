package action;

import java.io.PrintWriter;
import java.util.List;

import model.Book;
import model.Cart;
import model.Order;
import model.Orderitem;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.AppService;

public class CartAction extends BaseAction{
	private int id;
	private int buyerId;
	private int bookId;
	private int amount;
	
	
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
	
	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}
	
	@Override
	public String execute() throws Exception {

		List<Cart> carts = appService.getAllCarts();
		request().setAttribute("carts", carts);
		
		
		List<Order> orders = appService.getAllOrders();
		request().setAttribute("orders", orders);
		
		List<User> users=appService.getAllUsers();
		request().setAttribute("users", users);

		List<Book> books = appService.getAllBooks();
		request().setAttribute("books", books);
		return SUCCESS;
	}
	
	public String UserInit() throws Exception {

		List<Cart> carts = appService.getUserCarts();
		request().setAttribute("carts", carts);

		List<Order> orders = appService.getUserOrders();
		request().setAttribute("orders", orders);

		List<Book> books = appService.getAllBooks();
		request().setAttribute("books", books);
		return "user";
	}
	
	
	public String get() throws Exception {

		List<Cart> carts = appService.getAllCarts();
	
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
        total=carts.size();
        result.put("total", total);
        
        for(int i=0;i<carts.size()&&count<max;i++){
        	count++;
        	if(count<=min) continue;
        	JSONObject obj = new JSONObject(); 
        	Cart cart = carts.get(i);
			
			obj.put("id", cart.getId());
			obj.put("amount", cart.getAmount());
			obj.put("bookId",cart.getBookId());
			obj.put("buyerId",cart.getBuyerId());
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

		List<Cart> carts = appService.getUserCarts();
	
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
        total=carts.size();
        result.put("total", total);
        
        for(int i=0;i<carts.size()&&count<max;i++){
        	count++;
        	if(count<=min) continue;
        	JSONObject obj = new JSONObject(); 
        	Cart cart = carts.get(i);
			
			obj.put("id", cart.getId());
			obj.put("amount", cart.getAmount());
			obj.put("bookId",cart.getBookId());
			obj.put("buyerId",cart.getBuyerId());
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

		Cart cart = appService.getCartById(id);
		appService.deleteCart(cart);

		JSONObject obj = new JSONObject(); 
		obj.put("success",true);
        String str = obj.toString();  
        response().getWriter().write(str);  
		return null;
	}
	
	public String add() throws Exception {

		Cart cart = new Cart(buyerId, bookId, amount);
		appService.addCart(cart);

		return SUCCESS;
	}

	public String update() throws Exception {

		Cart cart = appService.getCartById(id);
		System.out.println(buyerId);
		System.out.println(bookId);
		cart.setBuyerId(buyerId);
		cart.setBookId(bookId);
		cart.setAmount(amount);
		appService.updateCart(cart);

		return SUCCESS;
	}
	
	
	
}
