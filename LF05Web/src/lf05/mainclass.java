package lf05;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.*;

public class mainclass {
	public static void main(String[] args) {
		Logger lgr = Logger.getLogger(mainclass.class.getName());
		Properties props = new Properties();
		String fileName = "src/db.properties";
		String query = "SELECT VORNAME,NACHNAME FROM kunde";
		String url = "";
		String user = "";
		String passwd = "";

		try (FileInputStream fis = new FileInputStream(fileName)) {
			props.load(fis);
		} catch (IOException ex) {
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}

		url = props.getProperty("db.url");
		user = props.getProperty("db.user");
		passwd = props.getProperty("db.password");

		try (Connection con = DriverManager.getConnection(url, user, passwd);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query)) {
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}

		} catch (SQLException ex) {
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
}
