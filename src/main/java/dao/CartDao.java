package dao;

import java.util.List;

import model.Cart;

public interface CartDao {

	public Integer save(Cart cart);

	public void delete(Cart cart);

	public void update(Cart cart);

	public Cart getCartById(int id);

	public List<Cart> getAllCart();
	
	public List<Cart> getUserCart();

}