package dimesSqlBasics;

import java.sql.*;

public class Connector {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public Connector() {
		System.out.println("Starting constructor");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		System.out.println("Finished constructor");

	}

	public void connect() {
		System.out.println("Starting connect()");
		try {
			if ((conn == null) || (conn.isClosed())) {
				System.out.println("connect(): getting connection");
				try {
					conn = DriverManager
							.getConnection("jdbc:mysql://localhost:5551/DIMES_PLAYGROUND?"
									+ "user=codeLimited&password=");
				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
					// ex.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished connect()");
	}

	public void closeConnection() {
		System.out.println("Starting closeConnection()");
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqlEx) {
			} // ignore

			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqlEx) {
			} // ignore

			stmt = null;
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sqlEx) {
			} // ignore

			conn = null;
		}

		System.out.println("Finised closeConnection()");
	}

	public ResultSet submitStatement(String query) {
		rs = null;
		System.out.println("Started submitStatement()");
		try {
			if ((conn == null) || (conn.isClosed())) {
				System.out.println("submitStatement(): trying to connect");
				this.connect();
			}
			try {
				if (stmt == null) {
					System.out.println("submitStatement(): createing stmt");
					stmt = conn.createStatement();
				}
				System.out.println("submitStatement(): executing query");
				rs = stmt.executeQuery(query);
			} catch (SQLException ex) {
				// handle any errors
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
				// ex.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}
}
