package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/HoraServlet")
public class HoraServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		response.setContentType("text/html");
		PrintWriter html = response.getWriter();
		html.print("<strong>");
		Date date = (Date)sesion.getAttribute("date");
		if(date != null) {
			html.print("Último acceso de la sesión: " + date + "<br>");
		}
		else {
			html.print("Este es el primer acceso de la sesión <br>");
		}
		date = new Date();
		sesion.setAttribute("date", date);
		sesion.setMaxInactiveInterval(10);
		html.print("Fecha actual: " + date);
		html.print("</strong>");
	}

}

