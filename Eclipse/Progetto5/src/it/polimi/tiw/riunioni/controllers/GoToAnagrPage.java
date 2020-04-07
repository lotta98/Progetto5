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

import it.polimi.tiw.riunioni.DAO.UtenteDAO;
import it.polimi.tiw.riunioni.beans.Riunione;
import it.polimi.tiw.riunioni.beans.Utente;


@WebServlet("/GoToAnagrPage")
public class GoToAnagrPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private Connection connection=null;
  
      
    public GoToAnagrPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	
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
		System.out.println("OK4");
		String loginpath = getServletContext().getContextPath() + "/login.jsp";
		HttpSession s = request.getSession();
		if (s.isNew() || s.getAttribute("user") == null) {
			response.sendRedirect(loginpath);
			return;
		} 
		
		UtenteDAO uDAO = new UtenteDAO(connection);
		List<Utente> utenti;
		int idUtente = ((Utente) s.getAttribute("user")).getId();
		
		try {
			
			utenti= uDAO.utentiRegistrati(idUtente);
			System.out.println("OK5");
			request.setAttribute("utenti", utenti);
			String path = "/WEB-INF/PaginaAnagrafica.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			System.out.println("OK6");
			dispatcher.forward(request, response);
			
			
		} catch (SQLException e) {
			response.sendError(500, "00Database access failed");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OK1");
		HttpSession s = request.getSession();
		int idUtente = ((Utente) s.getAttribute("user")).getId();
		
		
		Riunione riunione = new Riunione();
		int m=Integer.parseInt(request.getParameter("mese"));
		int g=Integer.parseInt(request.getParameter("giorno"));
		int o=Integer.parseInt(request.getParameter("ora"));
		int d=Integer.parseInt(request.getParameter("durata"));
		riunione.setTitolo(request.getParameter("titolo"));
		riunione.setAnno(2020);
		riunione.setMese(m);
		riunione.setGiorno(g);
		riunione.setOra(o);
		riunione.setDurata(d);
		riunione.setMaxPart(4);
		riunione.setIdCreatore(idUtente);
		System.out.println("OK2");
		request.setAttribute("RiunioneDaCreare", riunione);
		System.out.println("OK3");
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
