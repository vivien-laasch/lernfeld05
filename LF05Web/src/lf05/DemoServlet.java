package lf05;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

@SuppressWarnings("serial")
public class DemoServlet extends HttpServlet {
	private String mymsg;

	public void init() throws ServletException {
		mymsg = "I hate being";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Setting up the content type of webpage
		response.setContentType("text/html");

		// Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + mymsg + "</h1>");
	}

	public void destroy() {
		/*
		 * leaving empty for now this can be used when we want to do something at the
		 * end of Servlet life cycle
		 */
	}
}