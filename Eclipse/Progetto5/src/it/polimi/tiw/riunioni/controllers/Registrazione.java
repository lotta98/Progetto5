package it.polimi.tiw.riunioni.controllers;
import  javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.polimi.tiw.riunioni.beans.*;
import it.polimi.tiw.riunioni.DAO.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	public void init() throws ServletException {
		try {
			ServletContext context = getServletContext();
			String driver = context.getInitParameter("dbDriver");
			String url = context.getInitParameter("dbUrl");
			String user = context.getInitParameter("dbUser");
			String password = context.getInitParameter("dbPassword");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
			throw new UnavailableException("Can't load database driver");
		} catch (SQLException e) {
		    e.printStackTrace();
			throw new UnavailableException("Couldn't get db connection");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usrn = request.getParameter("username");
		String pwd = request.getParameter("password");
		String pwdconf = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		UtenteDAO usr = new UtenteDAO(connection);
		Utente u = null;
		try {
			u = usr.checkUser(usrn, pwd);
		} catch (SQLException e) {
			
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Failure in database credential checking");
 		}
		String path = getServletContext().getContextPath();
		if (u == null && pwd==pwdconf) {
			 
			u.setUsername(usrn);
			u.setPassword(pwd);
			u.setNome(nome);
			u.setCognome(cognome);
			usr.addUtente(u);
			path = getServletContext().getContextPath() + "/login.jsp";
			response.sendRedirect(path);
			
		} else {
			  patherror = getServletContext().getContextPath() + "/Registrazione.jsp";
			response.sendRedirect(patherror);
			
		}
		
	}
	
	
	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException sqle) {}
	}	
}


	
	
}
