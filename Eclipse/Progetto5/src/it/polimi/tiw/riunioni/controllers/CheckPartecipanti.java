package it.polimi.tiw.riunioni.controllers;
import  javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
		/*
	
		
			HttpSession session = request.getSession();
			if(session.isNew())
			session.setAttribute("counter",1);
			else 
			{ int toAdd= (int)session.getAttribute("counter")+1;
			session.setAttribute("counter",toAdd); 
			}
			
			
		
		if (/*numeroutenti > ((Riunione) request.getAttribute("RiunioneDaCreare")).getMaxPart() && counter <=3) {
			
			String pathhome = getServletContext().getContextPath() + "/homepage.jsp";
		response.sendRedirect(pathhome);
		return ;
		} else if (/*numeroutenti > request.getAttribute(RiunioneDaCreare).getMaxPart() && counter >3) {
			String pathend = getServletContext().getContextPath() + "/PaginaCancellazione.jsp";
			response.sendRedirect(pathend);
			return;
		} else {
			
		   addRiunione(request);
			addRiunionePartecipanti(request.getAttribute(RiunioneDaCreare).getId(),request);
			
			
			response.sendRedirect("/GotoAnagrPage");
			*/
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String[] idS = request.getParameterValues("checkbox");
			int[] id = null;
			for(int i=0;i<idS.length;i++)
				id[i]=Integer.parseInt(idS[i]);
			
		}
		
}
