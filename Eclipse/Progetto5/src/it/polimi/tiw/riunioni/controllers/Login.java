package it.polimi.tiw.riunioni.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.polimi.tiw.riunioni.beans.Utente;

import it.polimi.tiw.riunioni.DAO.UtenteDAO;

@WebServlet("/Login")
public class Login extends HttpServlet{
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
		UtenteDAO usr = new UtenteDAO(connection);
		Utente u = null;
		try {
			u = usr.checkUser(usrn, pwd);
		} catch (SQLException e) {
			
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Failure in database credential checking");
 		}
		String path = getServletContext().getContextPath();
		if (u == null) {
			path = getServletContext().getContextPath() + "/login.jsp";
		} else {
			request.getSession().setAttribute("user", u);
			request.getSession().setAttribute("err", 0);
			String target ="/GoToHomePage";
			path = path + target;
		}
		response.sendRedirect(path);
	}

	
	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException sqle) {}
	}	
}
