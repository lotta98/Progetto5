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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usrn = request.getParameter("username");
		String pwd = request.getParameter("password");
		String pwdconf = request.getParameter("password2");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		UtenteDAO usr = new UtenteDAO(connection);
		Utente u = new Utente();
		
		String path = getServletContext().getContextPath();
		try {
			if(usr.checkNuovoUser(usrn).getUser()!=null) {
				String patherror = getServletContext().getContextPath() + "/Registrazione.jsp";
				response.sendRedirect(patherror);
				return;
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pwd.equals(pwdconf)) {
			
			u.setUser(usrn);
			u.setPassword(pwd);
			u.setNome(nome);
			u.setCognome(cognome);
			try {
				usr.addUtente(u);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			}
			path = getServletContext().getContextPath() + "/login.jsp";
			response.sendRedirect(path);
			
		} 
		else {
		 String patherror = getServletContext().getContextPath() + "/Registrazione.jsp";
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


	
	

