package mulletsoft.greed;

import java.sql.SQLException;

import junit.framework.TestCase;

public class DatabaseHandlerConnectionTest extends TestCase {

	public void testRun() throws SQLException {
	    DatabaseHandler handler = new DatabaseHandler();
	    handler.connect();
	    assertTrue(handler.isConnected());
	  }
}
