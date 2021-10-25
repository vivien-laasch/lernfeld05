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

	public static Connection initDB() throws SQLException, ClassNotFoundException, IOException {
		String file = "db.properties";
		try (InputStream is = DBModel.class.getClassLoader().getResourceAsStream(file)) {
			props.load(is);
		}

		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String passwd = props.getProperty("db.password");
		String dbDriver = props.getProperty("db.driver");

		Class.forName(dbDriver);
		return DriverManager.getConnection(url, user, passwd);

	}

	public static Map<Integer, Map<String, String>> getTable(Connection con, List<String> tables)
			throws SQLException {
		ArrayList<String> cond = new ArrayList<>();
		ArrayList<String> col = new ArrayList<>();
		return DBModel.getTable(con, tables, col, cond);
	}

	public static Map<Integer, Map<String, String>> getTable(Connection con, List<String> tables, List<String> col,
			List<String> cond) throws SQLException {
		StringBuilder bld = new StringBuilder();
		String filter = "";
		String table = "";
		ResultSet rs;
		Map<Integer, Map<String, String>> ret = new HashMap<>();

		for (String str : tables) {
			bld.append(str + ",");
		}

		table = bld.toString().replaceAll(",+$", "");

		if (col.size() == cond.size()) {
			bld = new StringBuilder();
			for (int i = 0; i < col.size(); i++) {
				if (col.get(i).length() > 0 && cond.get(i).length() > 0) {
					bld.append(" AND " + col.get(i) + " = '" + cond.get(i) + "'");
				}
			}
			filter = bld.toString();
		}

		String query = "SELECT * FROM " + table + " WHERE 1=1" + filter;

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

}
