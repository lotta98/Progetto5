package it.polimi.tiw.riunioni.controllers;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import it.polimi.tiw.riunioni.DAO.RiunioniDAO;
import it.polimi.tiw.riunioni.DAO.RiunioniPartecipanteDAO;
import it.polimi.tiw.riunioni.beans.Riunione;
import it.polimi.tiw.riunioni.beans.RiunionePartecipanti;
import it.polimi.tiw.riunioni.beans.Utente;



@WebServlet("/GoToHomePage")
public class GoToHomePage extends HttpServlet {
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
			throw new UnavailableException("Can't load database driver");
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginpath = getServletContext().getContextPath() + "/login.jsp";
		HttpSession s = request.getSession();
		if (s.isNew() || s.getAttribute("user") == null) {
			response.sendRedirect(loginpath);
			return;
		} 
		
		//Riunioni create
		RiunioniDAO rDAO = new RiunioniDAO(connection);
		List<Riunione> riunioni;
		
		int idUtente = ((Utente) s.getAttribute("user")).getId();
		
		try {
			riunioni= rDAO.findRiunioniCreate(idUtente);
			request.setAttribute("riunioniCreate", riunioni);
		} catch (SQLException e) {
			response.sendError(500, "00Database access failed");
		}
		
		//Riunioni a cui viene invitato
		
		RiunioniPartecipanteDAO rpDAO = new RiunioniPartecipanteDAO(connection);
		
		List<RiunionePartecipanti> rp;
		List<Riunione> invitiRiunioni;
		try {
			rp= rpDAO.findRiunioniPartByUser(idUtente);
			System.out.println("OK1");
			invitiRiunioni= rDAO.findRiunioniByUser(rp);
			System.out.println("OK2");
			request.setAttribute("invitiRiunioni", invitiRiunioni);
			String path = "/WEB-INF/HomePage.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			response.sendError(500, "01Database access failed");
		}
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException sqle) {}
	}

}
