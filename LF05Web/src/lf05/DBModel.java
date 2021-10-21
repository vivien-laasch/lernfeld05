package lf05;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class DBModel {
	private Connection db = null;

	public DBModel(String url, String user, String passwd) throws SQLException {
		db = DriverManager.getConnection(url, user, passwd);
	}

	public List<DBElement> getTable(String table) throws SQLException{
		String[] cond = null;
		return this.getTable(table, "", cond);
	}

	public List<DBElement> getTable(String table, String col, String[] cond) throws SQLException {
		String filter = "";
		Statement st;
		ResultSet rs;
		String query = "SELECT * FROM" + table + "WHERE 1=1" + filter;

		st = this.db.createStatement();
		rs = st.executeQuery(query);
		st.close();
		
		
		// countColumns
		return Collections.emptyList();
	}
}
