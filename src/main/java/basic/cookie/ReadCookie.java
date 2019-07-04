package basic.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ReadCookie extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Cookie c = null;
		Cookie[] cookies = request.getCookies();

		String secondName = null;
		PrintWriter writer = response.getWriter();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				c = cookies[i];

				if ("qian".equals(c.getName())) {
					secondName = c.getValue();
					break;
				}

			}
		}

		writer.println("Read Cookie test!");
		writer.println("<br><br>");

		if (secondName != null && !secondName.equals("")) {

			writer.println("secondName is " + secondName);

		} else {
			writer.println("secondName is nulls");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
		
		response.encodeURL("");
		
	}
}