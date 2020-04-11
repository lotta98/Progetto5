<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
</head>
<body>
	<h1>Benvenuto nella form di Registrazione</h1>
	<c:choose>
			<c:when test="${empty currentUser}">
				<p>Inserisci i dati nella form</p>
				<c:url value="/Registrazione" var="regUrl"/>	
				<c:if test= "${pwddiff == 1}"> <font color="FF0000">Le password non corrispondono</font></c:if>	
				<c:if test= "${nuovouser == 1}"> <font color="FF0000">Username già utilizzato</font></c:if>	
				<form method="post" action="${regUrl}">
	    				<label for="username"><b>Username</b></label><br>
	    				<input type="text" placeholder="Username" name="username" required/>
	    				<br>
	    				<label for="password"><b>Password</b></label><br>
	    				<input type="text" placeholder="Password" name="password" required/>
	    				<br>
	    				<label for="password2"><b>Reinserisci la password</b></label><br>
	    				<input type="password" placeholder="Password" name="password2" required/>
	    				<br>
	    				<label for="nome"><b> Nome</b></label><br>
	    				<input type="text" placeholder="Nome" name="nome" required/>
	    				<br>
	    				<label for="cognome"><b>Cognome</b></label><br>
	    				<input type="text" placeholder="Cognome" name="cognome" required/>
	    				<br>
	    				<button type="submit">Registrati</button>
	  			</form>			
			</c:when>
					
			
		</c:choose>
		<p>Hai già un account?<a href="/Progetto5/login.jsp">Accedi</a></p>
	
</body>
</html>