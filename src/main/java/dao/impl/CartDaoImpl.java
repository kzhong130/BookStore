package dao.impl;


import java.util.List;

import model.Order;
import model.Orderitem;
import model.Cart;

import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ActionContext;

import dao.CartDao;
import dao.OrderDao;

public class CartDaoImpl extends HibernateDaoSupport implements CartDao{
	
	public Integer save(Cart cart){
		return (Integer) getHibernateTemplate().save(cart);
	}

	public void delete(Cart cart){
		getHibernateTemplate().delete(cart);
	}

	public void update(Cart cart){
		getHibernateTemplate().merge(cart);
	}

	public Cart getCartById(int id){
		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) getHibernateTemplate().find(
				"from Cart as c where c.id=?", id);
		Cart cart = carts.size() > 0 ? carts.get(0) : null;
		return cart;
	}

	public List<Cart> getAllCart(){
		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) getHibernateTemplate().find("from Cart");
		return carts;
	}
	
	public List<Cart> getUserCart(){
		int id=(int) ActionContext.getContext().getSession().get("id");
		System.out.println(id);
		@SuppressWarnings("unchecked")
		//List<Order> orders = (List<Order>) getHibernateTemplate().find("from Order");
		List<Cart> carts = (List<Cart>) getHibernateTemplate().find(
						"from Cart as o where o.buyerId=?", id);
		return carts;
	}

}
