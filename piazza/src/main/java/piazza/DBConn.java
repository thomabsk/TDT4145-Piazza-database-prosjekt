package piazza;

/**
 *
 * @author thomabsk
 */

import java.sql.*;
import java.util.Properties;

public abstract class DBConn {

    //Edit these to connect to your own database
    private static final String DB_NAME = "piazza";
    private static final String DB_USERNAME = "Thomas"; //Change to local username on MySQL or add to your database
    private static final String DB_PASSWORD = "vbdatdat123"; //Change to local password on MySQL or add to your database

    protected Connection conn;
    public DBConn () {
    }
    public void connect() {
    	try {
	    Class.forName("com.mysql.cj.jdbc.Driver"); 
	    // Properties for user and password.
            Properties p = new Properties();
            p.put("user", DB_USERNAME);
            p.put("password", DB_PASSWORD);
            //The location of the database has to be correct here.           
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+DB_NAME+"?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false",p);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
}