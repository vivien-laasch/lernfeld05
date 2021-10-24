package lf05;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.HashMap;

public class DBModel {
	private static Properties props = new Properties();

	private DBModel() {

	}

	protected static Connection initDB() throws SQLException, ClassNotFoundException {
		String file = "lib/db.properties";
		try (FileInputStream fis = new FileInputStream(file)) {
			props.load(fis);
		} catch (IOException e) {
			// lgr.log(Level.SEVERE, e.getMessage(), e);
		}

		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String passwd = props.getProperty("db.password");
		String dbDriver = props.getProperty("db.driver");

		Connection con = null;
		Class.forName(dbDriver);
		con = DriverManager.getConnection(url, user, passwd);
		return con;

	}

	protected static Map<String, String> getTable(Connection con, String table) throws SQLException {
		ArrayList<String> cond = new ArrayList<>();
		ArrayList<String> col = new ArrayList<>();
		return DBModel.getTable(con, table, col, cond);
	}

	protected static Map<String, String> getTable(Connection con, String table, List<String> col, List<String> cond)
			throws SQLException {
		String filter = "";
		StringBuilder bld = new StringBuilder();
		ResultSet rs;
		HashMap<String, String> ret = new HashMap<>();

		if (col.size() == cond.size()) {
			for (int i = 0; i < col.size(); i++) {
				bld.append(" AND WHERE" + col.get(i) + " = " + cond.get(i));
			}
		}
		filter = bld.toString();

		//TODO Add back filter
		String query = "SELECT * FROM" + table;

		try (Statement st = con.createStatement()) {
			rs = st.executeQuery(query);
		}

		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columnCount; i++) {
				ret.put(rsmd.getColumnName(i), rs.getString(i));
			}
		}
		return ret;
	}
}
