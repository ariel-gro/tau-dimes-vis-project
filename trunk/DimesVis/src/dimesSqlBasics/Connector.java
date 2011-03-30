package dimesSqlBasics;

import java.sql.*;

public class Connector {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private int port = 0;
	private String user = null;
	private String pass = null;
	private String schema = null;

	public Connector() {
		System.out.println("Starting constructor");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (IllegalAccessException ex) {
			System.out.println("Default Constructor: IllegalAccessException");
			ex.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("Default Constructor: InstantiationException");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Default Constructor: ClassNotFoundException");
			e.printStackTrace();
		}

		System.out.println("Finished constructor");
	}

	public Connector(int portNumber, String userName, String password,
			String schema) {
		System.out.println("Starting constructor");
		this.port = portNumber;
		this.user = userName;
		this.pass = password;
		this.schema = schema;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (IllegalAccessException ex) {
			System.out.println("Constructor: IllegalAccessException");
			ex.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("Constructor: InstantiationException");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Constructor: ClassNotFoundException");
			e.printStackTrace();
		}

		System.out.println("Finished constructor");
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		try {
			if ((this.conn == null) || (this.conn.isClosed())) {
				this.port = port;
			} else {
				System.out.println("Can't set port when connection is opened. Call closeConection() first");
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException @ setPort()");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			// ex.printStackTrace();
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		try {
			if ((this.conn == null) || (this.conn.isClosed())) {
				this.user = user;
			} else {
				System.out.println("Can't set user name when connection is opened. Call closeConection() first");
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException @ setUser()");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			// ex.printStackTrace();
		}
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		try {
			if ((this.conn == null) || (this.conn.isClosed())) {
				this.pass = pass;
			} else {
				System.out.println("Can't set password when connection is opened. Call closeConection() first");
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException @ setPass()");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			// ex.printStackTrace();
		}
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		try {
			if ((this.conn == null) || (this.conn.isClosed())) {
				this.schema = schema;
			} else {
				System.out.println("Can't set Schema when connection is opened. Call closeConection() first");
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException @ setSchema()");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			// ex.printStackTrace();
		}
	}

	public void connect() {
		System.out.println("Starting connect()");
		try {
			if ((conn == null) || (conn.isClosed())) {
				System.out.println("connect(): getting connection");
				String connectionString = "jdbc:mysql://localhost:" + this.port
										+ "/" + this.schema + "?" + "user=" + this.user
										+ "&password=" + this.pass;
				System.out.println("connect(): connectionString = " + connectionString);
				conn = DriverManager.getConnection(connectionString);
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException @ connect()");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			// ex.printStackTrace();
		}
		System.out.println("Finished connect()");
	}

	public void closeConnection() {
		System.out.println("Starting closeConnection()");
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqlEx) {
				/* ignore */
			}

			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqlEx) {
				/* ignore */
			}

			stmt = null;
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sqlEx) {
				/* ignore */
			}

			conn = null;
		}

		System.out.println("Finised closeConnection()");
	}

	public ResultSet submitStatement(String query) {
		rs = null;
		System.out.println("Started submitStatement()");
		System.out.println("submitStatement(): query = " + query);
		try {
			// get a connection if it is not opened yet
			if ((conn == null) || (conn.isClosed())) {
				System.out.println("submitStatement(): trying to connect");
				this.connect();
			}
			
			// get a statement from the connection if one doesn't exist yet
			if ((stmt == null) && (conn != null) && (!conn.isClosed())) {
				System.out.println("submitStatement(): createing stmt");
				stmt = conn.createStatement();
			} else {
				System.out.println("submitStatement(): Can't create statement because conn is null or closed");
			}
			
			// execute query if connection and statement are OK
			if ((stmt != null) && (!stmt.isClosed()) &&
				(conn != null) && (!conn.isClosed()))
			{
				System.out.println("submitStatement(): executing query");
				rs = stmt.executeQuery(query);
			} else {
				System.out.println("submitStatement(): Can't execute query because conn or stmt are null or closed");
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException @ submitStatement()");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			// ex.printStackTrace();
		}

		return rs;
	}
}
