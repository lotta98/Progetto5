<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<p><c:out value="${currentUser.username}"></c:out></p>
<p><a href="<c:url value="/Logout"/>">Log out</a></p>
