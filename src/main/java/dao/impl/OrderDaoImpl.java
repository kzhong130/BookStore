package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Order;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.opensymphony.xwork2.ActionContext;

import dao.OrderDao;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	public Integer save(Order order) {
		getHibernateTemplate().save(order);
		List<Order> orders=getAllOrders();
		return orders.get(orders.size()-1).getId();
	}

	public void delete(Order order) {
		getHibernateTemplate().delete(order);
	}

	public void update(Order order) {
		getHibernateTemplate().merge(order);
	}

	public Order getOrderById(int id) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where o.id=?", id);
		Order order = orders.size() > 0 ? orders.get(0) : null;
		return order;
	}

	public List<Order> getUserOrders() {
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//String id=(String) request.getAttribute("id");
		//System.out.println(id);
		//HttpSession session=ServletActionContext.getRequest().getSession();
		//String id=(String)session.getAttribute("id");
		//System.out.println(id);
		//System.out.println(ActionContext.getContext().getSession().get("id"));
		int id=(int) ActionContext.getContext().getSession().get("id");
		System.out.println(id);
		@SuppressWarnings("unchecked")
		//List<Order> orders = (List<Order>) getHibernateTemplate().find("from Order");
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
						"from Order as o where o.buyerId=?", id);
		return orders;
	}

	public List<Order> getAllOrders(){
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find("from Order");
		return orders;
	}
	
}
