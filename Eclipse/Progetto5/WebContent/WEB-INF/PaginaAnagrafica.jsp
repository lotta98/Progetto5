<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Invita Utenti</h2>
	<c:choose>
		<form id="formName" action="/CheckPartecipanti" method="get">
			<c:when test="${utenti.size()>0}">
					<c:forEach var="utente" items="${utenti}" varStatus="row">
						<input type ="checkbox" name="cBox[]" value = "${utente.username}"></input>
  						<c:out value="${utente.nome}" />
    					<c:out value="${utente.cognome}" /><br>
    					
									
					</c:forEach>
					 <input type="submit" name="submit" value="Invita" />
							
			</c:when>
			<c:otherwise>Non ci sono Utenti
			</c:otherwise>
		</form>
	</c:choose>
	
</body>
</html>