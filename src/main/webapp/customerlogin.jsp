<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
<h3>${msg}</h3>
<form action="loginvalidations" method="post">
Enter Email: <input type="email" name="email"> <br>
Enter Password: <input type="password" name="password"> <br>
<input type="submit">
</form>
</body>
</html>