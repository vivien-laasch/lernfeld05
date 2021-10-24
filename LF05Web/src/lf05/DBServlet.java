package lf05;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger lgr = Logger.getLogger(DBServlet.class.getName());

	@Override
	public void init() throws ServletException {
		try {
			FileHandler fh = new FileHandler("Servlet.log");
			lgr.addHandler(fh);
		} catch (Exception e) {
			lgr.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// stuff
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String table = request.getParameter("table");
		Map<String, String> output = new HashMap<>();
		PrintWriter out = response.getWriter();
		try {
			Connection con = DBModel.initDB();
			output = DBModel.getTable(con, table);
		} catch (Exception e) {
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}

		out.println("<h1>" +  DBModel.initDBB() + "</h1>");
		out.println("<h1>" + table + "</h1>");
		output.forEach((k, v) -> out.println("<h1>" + k + ": " + v + "</h1>"));

		/*
		 * old forwarding testing RequestDispatcher rs =
		 * request.getRequestDispatcher("welcome.html"); rs.forward(request, response);
		 */
	}

	@Override
	public void destroy() {
		/*
		 * end of Servlet life cycle
		 */
	}
}