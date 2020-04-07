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
		String loginpath = getServletContext().getContextPath() + "/login.jsp";
		HttpSession s = request.getSession();
		if (s.isNew() || s.getAttribute("user") == null) {
			response.sendRedirect(loginpath);
			return;
		} 
		doPost(request,response);

		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			String[] idS = request.getParameterValues("checkbox");
			int part=idS.length;
			int idPart[]=new int[part];
			request.setAttribute("Selezionati", idS);
		
			
			for(int i=0;i<idS.length;i++) {
				System.out.println(idS[i]);
				System.out.println(Integer.parseInt(idS[i]));
				idPart[i]=Integer.parseInt(idS[i]);
				
			}
			
			
			
			if(part>3) {
				if((boolean) request.getAttribute("cont")) {
					
					int cont=(int) request.getAttribute("cont");
					if(cont==2) {
						String pathend = getServletContext().getContextPath() + "/PaginaCancellazione.jsp";
						response.sendRedirect(pathend);
					}
					request.setAttribute("cont", cont+1);
					request.setAttribute("eccessivi", part-3);
					response.sendRedirect("/GoToAnagrPage");
				}
					
			}
			else {
				System.out.println("OKCP1");
				RiunioniDAO rDAO=new RiunioniDAO(connection);
				RiunioniPartecipanteDAO rpDAO=new RiunioniPartecipanteDAO(connection);
				try {
					System.out.println("OKCP2");
					Riunione r=(Riunione) request.getSession().getAttribute("RiunioneDaCreare");
					rDAO.addRiunione(r);
					System.out.println("OKCP3"+ r.getId());
					rpDAO.addRiunionePartecipante(r.getId(), idPart);
					System.out.println("OKCP4");
					String path=getServletContext().getContextPath() + "/GoToHomePage";
					response.sendRedirect(path);
					
				} catch (SQLException e) {
					response.sendError(500, "00Database access failed");
				}
				
			}
			
				
				
			
		}
		
}
