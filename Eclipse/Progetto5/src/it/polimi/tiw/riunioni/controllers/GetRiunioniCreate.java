package it.polimi.tiw.riunioni.controllers;

import it.polimi.tiw.riunioni.DAO.*;
import it.polimi.tiw.riunioni.beans.*;

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





@WebServlet("/GetRiunioniCreate")

public class GetRiunioniCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;

	public GetRiunioniCreate() {
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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RiunioniDAO rDAO = new RiunioniDAO(connection);
		List<Riunione> riunioni;
		int idCreatore = ((Utente) session.getAttribute("currentUser")).getId();
		try {
			riunioni= rDAO.findRiunioniCreate(idCreatore);
			String path = "/WEB-INF/HomePage.jsp";
			request.setAttribute("riunione", riunioni);
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			response.sendError(500, "Database access failed");
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
