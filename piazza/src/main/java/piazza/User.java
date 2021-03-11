package piazza;

/**
 *
 * @author sveinbra
 */

import java.sql.*;
import java.util.*;

public class User extends ActiveDomainObject {
    private String email;
    private String password;
    private String type;

    public User (String email) {
        this.email = email;
    }

    public String getPassw(){
        return password;
    }
    public void initialize (Connection conn) {
        try {
            //Statement stmt = conn.createStatement();
            String query = "select password, type from user where userName=?";
            PreparedStatement loginUser = conn.prepareStatement(query);
            loginUser.setString(1,email);
            loginUser.executeQuery();
            ResultSet rs = loginUser.getResultSet();
            while (rs.next()) {
                password = rs.getString("password");
                type = rs.getString("type");
            }

        } catch (Exception e) {
            System.out.println("db error during select of bruker= "+e);
            return;
        }
    }
    
    public void refresh (Connection conn) {
        initialize (conn);
    }
    
    public void save (Connection conn) {
        try {
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("update user set userName="+email+", password="+password+", type="+type+"where userName="+email);
        } catch (Exception e) {
            System.out.println("db error during update of bruker="+e);
            return;
        }
    }
    
}