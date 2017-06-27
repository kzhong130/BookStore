package service;

import java.io.IOException;
import java.util.List;

import model.Book;
import model.Order;
import model.Orderitem;
import model.Query;
import model.User;
import model.Cart;


public interface AppService {

	/**
	 * book
	 * 
	 */
	public Integer addBook(Book book);

	public void deleteBook(Book book);

	public void updateBook(Book book);

	public Book getBookById(int id);

	public List<Book> getAllBooks();

	/**
	 * order
	 * 
	 */
	public Integer addOrder(Order order);

	public void deleteOrder(Order order);

	public void updateOrder(Order order);

	public Order getOrderById(int id);

	public List<Order> getAllOrders();
	
	public List<Order> getUserOrders();

	/**
	 * order item
	 * 
	 */
	public Integer addOrderitem(Orderitem orderitem);

	public void deleteOrderitem(Orderitem orderitem);

	public void updateOrderitem(Orderitem orderitem);

	public Orderitem getOrderitemById(int id);

	public List<Orderitem> getAllOrderitems();
	
	public List<Orderitem> getUserOrderitems();

	/**
	 * user
	 * 
	 */
	public Integer addUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);

	public User getUserById(int id);
	
	public User getUserByName(String name);

	public List<User> getAllUsers();
	
	
	/*
	 * cart 
	 *
	 */
	
	public Integer addCart(Cart cart);

	public void deleteCart(Cart cart);

	public void updateCart(Cart cart);

	public Cart getCartById(int id);

	public List<Cart> getAllCarts();
	
	public List<Cart> getUserCarts();
	
	
	public String login(String username,String password);
	
	public String logout();
	
	public String showBooks(int rows,int page);
	
	public String showBookInfo(int id);
	
	public String showOrderitemInfoByBook(int rows,int page);
	
	//public String showOrderitemInfoByCatagory(int rows,int page);
	
	/*
	 * SaleStatistics
	 * 
	 */
	public void StatisticsByBookId(int bookId);
	
	public List<Orderitem> GetStatisticsByBookId();
	
	
	//Query
	public void QueryAll();
	
	public String ShowQueryInfo(int rows,int page);
	
	public List<Query> getAllQuery();
	
	public void QueryByUser(int userid);
	
	public void QueryByBook(int bookid);
	
	public void QueryByCatagory(String catagory);
	
	public void QueryByTime(String startTime,String endTime);
}
