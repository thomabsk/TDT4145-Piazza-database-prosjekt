package piazza;

import java.sql.*;
import java.util.*;


/*
*
* Controller used for logging into a user in the database.
*/
public class UserLoginCtrl extends DBConn {
    private User user;
    private boolean logged_in = false;

    public UserLoginCtrl () {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of MakeUserLoginCtrl="+e);
            return;
        }
    }

    public String getUserType(){
        return user.getUserType();
    }
    public String getUserName(){
        return user.getUserName();
    }
    
    public boolean isLoggedIn(){
        return logged_in;
    }

    /* 
    *  Makes the object and checks the password toward this object, saves the new state of login.
    */
    public void loginUser(String email, String password){

        user = new User(email);
        user.initialize(conn);

        if(user.checkCorrectPassword(password)){ 
            logged_in = true;
        }
        else {
            logged_in = false;
        }
    }

}