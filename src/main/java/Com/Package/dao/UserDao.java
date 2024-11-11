package Com.Package.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Com.Package.model.User;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public UserDao(Connection con) {
		this.con = con;
	}

	public User userLogin(String email, String password) {
		User user = null;
		try {
			 query = "SELECT * FROM user WHERE email=? and password=?";
	            pst = this.con.prepareStatement(query);
	            pst.setString(1, email);
	            pst.setString(2, password);
	            rs = pst.executeQuery();
	            
	            if(rs.next()) {
	            	user = new User();
	                user.setId(rs.getInt("id"));
	                user.setName(rs.getString("name"));
	                user.setEmail(rs.getString("email"));
	                // Add other fields if needed
	            }
		}catch(Exception e) {
			 e.printStackTrace();
			 System.out.print(e.getMessage());
		}
		
	return user;
		
	}

}
