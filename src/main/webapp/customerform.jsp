<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="savecustomer" modelAttribute="customerobj">
<h1>Customer form</h1>
<br>
Enter Name:<form:input path="name"/><br>
Enter Address:<form:input path="address"/><br>
Enter Mobile number:<form:input path="mobilenum"/><br>
Enter Email:<form:input path="email"/><br>
Enter Password:<form:input path="password"/><br>
<input type="submit" value="Register">
</form:form>
</body>
</html>