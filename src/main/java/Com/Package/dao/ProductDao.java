package Com.Package.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import Com.Package.model.*;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<Product> getAllProduct(){
		List<Product> product = new ArrayList<Product>();
		
		try {
			query = "select * from product";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
			
				product.add(row);
			}
		}catch(Exception e){
		    e.printStackTrace();	
		}
		
		return product;
	}
        public List<Cart> getCartProducts(ArrayList<Cart> cartList){
        	List<Cart> product = new ArrayList<Cart>();
        	
        	try {
        		if(cartList.size()>0) {
        			for(Cart item:cartList) {
        				query = "select * from product where id=?";
        				pst = this.con.prepareStatement(query);
        				pst.setInt(1, item.getId());
        				rs = pst.executeQuery();
        				while(rs.next()) {
        					Cart row = new Cart();
        					row.setId(rs.getInt("id"));
        					row.setName(rs.getString("name"));
        					row.setCategory(rs.getString("category"));
        					row.setPrice(rs.getDouble("price")*item.getQuantity());
        					row.stQuantity(item.getQuantity());
        					product.add(row);
        					
        			
        					
        					
        				}
        				
        				
        			}
        		}
        		
        		
        	}catch(Exception e){
        		System.out.println(e.getMessage());
        	//	e.printStackTrace();
        	}
        	
        	return product;
        }
         
        public Product getSingleProduct(int id) {
        Product row = null;
        
        try {
        	query = "select * from product where id=?";
        	pst = this.con.prepareStatement(query);
        	pst.setInt(1, id);
        	rs = pst.executeQuery();
        	
        	while(rs.next()) {
        		row = new  Product();
        		row.setId(rs.getInt(id));
        		row.setName(rs.getString("name"));
        		row.setCategory(rs.getString("category"));
        		row.setPrice(rs.getDouble("price"));
        		row.setImage(rs.getString("image"));
        		
        		
        	}
        	
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        return row;
        }
        public double getTotalCartPrice(ArrayList<Cart> cartList) {
        	double sum =0;
        	
        	try {
        		if(cartList.size()>0) {
        			for(Cart item:cartList) {
        				query = "select price from product where id=?";
        				pst = this.con.prepareStatement(query);
        				pst.setInt(1, item.getId());
        				rs = pst.executeQuery();
        			
        				while(rs.next()) {
        					sum+=rs.getDouble("price")*item.getQuantity();
        				}
        			}
        		}
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        		
        	}
        	
        	return sum;
        }
}
