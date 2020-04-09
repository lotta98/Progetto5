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
							<th>Giorno</th>
							<th>Mese</th>
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
								<td><c:out value="${riunione.giorno}" /></td>
								<td><c:out value="${riunione.mese}" /></td>
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
							<th>Giorno</th>
							<th>Mese</th>
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
								<td><c:out value="${riunione.giorno}" /></td>
								<td><c:out value="${riunione.mese}" /></td>
								<td><c:out value="${riunione.ora}" /></td>
								
							</c:forEach>
						</tbody>
						</table>
		</c:when>
		<c:otherwise>No topics to display.
		</c:otherwise>
	</c:choose>

	<p>Inserisci i dati per creare una nuova Riunione</p>
	<c:if test="${err!=0}"><font color="red"> Errore inserimento dati</font> </c:if>
			<c:url value="/GoToAnagrPage" var="url"/>			
			<form method="post" action="${url}">
    				<label for="titolo"><b>Titolo</b></label>
    				<div><input type="text" placeholder="Inserisci Titolo" name="titolo" required/></div>
    				
    				<label for="giorno">Giorno</label>
  					<div><input type="text" placeholder="Inserisci Giorno" name="giorno" required></div>
  					
    				<label for="mese">Mese</label>
  					<div><input type="text" placeholder="Inserisci Mese" name="mese" required></div>
  					
    				<label for="ora">Ora</label>
    				<div><input type="text" placeholder="Inserisci Ora" name="ora" required/></div>
    				
    				<label for="durata">Durata</label>
    				<div><input type="text" placeholder="Inserisci Durata" name="durata" required/></div>
    				
    				
    				<button type="submit">Crea Riunione</button>
  			</form>	
	

</body>
</html>

