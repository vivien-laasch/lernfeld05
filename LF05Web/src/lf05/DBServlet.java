package lf05;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ArrayList<String> tables = new ArrayList<>();
		ArrayList<String> columns = new ArrayList<>();
		ArrayList<String> filters = new ArrayList<>();
		Map<Integer, Map<String, String>> output = new HashMap<>();
		PrintWriter out = response.getWriter();
		
		tables.add(request.getParameter("table"));
		columns.add(request.getParameter("column"));
		filters.add(request.getParameter("filter"));
	
		try {
			Connection con = DBModel.initDB();
			output = DBModel.getTable(con, tables, columns, filters);
		} catch (Exception e) {
			out.println(e.getMessage());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		output.forEach((k, v) -> {
			out.println("<h1>" + k + "</h1>\n");
			v.forEach((x, y) -> out.println(x + ": " + y + "\n"));
		});

		out.println("<a href=\"welcome.html\">Back</a>");
		/*
		 * old forwarding testing RequestDispatcher rs =
		 * request.getRequestDispatcher("welcome.html"); rs.forward(request, response);
		 */
	}
}