<%@page import="com.jsp.shoppingcart.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>customer option</title>
</head>
<body>
<% Customer c=(Customer)session.getAttribute("customerinfo");
	if(c!=null){
%>
		<h1>${msg }</h1>
		<h1><a href="displayproducts">Display all products</a></h1>
		<h1><a href="displaybybrand.jsp">Display product by brand</a></h1>
		<h1><a href="displaybycategory.jsp">Display product by category</a></h1>
		<h1><a href="displaybyrange.jsp">Display between range</a></h1>
<% } else{ %>
		<h1><a href="customerlogin.jsp">Login first</a></h1>
<% } %>

</body>
</html>