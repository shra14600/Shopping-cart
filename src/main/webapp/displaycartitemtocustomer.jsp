<%@page import="com.jsp.shoppingcart.dto.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
List<Item> items=(List<Item>) request.getAttribute("itemslist");
double totalprice=(Double) request.getAttribute("totalprice");
%>
<table>
<th>Brand</th>
<th>Model</th>
<th>Category</th>
<th>Price</th>
<th>Quantity</th>
<% for(Item i:items){ %>
<tr>
<td><%=i.getBrand() %></td>
<td><%=i.getModel() %></td>
<td><%=i.getCategory() %></td>
<td><%=i.getPrice() %></td>
<td><%=i.getQuantity() %></td>
</tr>
<%} %>
</table>
<h2>Total price:<%=totalprice %></h2><br>
<a href="addorder">BUY NOW</a>
</body>
</html>