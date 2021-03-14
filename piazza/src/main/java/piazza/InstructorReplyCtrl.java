package piazza;

import java.sql.*;

public class InstructorReplyCtrl extends DBConn{
    private int postID;

    public InstructorReplyCtrl(){
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of MakeUserLoginCtrl="+e);
            return;
        }
    }


    public void setPostReplyID (int postID){
        this.postID = postID;
    }


    

}
