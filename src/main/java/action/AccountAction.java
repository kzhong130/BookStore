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

public class AccountAction extends BaseAction {
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

	public String execute() throws Exception{
		User user = appService.getUserByName(username);
		/*
		if(user==null) return "no";
		if(user.getPassword().equals(password)){
			request().getSession().setAttribute("id", user.getId());
			request().getSession().setAttribute("name", user.getUsername());
			request().getSession().setAttribute("role", user.getRole());
			System.out.println(user.getId());
			System.out.println(user.getRole());
			if(user.getRole().equals("user")){
				System.out.println(233);
				return "user";
			}
			else {
				return "admin";
			}
		}
		return "no";*/
		return appService.login(username, password);
	}
	
	public String logout() throws Exception{
		/*
		request().getSession().removeAttribute("name");
		request().getSession().removeAttribute("id");
		request().getSession().removeAttribute("role");
		return "logout";
		*/
		return appService.logout();
	}
	
}
