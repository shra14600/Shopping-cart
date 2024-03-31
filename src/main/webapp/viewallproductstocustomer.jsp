<%@page import="java.util.List"%>
<%@page import="com.jsp.shoppingcart.dto.Product"%>
<%@page import="com.jsp.shoppingcart.dto.Merchant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product list</title>
</head>
<body>
<%
List<Product> products=(List<Product>)request.getAttribute("productlist");
%>
<h1><a href="fetchitemsfromcart">View Cart</a></h1>
<table cellPadding="20px" border="1">
<th>brand</th>
<th>model</th>
<th>category</th>
<th>price</th>
<th>Add to cart</th>
<%for(Product p:products){%>
	<tr>
	<td> <%= p.getBrand() %></td>
	<td> <%= p.getModel() %></td>
	<td> <%= p.getCategory() %></td>
	<td> <%= p.getPrice() %></td>
	<td><a href="additem?id=<%= p.getId() %>">add</a></td>
	</tr>
<% }%>
</table>
</body>
</html>