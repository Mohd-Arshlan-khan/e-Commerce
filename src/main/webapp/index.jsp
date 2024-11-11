<%@page import="Com.Package.model.Cart" %>
<%@ page import="java.util.*" %>
<%@ page import="Com.Package.model.Product" %>
<%@ page import="Com.Package.dao.ProductDao"%>
<%@ page import="db.connection.DBcon"%>
<%@page import="Com.Package.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pd = new ProductDao(DBcon.getConnection());
List<Product> products = pd.getAllProduct();


ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list != null){
   request.setAttribute("cart_list", cart_list);
}


%>

<!DOCTYPE html>
<html>
<head>
<title>Shopping Cart</title>
<%@include file="includes/head.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-images/<%=p.getImage() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
	                   <h6 class="price">Price: $<%= p.getPrice()%><h6>				 
						<h6 class="category">Category: <%= p.getPrice() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add to Cart</a> 
								<a class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
		        		
		        	<%}
		        	 
		         }
			%>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>