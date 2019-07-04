package basic.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SetCookie extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String firstName = request.getParameter("firstName");
		String secondName = request.getParameter("secondName");

		if (firstName != null && !firstName.equals("") && secondName != null && !secondName.equals("")) {
			Cookie c1 = new Cookie("firstName", firstName);
			Cookie c2 = new Cookie("secondName", secondName);

			c1.setMaxAge(60 * 5);
			c2.setMaxAge(60 * 5);

			c1.setComment("this is firstName");
			c2.setComment("this is secondName");

			response.addCookie(c1);
			response.addCookie(c2);

			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");

			PrintWriter writer = response.getWriter();

			writer.println("cookie save success!");
			writer.println("<br><br>");

			writer.println("<a href='ReadCookie'>read cookie</a>");

		} else {
			response.sendRedirect("login.html");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}