package dao;

import java.util.List;

import model.Orderitem;

public interface OrderitemDao {

	public Integer save(Orderitem orderitem);

	public void delete(Orderitem orderitem);

	public void update(Orderitem orderitem);

	public Orderitem getOrderitemById(int id);

	public List<Orderitem> getAllOrderitems();
	
	public List<Orderitem> getUserOrderitems();
	
	public List<Orderitem> getOrderitemsByBook();

}