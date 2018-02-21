package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DevuelveCookie
 */
@WebServlet("/DevuelveCookie")
public class DevuelveCookie extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DevuelveCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Servlet Test</title></head>");
		out.println("<body ><center><h1> THIS IS A SERVLET TEST </h1>");
		String answer = request.getParameter("email");
		out.println("<h2> emailCookie: " + answer + "</h2>");
		out.println("</center></body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// Se leen los parámetros
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String email = request.getParameter("email");
			// Se crea el objeto usuario (se supone que existe la clase Usuario)
			usuario usuario = new usuario(email, nombre, apellidos);

			// A continuación se guarda en la sesión el mismo objeto que en la base de datos
			HttpSession session = request.getSession( );
			session.setAttribute ("usuario",usuario);
			// Y se almacena el email en una cookie para poder identificar en el futuro
			// al usuario mediante su email cuando vuelva a navegar por el sitio web
			Cookie c = new Cookie("emailCookie", email);
			c.setMaxAge(60*60*24*365*0);
			c.setPath("/");
			response.addCookie(c);
			doGet(request,response);
	}
}
