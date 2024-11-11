package Com.Package.dao;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import Com.Package.model.*;

public class OrderDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao(Connection con) {
		this.con = con;
		}
	
	public boolean insertOrder(Order model) {

		boolean result = false;
		
		try {
			query = "insert into orders(p_id, user_id, order_quantity, order_date) values (?,?,?,?)";
			
		pst = this.con.prepareStatement(query);
		pst.setInt(1, model.getId());
		pst.setInt(2, model.getUid());
		pst.setInt(3, model.getQuantity());
		pst.setString(4, model.getDate());
		pst.executeUpdate();
		result = true;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Order> userOrder(int id){
		List<Order> list = new ArrayList<>();
		try {
			
			query = "select * from orders where user_id=? order by orders.order_id desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				ProductDao productDao = new ProductDao(this.con);
				int pId = rs.getInt("p_id");
				
				
				Product product = productDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("order_id"));
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice()*rs.getInt("order_quantity"));
				order.setQuantity(rs.getInt("order_quantity"));
				order.setDate(rs.getString("order_date"));
				list.add(order);
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void  cancelOrder(int id) {
		try {
			query = "delete from orders where order_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
