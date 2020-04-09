package it.polimi.tiw.riunioni.controllers;
import  javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;

import javax.servlet.RequestDispatcher;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import it.polimi.tiw.riunioni.DAO.*;
import it.polimi.tiw.riunioni.beans.*;

@WebServlet("/CheckPartecipanti")
public class CheckPartecipanti extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection=null;

    
	public CheckPartecipanti() {
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
		doPost(request,response);

}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			if(request.getParameterValues("checkbox")==null) {
				String path=getServletContext().getContextPath() + "/GoToAnagrPage";
				response.sendRedirect(path);
				return;
			}
			String[] idS = request.getParameterValues("checkbox");
			int part=idS.length;
			
			int idPart;
			List<Utente> utentiSelezionati=new ArrayList<Utente>();
		
			
			for(int i=0;i<idS.length;i++) {
				Utente u=new Utente();
				idPart=Integer.parseInt(idS[i]);
				u.setId(idPart);
				utentiSelezionati.add(u);
			}
			
			request.getSession().setAttribute("select", utentiSelezionati);
			
			
			if(part>3) {
					int cont=(int) request.getSession().getAttribute("cont");
					if(cont==2) {
						String pathend ="/WEB-INF/PaginaCancellazione.jsp";
						RequestDispatcher dispatcher = request.getRequestDispatcher(pathend);
						dispatcher.forward(request, response);
						return;
					}
					
					request.getSession().setAttribute("cont", cont+1);
					request.getSession().setAttribute("eccessivi", part-3);
					String path=getServletContext().getContextPath() + "/GoToAnagrPage";
					response.sendRedirect(path);
				
					
			}
			else {
				
				RiunioniDAO rDAO=new RiunioniDAO(connection);
				RiunioniPartecipanteDAO rpDAO=new RiunioniPartecipanteDAO(connection);
				try {
					
					Riunione r=(Riunione) request.getSession().getAttribute("RiunioneDaCreare");
					rDAO.addRiunione(r);
					
					rpDAO.addRiunionePartecipante(r.getId(), utentiSelezionati);
					
					String path=getServletContext().getContextPath() + "/GoToHomePage";
					response.sendRedirect(path);
					
				} catch (SQLException e) {
					response.sendError(500, "00Database access failed");
				}
				
			}
			
				
				
			
		}
		
}
