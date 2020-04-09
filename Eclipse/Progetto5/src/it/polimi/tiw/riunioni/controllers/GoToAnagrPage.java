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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import it.polimi.tiw.riunioni.DAO.RiunioniDAO;
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

		
		HttpSession s = request.getSession();
		

		UtenteDAO uDAO = new UtenteDAO(connection);
		List<Utente> utenti;
		List<Utente> sel=new ArrayList<Utente>();
		int idUtente = ((Utente) s.getAttribute("user")).getId();

		try {

			if((int) request.getSession().getAttribute("cont")==0)
				request.setAttribute("select", sel);
			utenti= uDAO.utentiRegistrati(idUtente);

			request.setAttribute("utenti", utenti);

			String path = "/WEB-INF/PaginaAnagrafica.jsp";

			RequestDispatcher dispatcher = request.getRequestDispatcher(path);

			dispatcher.forward(request, response);


		} catch (SQLException e) {
			response.sendError(500, "00Database access failed");
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = getServletContext().getContextPath() + "/GoToHomePage";
		GregorianCalendar calendar = new GregorianCalendar();
		request.getSession().setAttribute("err", 0);
		HttpSession s = request.getSession();
		int idUtente = ((Utente) s.getAttribute("user")).getId();
		int ultimoId = 0;

		Riunione riunione = new Riunione();
		RiunioniDAO u=new RiunioniDAO(connection);
		try {
			ultimoId=u.getMaxId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String t = null;
		int m = 0,g = 0,o = 0,d =0;
		try {
			t=request.getParameter("titolo");
			m=Integer.parseInt(request.getParameter("mese"));
			g=Integer.parseInt(request.getParameter("giorno"));
			o=Integer.parseInt(request.getParameter("ora"));
			d=Integer.parseInt(request.getParameter("durata"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (m < calendar.get(Calendar.MONTH)+1) {	
			request.getSession().setAttribute("err", 1);
			response.sendRedirect(path);	
			return;
		} else {
			if (m == calendar.get(Calendar.MONTH)+1 && g < calendar.get(Calendar.DAY_OF_MONTH)) {
				request.getSession().setAttribute("err", 1);
				response.sendRedirect(path);
				return;
			}
		}

		if (g <= 0 || m <= 0) {                                       // evitare mese e giorno negativi
			request.getSession().setAttribute("err", 1);
			response.sendRedirect(path);
			return;
		} 
		else {
			if (m > 12 || g > 31) {                                 // evitare mese e giorno superiori a 12 e 31
				request.getSession().setAttribute("err", 1);
				response.sendRedirect(path);
				return;
			}
			else {
				if (((m == 4 || m == 6 || m == 9 ||m == 11) && (g == 31)) || ((m == 2) && (g == 30 || g == 31))) {  // evitare inserimento di mesi senza il 31° giorno e controllo su febbraio(considerato con 29 giorni)
					request.getSession().setAttribute("err", 1);
					response.sendRedirect(path);
					return;
				}
			}
		}

		if (o < 0 || o > 23) {    // controllo orario
			request.getSession().setAttribute("err", 1);
			response.sendRedirect(path);
			return;
		}
		
		if (d <= 0) {                                       // evitare durata negativa o nulla
			request.getSession().setAttribute("err", 1);
			response.sendRedirect(path);
			return;
		} 


		riunione.setId(ultimoId+1);
		riunione.setTitolo(t);
		riunione.setAnno(2020);
		riunione.setMese(m);
		riunione.setGiorno(g);
		riunione.setOra(o);
		riunione.setDurata(d);
		riunione.setMaxPart(4);
		riunione.setIdCreatore(idUtente);

		request.getSession().setAttribute("RiunioneDaCreare", riunione);

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
