<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- in order to hide the login credentials from the url,we use method as post -->
<h1>${msg}</h1>
<form action="loginvalidation" method="post">         
enter email:<input type="email" name="email">
enter password:<input type="password" name="password">
<input type="submit" value="Login">
</form>
</body>
</html>