<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina di cancellazione</title>
</head>
<body>
<jsp:include page="header.jsp" />
<br><br>
<p>Tre tentativi di definire una riunione con troppi partecipanti, la riunione non sarà creata</p>
<BR>
<p><a href="<c:url value="/GoToHomePage"/>">Go To Home Page</a></p>
</body>
</html>