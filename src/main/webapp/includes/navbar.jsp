<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NavBar</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-Dark bg-dark mt-2">
<div class="container">

  <a class="navbar-brand">Shopping-Cart</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ms-auto">
      <li class="nav-item active">  
      <a class="nav-link" href="index.jsp">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="cart.jsp">Cart <span class="badge badge-danger px-1">${cart_list.size()}</span></a>
      </li>
     
     <%
           if(auth != null){ %>
            <li class="nav-item">
        <a class="nav-link " href="orders.jsp">Orders</a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="log-out">Logout</a>
      </li>
           
           
           <%}else{%>
           
             <li class="nav-item">
        <a class="nav-link " href="login.jsp">Login</a>
      </li>
           <%}
     %>
     
     
    
    </ul>
   
  </div>
  </div>
</nav>

</body>
</html>