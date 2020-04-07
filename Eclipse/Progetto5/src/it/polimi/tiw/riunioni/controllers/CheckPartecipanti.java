package it.polimi.tiw.riunioni.controllers;
import  javax.servlet.*;
import java.io.*;
import it.polimi.tiw.riunioni.DAO.*;
import it.polimi.tiw.riunioni.beans.*;


public class CheckPartecipanti extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		
		HttpSession session = request.getSession();
		if(session.isNew())
		session.setAttribute("counter",1);
		else 
		{ int toAdd= (int)session.getAttribute("counter")+1;
		session.setAttribute("counter",toAdd); // update counterfor thissession
		}
		
		
	
	if (/*numeroutenti*/ > request.getAttribute(RiunioneDaCreare).getMaxPart() && counter <=3) {
		
		String pathhome = getServletContext().getContextPath() + "/homepage.jsp";
	response.sendRedirect(pathhome);
	return ;
	} else if (/*numeroutenti*/ > request.getAttribute(RiunioneDaCreare).getMaxPart() && counter >3) {
		String pathend = getServletContext().getContextPath() + "/PaginaCancellazione.jsp";
		response.sendRedirect(pathend);
		return;
	} else {
		
	   addRiunione(request);
		addRiunionePartecipanti(request.getAttribute(RiunioneDaCreare).getId(),request);
		
		
		response.sendRedirect("/GotoAnagrPage");
		return;
	}
		
}
