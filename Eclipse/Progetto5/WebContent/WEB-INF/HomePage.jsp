<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOMEPAGE</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h1>HomePage</h1>
	<h2>Riunioni Create</h2>
	
	<c:choose>
		<c:when test="${riunioniCreate.size()>0}">
		<table border="1">
							<thead
							><tr>
							<th>Titolo</th>
							<th>Data</th>
							<th>Ora</th>
							</tr></thead>
							<tbody>
							<c:forEach var="riunione" items="${riunioniCreate}" varStatus="row">
								<c:choose>
									<c:when test="${row.count % 2 == 0}">
										<tr class="even">
									</c:when>
									<c:otherwise>
										<tr>
									</c:otherwise>
								</c:choose>
								<td><c:out value="${riunione.titolo}" /></td>
								<td><c:out value="${riunione.data}" /></td>
								<td><c:out value="${riunione.ora}" /></td>
								
							</c:forEach>
						</tbody>
						</table>
			
		</c:when>
		<c:otherwise>No topics to display.
		</c:otherwise>
	</c:choose>
	<h2>Inviti a Riunioni</h2>
	<c:choose>
		<c:when test="${invitiRiunioni.size()>0}">
			<table border="1">
							<thead
							><tr>
							<th>Titolo</th>
							<th>Data</th>
							<th>Ora</th>
							</tr></thead>
							<tbody>
							<c:forEach var="riunione" items="${invitiRiunioni}" varStatus="row">
								<c:choose>
									<c:when test="${row.count % 2 == 0}">
										<tr class="even">
									</c:when>
									<c:otherwise>
										<tr>
									</c:otherwise>
								</c:choose>
								<td><c:out value="${riunione.titolo}" /></td>
								<td><c:out value="${riunione.data}" /></td>
								<td><c:out value="${riunione.ora}" /></td>
								
							</c:forEach>
						</tbody>
						</table>
		</c:when>
		<c:otherwise>No topics to display.
		</c:otherwise>
	</c:choose>
	<p>Inserisci i dati per creare una nuova Riunione</p>
			<c:url value="/GoToAnagrPage" var="url"/>			
			<form method="post" action="${url}">
    				<label for="titolo"><b>Titolo</b></label>
    				<input type="text" placeholder="Inserisci Titolo" name="titolo" required/>
    				<br>
    				<label for="data">Data:</label>
  					<input type="date" id="data" name="data">
    				<label for="ora"><b>Ora</b></label>
    				<input type="text" placeholder="Inserisci Titolo" name="ora" required/>
    				<br>
    				<button type="submit">Crea Riunione</button>
  			</form>	
	

</body>
</html>

