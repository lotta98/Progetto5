<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Progetto 5</title>
</head>
<body>
	<jsp:include page="WEB-INF/header.jsp" />
	<h1>Welcome to Login</h1>
	<c:choose>
		<c:when test="${empty currentUser}">
			<p>Log in</p>
			<c:url value="/Login" var="loginUrl"/>			
			<form method="post" action="${loginUrl}">
    				<label for="username"><b>Username</b></label>
    				<input type="text" placeholder="Enter Username" name="username" required/>
    				<label for="password"><b>Password</b></label>
    				<input type="password" placeholder="Enter Password" name="password" required/>
    				<button type="submit">Login</button>
  			</form>			
		</c:when>
				
		
	</c:choose>
</body>
</html>