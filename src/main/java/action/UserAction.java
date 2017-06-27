package action;

import java.io.PrintWriter;
import java.util.List;

import model.Book;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.AppService;

public class UserAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;
	private String role;
	private String phone;
	private String email;
	private String address;
	
	private AppService appService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	
	public void setPhone(String phone){
		this.phone=phone;
	}
	
	public String getPhone(){
		return phone;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	
	public String update() throws Exception {

		User user = appService.getUserById(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		user.setAddress(address);
		user.setEmail(email);
		user.setPhone(phone);
		appService.updateUser(user);
		System.out.println(23333);

		return SUCCESS;
	}
	
	public String get() throws Exception {

		List<User> users = appService.getAllUsers();
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
        total=users.size();
        result.put("total", total);
        
        for(int i=0;i<users.size()&&count<max;i++){
        	count++;
        	if(count<=min) continue;
        	JSONObject obj = new JSONObject(); 
        	User user = users.get(i);
			obj.put("id",user.getId());
			obj.put("address", user.getAddress());
			obj.put("email", user.getEmail());
			obj.put("password",user.getPassword());
			obj.put("phone",user.getPhone());
			obj.put("role",user.getRole());
			obj.put("username", user.getUsername());
			jsonArr.add(obj);
        }
        result.put("rows", jsonArr);  
        response().setCharacterEncoding("UTF-8");  
        response().setContentType("application/json;charset=utf-8");  
        String str = result.toString();  
        PrintWriter out=response().getWriter();
        out.write(str);
        
        //System.out.println(str);
        
		//System.out.println(total);
		//System.out.println(count);
		
		
		return null;
	}
	
	public String delete() throws Exception {

		User user = appService.getUserById(id);
		appService.deleteUser(user);

		JSONObject obj = new JSONObject(); 
		obj.put("success",true);
        String str = obj.toString();  
        response().getWriter().write(str);  
		return null;
	}

	public String add() throws Exception {

		User user = new  User(username,password,role,phone,email,address);
		appService.addUser(user);
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {

		List<User> users = appService.getAllUsers();
		request().setAttribute("users", users);

		return SUCCESS;
	}
	
}
