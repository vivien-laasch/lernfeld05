package lf05;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.HashMap;

public class DBModel {
	private static Properties props = new Properties();

	private DBModel() {

	}

	protected static Connection initDB() throws SQLException, ClassNotFoundException {
		String file = "db.properties";
		try (InputStream is = DBModel.class.getClassLoader().getResourceAsStream(file)) {
			props.load(is);
		} catch (IOException e) {
			// lgr.log(Level.SEVERE, e.getMessage(), e);
		}

		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String passwd = props.getProperty("db.password");
		String dbDriver = props.getProperty("db.driver");

		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(url, user, passwd);
		return con;

	}

	protected static String initDBB() {
		String file = "db.properties";
		try (InputStream is = DBModel.class.getClassLoader().getResourceAsStream(file)) {
			props.load(is);
		} catch (IOException e) {
			return e.toString();
		}

		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String passwd = props.getProperty("db.password");
		return url + user + passwd;

	}

	protected static Map<Integer, Map<String, String>> getTable(Connection con, String table) throws SQLException {
		ArrayList<String> cond = new ArrayList<>();
		ArrayList<String> col = new ArrayList<>();
		return DBModel.getTable(con, table, col, cond);
	}

	protected static Map<Integer, Map<String, String>> getTable(Connection con, String table, List<String> col,
			List<String> cond) throws SQLException {
		String filter = "";
		StringBuilder bld = new StringBuilder();
		ResultSet rs;
		Map<Integer, Map<String, String>> ret = new HashMap<>();

		if (col.size() == cond.size()) {
			for (int i = 0; i < col.size(); i++) {
				bld.append(" AND WHERE" + col.get(i) + " = " + cond.get(i));
			}
		}
		filter = bld.toString();

		//TODO Add back filter
		String query = "SELECT * FROM " + table + " WHERE 1=1 ";

		try (Statement st = con.createStatement()) {
			rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String, String> tmp = new HashMap<>();
				for (int i = 2; i <= columnCount; i++) {
					tmp.put(rsmd.getColumnName(i), rs.getString(i));
				}
				ret.put(rs.getInt(1), tmp);
			}
		}
		return ret;
	}

	public static String getTable2(Connection con, String table) {
		return "SELECT * FROM " + table;
	}
}
