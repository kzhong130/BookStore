package action;

import java.io.PrintWriter;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import model.Book;
import model.Cart;
import service.AppService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BookAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String bookName;
	private int bookNumber;
	private double price;
	private String catagory;
	private String author;


	private int buyerId;
	private int bookId;
	private int amount;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName=bookName;
	}

	public int getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getAuthor(){
		return author;
	}
	
	public void setAuthor(String author){
		this.author=author;
	}
	
	
	public String add() throws Exception {
		int nprice=(int) (price*100);
		Book book = new Book(bookName,bookNumber,nprice,catagory,author);
		appService.addBook(book);

		return SUCCESS;
	}


	public String delete() throws Exception {

		Book book = appService.getBookById(id);
		appService.deleteBook(book);
		
		JSONObject obj = new JSONObject(); 
		obj.put("success",true);
        String str = obj.toString();  
        response().getWriter().write(str);  
		return null;
	}
	
	public String get() throws Exception {
		/*
		List<Book> books = appService.getAllBooks();
		//request().setAttribute("books", books);
		
		JSONObject result = new JSONObject();  
        //以数组的形式保存数据库结果集  
        JSONArray jsonArr= new JSONArray();  
        
        int rows=Integer.parseInt(request().getParameter("rows"));
        int page=Integer.parseInt(request().getParameter("page"));
        
        
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
        response().setCharacterEncoding("UTF-8");  
        response().setContentType("application/json;charset=utf-8");  
        String str = result.toString();  
        PrintWriter out=response().getWriter();
        out.write(str);
        
        System.out.println(str);
        
		//System.out.println(total);
		//System.out.println(count);
		
		
		return null;
		*/
		
		int rows=Integer.parseInt(request().getParameter("rows"));
        int page=Integer.parseInt(request().getParameter("page"));
		return appService.showBooks(rows, page);
	}
	
	public String update() throws Exception {
		int nprice=(int) (price*100);
		Book book = appService.getBookById(id);
		book.setBookNumber(bookNumber);
		book.setAuthor(author);
		book.setPrice(nprice);
		book.setCatagory(catagory);
		book.setBookName(bookName);
		appService.updateBook(book);

		return SUCCESS;
	}

	public String execute() throws Exception {

		List<Book> books = appService.getAllBooks();
		request().setAttribute("books", books);
		return SUCCESS;
		
	}
	
	public String purchase() throws Exception{
		
		/*Book book=appService.getBookById(id);
		int originalNum=book.getBookNumber();
		if(originalNum<bookNumber) return SUCCESS;
		book.setBookNumber(originalNum-bookNumber);
		appService.updateBook(book);*/
		int buyerId= (int) ActionContext.getContext().getSession().get("id");
		Cart cart=new Cart(buyerId,id,bookNumber);
		appService.addCart(cart);
		return "cart";
	}
}
