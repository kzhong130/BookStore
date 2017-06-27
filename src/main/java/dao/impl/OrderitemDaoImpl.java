package dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Order;
import model.Orderitem;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ActionContext;

import dao.OrderitemDao;

public class OrderitemDaoImpl extends HibernateDaoSupport implements
		OrderitemDao {

	public Integer save(Orderitem orderitem) {
		return (Integer) getHibernateTemplate().save(orderitem);
	}

	public void delete(Orderitem orderitem) {
		getHibernateTemplate().delete(orderitem);
		/*
		System.out.println("mdd");
		int id=orderitem.getId();
		
		Session session=this.getSession();
	      //session.beginTransaction();             
	       PreparedStatement st = null;
		try {
			st = session.connection().prepareStatement(             
			          "{call deleteorder(?)}");
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             
	       try {
			st.setInt(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       try {
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       //session.getTransaction().commit();  
	       System.out.println(id);
		*/
	}

	public void update(Orderitem orderitem) {
		getHibernateTemplate().merge(orderitem);
	}

	public Orderitem getOrderitemById(int id) {
		@SuppressWarnings("unchecked")
		List<Orderitem> orderitems = (List<Orderitem>) getHibernateTemplate()
				.find("from Orderitem as oi where oi.id=?", id);
		Orderitem orderitem = orderitems.size() > 0 ? orderitems.get(0) : null;
		return orderitem;
	}

	public List<Orderitem> getAllOrderitems() {
		@SuppressWarnings("unchecked")
		List<Orderitem> orderitems = (List<Orderitem>) getHibernateTemplate()
				.find("from Orderitem");
		return orderitems;
	}

	@Override
	public List<Orderitem> getUserOrderitems() {
		int id=(int) ActionContext.getContext().getSession().get("id");
		System.out.println("test");
		System.out.println(id);
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where o.buyerId=?", id);
		int num=orders.size();
		System.out.println(num);
		List<Orderitem> orderitems=null;
		if(num>=1){
			orderitems = (List<Orderitem>) getHibernateTemplate()
					.find("from Orderitem as o where o.orderId=?",orders.get(0).getId());
		}
		else return null;
		for(int i=1;i<num;i++){
			@SuppressWarnings("unchecked")
			List<Orderitem> temp = (List<Orderitem>) getHibernateTemplate()
				.find("from Orderitem as o where o.orderId=?",orders.get(i).getId());
			System.out.println(orders.get(i).getId());
			orderitems.addAll(temp);
		}
		System.out.println("success");
		System.out.println(orderitems.size());
		return orderitems;
	}

	@Override
	public List<Orderitem> getOrderitemsByBook() {
		int bookId=(int) ActionContext.getContext().getSession().get("searchBookId");
		List <Orderitem>orderitems = (List<Orderitem>) getHibernateTemplate().find("from Orderitem as o where o.bookId=?",bookId);
		return orderitems;
	}
	
}
