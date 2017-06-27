package service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import model.Book;
import model.Cart;
import model.Order;
import model.Orderitem;
import model.User;
import model.Query;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.AppService;
import dao.BookDao;
import dao.OrderDao;
import dao.OrderitemDao;
import dao.QueryDao;
import dao.UserDao;
import dao.CartDao;
import dao.QueryDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class AppServiceImpl implements AppService {

	private BookDao bookDao;
	private OrderDao orderDao;
	private OrderitemDao orderitemDao;
	private UserDao userDao;
	private CartDao cartDao;
	private QueryDao queryDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setOrderitemDao(OrderitemDao orderitemDao) {
		this.orderitemDao = orderitemDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setCartDao(CartDao cartDao){
		this.cartDao=cartDao;
	}

	public void setQueryDao(QueryDao queryDao){
		this.queryDao=queryDao;
	}
	
	/**
	 * book
	 * 
	 */
	public Integer addBook(Book book) {
		return bookDao.save(book);
	}

	public void deleteBook(Book book) {
		bookDao.delete(book);
	}

	public void updateBook(Book book) {
		bookDao.update(book);
	}

	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}

	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	/**
	 * order
	 * 
	 */
	public Integer addOrder(Order order) {
		return orderDao.save(order);
	}

	public void deleteOrder(Order order) {
		orderDao.delete(order);
	}

	public void updateOrder(Order order) {
		orderDao.update(order);
	}

	public Order getOrderById(int id) {
		return orderDao.getOrderById(id);
	}

	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}
	
	public List<Order> getUserOrders() {
		return orderDao.getUserOrders();
	}

	/**
	 * order item
	 * 
	 */
	public Integer addOrderitem(Orderitem orderitem) {
		return orderitemDao.save(orderitem);
	}

	public void deleteOrderitem(Orderitem orderitem) {
		orderitemDao.delete(orderitem);
	}

	public void updateOrderitem(Orderitem orderitem) {
		orderitemDao.update(orderitem);
	}

	public Orderitem getOrderitemById(int id) {
		return orderitemDao.getOrderitemById(id);
	}

	public List<Orderitem> getAllOrderitems() {
		return orderitemDao.getAllOrderitems();
	}

	public List<Orderitem> getUserOrderitems() {
		return orderitemDao.getUserOrderitems();
	}
	
	
	/**
	 * user
	 * 
	 */
	public Integer addUser(User user) {
		return userDao.save(user);
	}

	public void deleteUser(User user) {
		userDao.delete(user);
	}

	public void updateUser(User user) {
		userDao.update(user);
	}

	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User getUserByName(String name) {
		return userDao.getUserByname(name);
	}

	//Cart
	
	
	public Integer addCart(Cart cart) {
		return cartDao.save(cart);
	}


	public void deleteCart(Cart cart) {
		cartDao.delete(cart);
		
	}


	public void updateCart(Cart cart) {
		cartDao.update(cart);	
	}


	public Cart getCartById(int id) {
		return cartDao.getCartById(id);
	}


	public List<Cart> getAllCarts() {
		return cartDao.getAllCart();
	}


	public List<Cart> getUserCarts() {
		return cartDao.getUserCart();
	}


	public String login(String username, String password) {
		User user = getUserByName(username);
		if(user==null) return "no";
		if(user.getPassword().equals(password)){
			ActionContext.getContext().getSession().put("id",user.getId());
			//request().getSession().setAttribute("id", user.getId());
			//request().getSession().setAttribute("name", user.getUsername());
			//request().getSession().setAttribute("role", user.getRole());
			ActionContext.getContext().getSession().put("name",user.getUsername());
			ActionContext.getContext().getSession().put("role",user.getRole());
			System.out.println(user.getId());
			System.out.println(user.getRole());
			System.out.println("sssss");
			if(user.getRole().equals("user")){
				System.out.println(233);
				return "user";
			}
			else {
				return "admin";
			}
		}
		return "no";
	}


	public String logout() {

		ActionContext.getContext().getSession().remove("name");
		ActionContext.getContext().getSession().remove("id");
		ActionContext.getContext().getSession().remove("role");
		return "logout";
	}


	public String showBooks(int rows, int page){
		List<Book> books = getAllBooks();
		JSONObject result = new JSONObject();  
        //以数组的形式保存数据库结果集  
        JSONArray jsonArr= new JSONArray();  
        
        int total=0;
        int count=0;
        int min=(page-1)*rows;
        int max=min+rows;
        total=books.size();
        result.put("total", total);
        
        for(int i=0;i<books.size()&&count<max;i++){
        	count++;
        	if(count<=min) continue;
        	JSONObject obj = new JSONObject(); 
        	Book book = books.get(i);
			double nprice=((double)book.getPrice())/100;
			obj.put("id", book.getId());
			obj.put("author", book.getAuthor());
			obj.put("bookName",book.getBookName());
			obj.put("bookNumber",book.getBookNumber());
			obj.put("catagory",book.getCatagory());
			obj.put("price", nprice);
			jsonArr.add(obj);
        }
        result.put("rows", jsonArr);  
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");  
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");  
        String str = result.toString();  
        PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.write(str);
        
        System.out.println(str);
        
		
		return null;
	}

	
	public String showBookInfo(int id) {
		
		PrintWriter out=null;
		try {
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Book book=getBookById(id);
		/*out.println("<table>");
		out.println("<tr><td><b> author: </b></td>" + "<td>"+ book.getAuthor() + "</td></tr>");
	
		out.write("</table>");*/	
		JSONObject obj = new JSONObject(); 
		obj.put("author",book.getAuthor());
        String str = obj.toString();  
        System.out.println(str);
		out.write(str);
		out.close();
		
		return "success";
	}
	
	
	

	public void StatisticsByBookId(int bookId) {
		System.out.println(666);
		ActionContext.getContext().getSession().put("searchBookId",bookId);
		
		//return "success";
	}

	public List<Orderitem> GetStatisticsByBookId(){
		
		List<Orderitem> orderitems=orderitemDao.getOrderitemsByBook();
		return orderitems;
		
	}
	
	

	@Override
	public String showOrderitemInfoByBook(int rows, int page) {
		
		List<Orderitem> orderitems=GetStatisticsByBookId();
		JSONObject result = new JSONObject();  
 
        JSONArray jsonArr= new JSONArray();  
        
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
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");  
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");  
        String str = result.toString();  
        PrintWriter out=null;
		try {
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.write(str);
        
		
		return null;
	}


	public void QueryAll() {
		queryDao.QueryAll();
		
	}
	
	public String ShowQueryInfo(int rows,int page){
		List<Query> querys=getAllQuery();
		//request().setAttribute("orders", orders);

		List<User> users = getAllUsers();
		//request().setAttribute("users", users);
		
		List<Book> books = getAllBooks();
		//request().setAttribute("books", books);

		JSONObject result = new JSONObject();  
        //以数组的形式保存数据库结果集  
        JSONArray jsonArr= new JSONArray();  
        
        
        int total=0;
        int count=0;
        int min=(page-1)*rows;
        int max=min+rows;
        total=querys.size();
        result.put("total", total);
        
        for(int i=0;i<querys.size()&&count<max;i++){
        	count++;
        	if(count<=min) continue;
        	JSONObject obj = new JSONObject(); 
        	Query query = querys.get(i);
        	Order order=orderDao.getOrderById(query.getId());
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
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");  
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");  
        String str = result.toString();  
        PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(str);
		return null;
	}

	public List<Query> getAllQuery(){
		return queryDao.getAllQuery();
	}

	@Override
	public void QueryByUser(int userid) {
		queryDao.QueryByUser(userid);
		
	}

	@Override
	public void QueryByBook(int bookid) {
		queryDao.QueryByBook(bookid);
		
	}

	@Override
	public void QueryByCatagory(String catagory) {
		queryDao.QueryByCatagory(catagory);
		
	}

	@Override
	public void QueryByTime(String startTime, String endTime) {
		queryDao.QueryByTime(startTime, endTime);		
	}

	/*
	@Override
	public String showOrderitemInfoByCatagory(int rows, int page) {
		
		return null;
	}
*/
	

}
