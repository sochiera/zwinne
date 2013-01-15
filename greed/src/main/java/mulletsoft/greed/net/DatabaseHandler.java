package mulletsoft.greed.net;

import java.sql.*;
import java.util.Properties;

public class DatabaseHandler {
	
	private String username, password, url;
	private boolean connected;
	
	public DatabaseHandler()
	{
		this.connected = false;
		this.username = "";
		this.password = "";
		this.url = "jdbc:postgresql://127.0.0.1:5432/db";
		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("No PSQL Driver found." + e.getMessage());
        }
	}
	
	public void connect() throws SQLException {
        Connection connection = DriverManager.getConnection(this.url);
        connection.close();
        this.connected = true;
    }
	
	public boolean isConnected() {
		return this.connected;
	}
	
}
