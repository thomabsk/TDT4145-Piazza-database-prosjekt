package piazza;

import java.sql.*;
import java.util.*;

public class UserLoginCtrl extends DBConn {
    private User user;
    private boolean logged_in = false;

    public UserLoginCtrl () {
        connect();
        // La laging av avtale vÃ¦re en transaksjon
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of MakeUserLoginCtrl="+e);
            return;
        }
    }

    
    public String getUserName(){
        return user.getUserName();
    }
    
    public boolean isLoggedIn(){
        return logged_in;
    }
    public boolean loginUser(String email, String password){
        user = new User(email);
        user.initialize(conn);

        if(user.checkCorrectPassword(password)){ 
            logged_in = true;
            return true;
        }
        else {
            logged_in = false;
            return false;
        }
    }

}