package piazza;

/**
 *
 * @author Svein Erik
 */

import java.sql.*;
import java.util.Properties;

public abstract class DBConn {

    //Edit these to connect to your own database
    private static final String DB_NAME = "piazza";
    private static final String DB_USERNAME = "Thomas"; //Change to local username on MySQL
    private static final String DB_PASSWORD = "vbdatdat123"; //Change to local password on MySQL

    protected Connection conn;
    public DBConn () {
    }
    public void connect() {
    	try {
            // Class.forName("com.mysql.jdbc.Driver").newInstance(); when you are using MySQL 5.7
	    Class.forName("com.mysql.cj.jdbc.Driver"); 
	    // Properties for user and password.
            Properties p = new Properties();
            p.put("user", DB_USERNAME);
            p.put("password", DB_PASSWORD);           
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+DB_NAME+"?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false",p);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
}