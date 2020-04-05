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
	<h2>Riunioni</h2>
	<c:choose>
		<c:when test="${riunioni.size()>0}">
			<table border="1">
				<tr>
					<th>Riunioni create</th>
				</tr>
				<tbody>
					<c:forEach var="riunione" items="${riunioni}" varStatus="row">
						<c:choose>
							<c:when test="${row.count % 2 == 0}">
								<tr class="even">
							</c:when>
							<c:otherwise>
								<tr>
							</c:otherwise>
						</c:choose>
						<c:url value="/GetRiunioniCreate" var="regURL">
							<c:param name="riunioneid" value="${riunione.id}" />
						</c:url>
						<td><a href="${regURL}"> <c:out
									value="${riunione.titolo}" />
						</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>No topics to display.
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${riunioni.size()>0}">
			<table border="1">
				<tr>
					<th>Inviti a riunioni</th>
				</tr>
				<tbody>
					<c:forEach var="riunione" items="${riunioni}" varStatus="row">
						<c:choose>
							<c:when test="${row.count % 2 == 0}">
								<tr class="even">
							</c:when>
							<c:otherwise>
								<tr>
							</c:otherwise>
						</c:choose>
						<c:url value="/GetInvitiRiunioni" var="regURL">
							<c:param name="riunioneid" value="${riunione.id}" />
						</c:url>
						<td><a href="${regURL}"> <c:out
									value="${riunione.titolo}" />
						</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>No topics to display.
		</c:otherwise>
	</c:choose>

</body>
</html>

